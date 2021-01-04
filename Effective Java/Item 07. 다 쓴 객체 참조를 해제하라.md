## Item 07. 다 쓴 객체 참조를 해제하라

### 메모리를 직접 관리하는 클래스

~~~ java
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    
    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];  // 스택에서 꺼내진 객체들을 GC가 회수하지 않아 메모리 누수 발생
    }
    
    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
~~~

스택이 커졌다가 줄어들었을 때 스택에서 꺼내진 객체들을 GC가 회수하지 않는다. 프로그램에서 그 객체들을 더 이상 사용하지 않더라도 말이다. 이 스택이 그 객체들의 다 쓴 참조를 여전히 가지고 있기 때문이다. 객체 참조 하나를 살려두면 GC는 그 객체뿐 아니라 그 객체가 참조하는 모든 객체를 회수하지 못한다. 이로 인해 성능에 악영향을 줄 수 있다.

* 다 쓴 참조: 다시 쓰지 않을 참조. 여기서는 `elements` 배열의 '활성 영역' 밖의 참조
  * '활성 영역'은 인덱스가 `size` 보다 작은 원소들로 구성



이를 해결하려면 그냥 해당 참조를 다 썼을 때 `null` 로 처리하여 다음 GC가 발생할 때 참조가 정리되게 한다. 이는 `null` 처리한 참조를 사용하려하면 즉시 `NullPointerException` 을 던지며 종료하므로 오류를 미리 방지할 수 있다.

~~~ java
public Object pop() {
    if (size == 0) {
        throw new EmptyStackException();
    }
    Object result = elements[--size];
    elements[size] = null;  // 다 쓴 참조 해제
    return result;
}
~~~



하지만 모든 객체를 다 쓰자마자 `null` 처리하는 것은 바람직하지 않다. 다 쓴 참조를 해제하는 가장 좋은 방법은 그 참조를 담은 변수를 유효 범위 밖으로 밀어내는 것이다. (로컬 변수가 영역을 넘어가면 쓸모 없어져서 정리되는 것처럼) **객체 참조를 `null` 처리하는 일은 예외적인 경우여야 한다. **

**자기 메모리를 직접 관리하는 클래스라면 메모리 누수에 주의해야 한다.** 스택도 마찬가지다. 위의 스택은 `elements` 배열로 저장소 풀을 만들어 원소를 관리한다. 배열의 활성 영역에 속한 원소들만 사용되고 비활성 영역은 쓰이지 않는다. 문제는 GC가 이를 알 수가 없다는 데 있다. GC가 보기에는 비활성 영역에서 참조하는 객체도 똑같이 유효한 객체다. 따라서, 개발자가 비활성 영역이 되는 순간 `null` 처리해서 해당 객체는 더 이상 쓰지 않는다는 것을 알려야 한다.



### 캐시

객체 참조를 캐시에 넣고 그 객체를 다 쓴 뒤로도 캐시에서 제거하지 않는 것도 메모리 누수의 원인이 된다.

- 해결방법 1: 캐시 외부에서 키를 참조하는 동안만 엔트리가 살아 있는 캐시가 필요한 경우

  ​			  → `WeakHashMap` 을 사용해 캐시를 만들어 다 쓴 엔트리는 자동으로 제거되게 한다.

- 해결방법 2: 시간이 지날수록 캐시 엔트리의 가치를 떨어뜨리는 방식을 사용하는 경우

  - `ScheduledThreadPoolExecutor` 같은 백그라운드 스레드를 활용해 쓰지 않는 엔트리를 제거
  - 캐시에 새 엔트리를 추가할 때 부수 작업으로 쓰지 않는 엔트리를 제거(`LinkedHashMap`의 `removeEldestEntry()`)



### 리스너 혹은 콜백

클라이언트가 콜백을 등록만 하고 명확히 해지하지 않는다면, 어떤 조치를 취하기 전까지 콜백이 계속 쌓여 메모리 누수의 원인이 된다. 이 경우, 콜백을 약한 참조로 저장하면 GC가 즉시 수거해간다.