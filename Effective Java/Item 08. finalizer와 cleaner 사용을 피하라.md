## Item 08. finalizer와 cleaner 사용을 피하라

자바의 객체 소멸자 중 **`finalizer` 는 예측할 수 없고 상황에 따라 위험할 수 있어 기본적으로 '쓰지 말아야' 한다.** **`cleaner`는 `finalizer` 보다는 덜 위험하지만 여전히 예측할 수 없고, 느리고 일반적으로 필요하지않다.** 자바에서는 접근할 수 없게 된 객체를 회수하는 역할을 GC가 담당하고, 비메모리 자원을 회수하는 것도 `try-with-resoureces` 와 `try-finally` 를 사용해 해결한다.



### 단점 1

`finalizer` 와 `cleaner` 는 즉시 수행된다는 보장이 없다. **즉, 이 두 가지로는 제때 실행되어야 하는 작업은 절대 할 수 없다.** `finalizer` 와 `cleaner` 그리고 이들의 수행 시점에 의존하는 프로그램 동작을 얼마나 빨리 수행할지는 전적으로 GC 알고리즘에 달렸으며, 이는 GC 구현마다 다르다.

예를 들어, 파일 리소스를 반납하는 작업을 그 안에서 처리한다면, 실제로 그 파일 리소스 처리가 언제 될지 알 수 없고, 자원 반납이 안되서 더이상 새로운 파일을 열 수 없는 상황이 발생할 수도 있다.



### 단점 2

클래스에 `finalizer` 를 달아두면 그 인스턴스의 자원 회수가 제멋대로 지연될 수 있다. `finalizer` 스레드는 우선 순위가 낮아서 언제 실행될지 모른다. 따라서, `finalizer` 안에 어떤 작업이 있고, 그 작업을 스레드가 처리 못해서 대기하고 있다면, 해당 인스턴스는 GC가 되지 않고 계속 쌓이다가 결국엔 `OutOfMomoryException` 이 발생할 수도 있다. 

`cleaner` 는 자신을 수행할 스레드를 제어할 수 있긴 하지만 여전히 백그라운드에서 수행되며 GC의 통제하에 있기 때문에 즉각 수행된다는 보장이 없다.



### 단점 3

심지어 `finalizer` 와 `cleaner` 의 수행 여부조차 보장하지 않는다. 즉, 접근할 수 없는 일부 객체의 종료 작업을 전혀 수행하지 못한 채 프로그램이 중단될 수도 있다는 것이다. **따라서, 상태를 영구적으로 수정하는 작업에서는 절대 `finalizer` 와 `cleaner` 에 의존해서는 안 된다.**

예를 들어, 데이터베이스 같은 공유 자원의 영구 락 해제를 이 둘을 통해 실행하면 분산 시스템 전체가 멈출 수 있다.

`System.gc`나 `System.runFinalization` 를 실행해도 `finalizer` 나 `cleaner` 를 바로 실행한다고 보장하진 못한다. 그걸 보장해주겠다고 만든 `System.runFinalizersOnExit`와  `Runtime.runFinalizersOnExit`은 심각한 결함으로 사용하지 않는다.



### 단점 4

`finalizer` 동작 중 발생한 예외는 무시되며, 처리할 작업이 남았어도 그 순간 종료된다. 잡지 못한 예외 때문에 해당 객체는 마무리가 덜 된 상태로 남을 수 있다. 그리고 다른 스레드가 그 객체를 사용하려 한다면 어떻게 동작할지 예측할 수 없다. 보통 잡지 못한 예외가 스레드를 중단시키고 스택 추적 내역을 추적하지만 `finalizer` 는 아무것도 출력하지 않는다.

`cleaner` 는 자신의 스레드를 통제하기 때문에 이러한 문제가 발생하지 않는다.



### 단점 5

