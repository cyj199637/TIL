## Item 03. private 생성자나 열거 타입으로 싱글턴임을 보증하라

### 싱글턴

: 인스턴스를 오직 하나만 생성할 수 있는 클래스 ex) 함수와 같은 무상태 객체, 설계상 유일해야 하는 시스템 컴포넌트 등

클래스를 싱글턴으로 만들면 이를 사용하는 클라이언트를 테스트하기가 어려워질 수 있다. 타입을 인터페이스로 정의하고 그 인터페이스를 구현해서 만든 싱글턴이 아니라면 싱글턴 인스턴스를 `mock` 객체로 대체하기 어렵기 때문이다.



### 싱글턴을 만드는 방식

두 방식 모두 생성자는 private으로 감춰두고, 유일한 인스턴스에 접근할 수 있는 수단으로 public static 멤버 변수를 하나 둔다.

#### 방법 1) public static 멤버 변수가 final인 경우

~~~ java
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();
    private Elvis() { ... } // Elvis.INSTANCE를 초기화할 때 딱 한 번만 호출됨
    					  // -> 다른 생성자가 없으므로 해당 인스턴스가 전체 시스템에서 하나뿐임이 보장됨
    
    public void leaveThebuilding() { ... }
}
~~~

- 예외: 권한이 있는 클라이언트가 리플렉션 API인 `AccessibleObject.setAccessible` 을 사용해 private 생성자를 호출		 할 수 있다. (private 생성자를 두 번째 객체가 생성될 때 예외를 던지도록 수정)
- 장점 1: 해당 클래스가 싱글턴임이 API에 잘 드러난다.
- 장점 2: 간결하다.



#### 방법 2) 정적 팩토리 메소드로 public static 멤버 변수를 제공

~~~ java
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis() { ... }
    // 항상 같은 객체를 반환하므로 해당 인스턴스가 전체 시스템에서 하나뿐임이 보장됨 (예외는 똑같이 적용됨)
    public static Elvis getInstance() { return INSTANCE; } 
    
    public void leaveThebuilding() { ... }
}
~~~

* 장점 1: API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다.
* 장점 2: 정적 팩토리를 제네릭 싱글턴 팩토리로 만들 수 있다.
* 장점 3: 정적 팩토리의 메소드 참조를 공급자로 사용할 수 있다.
  * `Elvis::getInstance` 를 `Supplier<Elvis>` 로 사용



### 방법 1/2로 만들어진 싱글턴 클래스를 직렬화하는 방법

싱글턴 클래스를 직렬화하려면 `Serializable` 을 구현한다고 선언하는 것만으로는 부족하다. 모든 인스턴스 필드를 `transient` (직렬화하지 않겠다) 로 선언하고 `readResolve` 메소드를 다음과 같이 구현해야 한다. 이렇게 하지 않으면 직렬화된 인스턴스를 역직렬화할 때마다 새로운 인스턴스가 만들어지기 때문이다.

~~~ java
private Object readResolve() {
    return INSTANCE;
}
~~~



#### 방법 3) 원소가 하나인 열거 타입을 선언

~~~ java
public enum Elvis {
    INSTANCE;
    
    public void leaveTheBuilding() { ... }
}
~~~

- 장점 1: 방법 1과 비슷하면서도 더 간결하고, 직렬화에 대한 추가 작업이 없다.
- 장점 2: 리플렉션으로 호출되는 상황에서도 두 번째 인스턴스가 생기지 않는다.



대부분 상황에서는 방법 3이 가장 좋은 방법이다. 단, 만들려는 싱글턴이 `Enum` 외의 클래스를 상속해야 한다면 방법 3을 사용할 수 없다. (열거 타입이 다른 인터페이스를 구현하도록 선언할 수는 있다.)