## Item 10. equals는 일반 규약을 지켜 재정의하라

### equals()를 재정의하지 않는 것이 나은 경우

`equals()`를 잘못 재정의하면 끔찍한 결과를 초래한다. 따라서 다음 상황 중 하나에 해당한다면 재정의하지 않는 것이 좋다.

* 각 인스턴스가 본질적으로 고유하다.

  : 값을 표현하는 클래스가 아닌 동작하는 개체를 표현하는 클래스인 경우 ex) `Thread`

* 인스턴스의 논리적 동치성을 검사할 일이 없다.

  * 논리적 동치성: 표현하는 방식은 다르지만 같은 의미를 담고 있는 관계

    ex) `Pattern` 처럼 `equals()`로 두 인스턴스가 같은 정규표현식을 나타내는지 검사하는 경우

* 상위 클래스에서 재정의한 `equals()` 하위 클래스에도 딱 들어맞는다.

* 클래스가 pricate이거나 package-private이고 `equals()` 를 호출할 일이 없다.

* 대상 클래스가 값이 같은 인스턴스가 둘 이상 만들어지지 않음을 보장하는 인스턴스 통제 클래스거나 `Enum` 이다.



### equals()를 재정의해야 하는 경우

객체 식별성이 아닌 논리적 동치성을 확인해야 하는데, 상위 클래스의 `equals()` 는 논리적 동치성을 비교하지 않을 때다. 주로 `Integer` 나 `String` 같은 값 클래스들이 여기 해당한다. `equals()` 로 객체가 같은지가 아닌 값이 같은지를 확인하도록 재정의해두면 논리적 동치성 비교는 물론 `Map` 의 키와 `Set` 원소로 사용할 수 있게 된다.



### equals()를 재정의할 때 지켜야하는 규약

다음은 `Object` 명세에 적힌 규약이다.컬렉션 클래스들을 포함한 많은 클래스들은 전달받은 객체가 이 규약을 지킨다고 가정하고 동작하므로 이 규약을 잘 지키며 재정의해야 한다.

`equals()` 는 동치관계를 구현하며, 다음을 만족한다.

#### 반사성
> null이 아닌 모든 참조 값 x에 대해, `x.equals(x)` 는 true다.

객체는 자기 자신과 같아야 한다. 



 #### 대칭성

> null이 아닌 모든 참조 값 x, y에 대해, `x.equals(y)` 가 true면 `y.equals(x)` 도 true다. 

두 객체는 서로에 대한 동치 여부에 똑같이 답해야 한다.



#### 추이성

> null이 아닌 모든 참조 값 x, y, z에 대해, `x.equals(y)` 가 ture이고 `y.equals(z)`도 true면 `x.equals(z)` 도 true다.

첫 번째 객체와 두 번째 객체가 같고, 두 번째 객체와 세 번째 객체가 같다면, 첫 번째 객체와 세 번째 객체도 같아야 한다.

예를 들어, 상위 클래스에는 없는 새로운 필드를 하위 클래스에 추가하는 상황을 생각해보자.

~~~ java
public class Point {
    private final int x;
    private final int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;
        Point p = (Point) o;
        return p.x == x && p.y == y;
    }
    ...
}

public vlass ColorPoint extends Point {
    private final Color color;
    
    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }
    
    @Override
    public boolean euqals(Object o) {
        if (!(o instanceof Point))
            return false;
        
        // o가 일반 Point면 색상을 무시하고 비교한다.
        if (!(o instanceof ColorPoint))
            return o.equals(this);
        
        // o가 ColorPoint면 색상까지 비교한다.
        return super.equals(o) && ((ColorPoint) o).color == color;
    }
}
~~~

위의 코드는 대칭성은 지켜주지만 추이성을 깨버린다.

~~~ java
ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
Point p2 = new Point(1, 2);
ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);

p1.equals(p2);	// true
p2.equals(p3);	// true
p1.equals(p3);	// false -> 대칭성 위배
~~~

사실 **구체 클래스를 확장해 새로운 값을 추가하면서 `equals()` 규약을 만족시킬 방법은 존재하지 않는다.** 이 경우에는 **상속 대신 컴포지션을 사용**하면 된다. Point를 상속하는 대신 Point를 ColorPoint의 private 필드로 두고, ColorPoint와 같은 위치의 일반 Point를 반환하는 뷰 메서드를 public으로 추가하는 식이다.

```java
public vlass ColorPoint extends Point {
    private final Point point;
    private final Color color;
    
    public ColorPoint(int x, int y, Color color) {
        point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    // 이 ColorPoint의 Point 뷰를 반환
    public Point asPoint() {
        return point;
    }
    
    @Override
    public boolean euqals(Object o) {
        if (!(o instanceof ColorPoint))
            return false;
        ColorPoint cp = (ColorPoint) o;
        return cp.point.equals(point) && cp.color.equals(color);
    }
    ...
}
```