**`finalizer` 와 `cleaner` 는 심각한 성능 문제도 동반한다.** `AutoCloseable` 객체를 만들고, `try-with-resource`로 자원 반납을 하는데 걸리는 시간은 12ns 인데 반해, Finalizer를 사용한 경우에 550ns가 걸렸다. 약 50배가 걸렸다. Cleaner를 사용한 경우에는 66ns가 걸렸다 약 5배.



### 단점 6

**`finalizer` 를 사용한 클래스는 `finalizer` 공격에 노출되어 심각한 보안 문제를 일으킬 수도 있다.** 어떤 클래스가 있고 그 클래스를 공격하려는 클래스가 해당 클래스를 상속 받는다. 악의적인 클래스의 생성자나 직렬화 과정에서 예외가 발생하면, 이 생성되다 만 객체의 `finalizer` 가 실행될 수 있다. 그럼 그 안에서 해당 객체의 참조를 기록할 수도 있고, GC가 되지 못하게 할 수도 있다. 또한 그 안에서 객체가 가진 메소드를 호출할 수도 있다. 즉, 원래는 생성자에서 예외가 발생해서 존재하질 않았어야 하는 객체인데, `finalizer` 때문에 살아 남아 있는 것이다.

`final` 클래스는 상속이 안되니까 근본적으로 이런 공격이 막혀 있으며, 다른 클래스는 아무 것도 수행하지 않는  `finalize()` 를 만들고 `final` 로 선언하면 된다.



### 자원 반납은 AutoCloseable로

`finalizer` 와 `cleaner` 는 많은 단점이 있기 때문에 사용하면 안 된다. 자원 반납이 필요한 클래스는 `AutoCloseable` 을 구현하고, `try-with-resource`를 사용하거나 클라이언트에서 객체를 다 쓰고 나면 `close()` 를 호출하면 된다.

`close()` 에는 이 객체가 더 이상 유효하지 않다는 것을 필드에 기록하고, 다른 메소드는 이 필드를 검사해서 객체가 닫힌 후에 불렸다면 `IllegalStateException` 을 던진다.



### finalizer와 cleaner는 언제 사용되는가?

#### 1) 자원의 소유자가 close()를 호출하지 않는 것에 대비한 안전망 역할

`close()` 를 클라이언트가 호출하지 않은 상황에서는 언제 호출될지 혹은 호출될지 안될지도 모르는 `finalizer` 와 `cleaner`여도 이들을 쓰는 것이 안하는 것 보다는 낫다. 실제로 자바에서 제공하는 `FileInputStream`, `FileOutputStream`, `ThreadPoolExecutor` 그리고 `java.sql.Connection`에는 안전망으로 동작하는 `finalizer` 가 있다.



#### 2) 네이티브 피어와 연결된 객체

* 네이티브 피어: 일반 자바 객체가 네이티브 메소드를 통해 기능을 위임한 네이티브 객체

네이티브 피어는 자바 객체가 아니라 GC가 그 존재를 인지하지 못해 회수하지 못한다. 이 때, `finalizer` 와 `cleaner` 를 사용하면 된다. 단, 성능 저하를 감당할 수 있고 네이티브 피어가 심각한 자원을 가지고 있지 않을 때만 해당된다. 이 상황이 아니라면 `close()` 를 사용해야 한다.



### cleaner 예제 코드

~~~ java
public class Room implements AutoCloseable {
    private static final Cleaner CLEANER = Cleaner.create();

    private static class State implements Runnable {
        int numJunkPiles;
        
        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }
        
        @Override
        public void run() {	 // close()나 cleaner가 호출
            System.out.println("방 청소");
            numJunkPiles = 0;
        }
    }
    
    private final State state;

    // cleanable 객체
    private final Cleaner.Cleanable cleanable;
    
    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() {
        cleanable.clean();
    }
}
~~~

* 주의할 점
  * `cleaner` 스레드(`State`)는 정리할 대상인 객체(`Room`)를 참조하면 안 된다. 순환 참조가 생겨서 GC 대상이 되지 않기 때문이다.