#### 일관성

> null이 아닌 모든 참조 값 x, y에 대해, `x.equals(z)` 를 반복해서 호출하면 항상 true를 반환하거나 항상 false를 반환한다.

두 객체가 같다면 앞으로도 영원히 같아야 한다. 가변 객체는 비교 시점에 따라 서로 다를 수도 혹은 같을 수도 있지만, 불변 객체는 한번 다르면 끝까지 달라야 한다. 클래스가 불변이든 가변이든 **`equals()` 의 판단에 신뢰할 수 없는 자원이 끼어들어서는 안 된다.**



#### null 아님

> null이 아닌 모든 참조 값 x에 대해, `x.equals(null)` 은 false다.

모든 객체가 null과 같지 않아야 한다.

~~~ java
@Override
public boolean equals(Object o) {
    if (o == null)
        return false;
    ...
}

@Override
public boolean equals(Object o) {
    if (!(o instanceof MyType))
        return false;
    MyType mt = (MyType) o;
    ...
}
~~~

위의 첫 번째 `equals()` 처럼 명시적으로 null 검사는 할 필요가 없다. 두 번째 `equals()` 처럼 `instanceof` 기반으로 타입을 검사했을 때 대상 객체가 null이면 어차피 false를 반환하기 때문이다.



### 올바르게 equals()를 구현하는 방법

#### 1. == 연산자를 사용해 입력이 자기 자신의 참조인지 확인한다.



#### 2. instanceof 연산자로 입력이 올바른 타입인지 확인한다.

어떤 인터페이스는 자신을 구현한 서로 다른 클래스끼리도 비교할 수 있도록 `equals()` 를 수정한다. 이런 인터페이스를 구현한 클래스라면 `equals()` 에서 클래스가 아닌 해당 인터페이스를 사용해야 한다.



#### 3. 입력을 올바른 타입으로 형변환한다.

2번 단계로 인해 이 단계는 무조건 성공한다.



#### 4. 입력 객체와 자기 자신의 대응되는 핵심 필드들이 모두 일치하는지 하나씩 검사한다.

모든 필드가 일치하면 true를, 하나라도 다르면 false를 반환한다.



### 타입별 인스턴스 비교

float와 double을 제외한 기본 타입 필드는 == 연산자로, 참조 타입 필드는 각각의 `equals()` 로, float와 double 필드는 각각 정적 메소드인 `Float.compare(float, float)` 와 `Double.compare(double, double)` 로 비교한다. 배열 필드는 원소 각각을 앞서 지침대로 비교한다. 배열의 모든 원소가 핵심 필드라면 `Arrays.equals()` 중 하나를 사용하면 된다.

null도 정상 값으로 취급하는 필드인 경우 정적 메소드인 `Objects.equals(Object, Object)` 로 비교해 `NullPointerException` 발생을 예방해야 한다.

비교하기가 복잡한 필드를 가진 클래스인 경우, 그 필드의 표준형을 저장해둔 후 표준형끼리 비교하면 훨씬 경제적이다.

어떤 필드를 먼저 비교하느냐에 따라 `equals()` 성능을 좌우하기도 한다. 다를 가능성이 크거나 비교하는 비용이 싼 필드를 먼저 비교하면 성능이 더 좋아진다. 동기화용 락 필드같이 객체의 논리적 상태와 관련 없는 필드는 비교하면 안 된다. 보통 파생 필드는 비교하지 않지만, 파생 필드가 객체 전체의 상태를 대표한다면 이를 먼저 비교하는 쪽이 더 빠르다.



### 주의사항

#### 1. equals()를 재정의할 땐 hashCode()도 반드시 재정의하자.



#### 2. 너무 복잡하게 해결하려 하지 말자.

필드들의 동치성만 검사해도 `equals()` 규약을 어렵지 않게 지킬 수 있다.



#### 3. Object 외의 타입을 매개변수로 받는 equals()는 선언하지 말자.

~~~ java
public boolean equals(MyType o) {
    ...
}
~~~

위의 `equals()` 는 `Object.equals()` 를 재정의한 것이 아니다. 입력 타입이 Object가 아니므로 다중정의한 것이다. 이런 `equals()` 는 오히려 방해만 된다.



### 실무 권장

`equals()` 를 직접 작성하고 테스트하는 것보다 lombok 의 `@EqualsAndHashCode` 등을 이용해 `equals()` 를 재정의하는 것이 간단하게 작성할 수 있으면서도 문제가 없다.