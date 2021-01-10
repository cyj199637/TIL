## Item 13. clone 재정의는 주의해서 진행하라

`Cloneable`은 복제해도 되는 클래스임을 명시하는 용도의 믹스인 인터페이스(mixin interface)이다. 하지만 의도한 목적에 조금 어긋난다. `clone` 메소드가 선언된 곳이 `Cloneable` 이 아닌 Object 클래스이고 그마저도 protected 접근 지정자이다. 그래서 `Cloneable` 을 구현한 것만으로는 외부 객체에서 `clone` 을 호출할 수 없다.

대신 `Cloneable` 은 `clone` 의 동작 방식을 결정한다. `Cloneable` 을 구현한 클래스의 객체에서 `clone` 을 호출하면 그 객체의 필드들을 복사한 객체를 반환하며, 그렇지 않은 클래스의 객체에서는 `CloneNotSupportedException` 을 던진다.



### clone 메소드의 일반 규약

> 이 객체의 복사본을 생성해 반환한다. '복사'의 정확한 뜻은 그 객체를 구현한 클래스에 따라 다를 수 있지만 일반적인 의도는 다음과 같다.
>
> 어떤 객체 x에 대해 다음 식은 참이다.
>
> `x.clone() != x`
>
> 다음 식도 참이지만 필수는 아니다.
>
> `x.clone().getClass() == x.getClass()`
>
> `x.clone().equals(x)`
>
> 관례상, 이 메소드가 반환하는 객체는 `super.clone` 을 호출해 얻어야 한다. 이 클래스와 모든 상위 클래스(Object 제외)가 이 관례를 따른다면 다음 식은 참이다.
>
> `x.clone().getClass() == x.getClass()`
>
> 관례상, 반환된 객체와 원본 객체는 독립적이어야 한다. 이를 만족하려면 `super.clone` 으로 얻은 객체의 필드 중 하나 이상을 반환 전에 수정해야 할 수도 있다.

이와 같이 `clone` 의 규약은 허술하기 때문에 구현에 관한 책임이 모두 개발자에게 있다. 예를 들어, `clone` 이 `super.clone` 이 아닌 생성자를 호출해 얻은 인스턴스를 반환해도 컴파일러에서 문제 삼지 않는다.

`Cloneable` 을 구현한 모든 클래스는 `clone` 을 재정의해야 하며, 이때 접근 제한자는 public으로 반환 타입은 클래스 자신으로 변경한다. 아래 코드처럼 `super.clone` 을 호출한 후 필요한 필드를 전부 적절히 수정한다.

~~~ java
@Override
public PhoneNumber clone() {
    try {
        // 공변 반환 타이핑으로 클라이언트가 형변환할 필요 없게끔 만들기
        return (PhoneNumber) super.clone();
    // Object의 clone에서 무조건 해당 에러를 던지게 되어있어 try-catch로 처리
    } catch (CloneNotSupportedException e) {
        throw new AssertionError();
    }
}
~~~





### clone은 원본 객체에 아무런 해를 끼치지 않는 동시에 복제된 객체의 불변식을 보장해야 한다.

클래스에 정의된 모든 필드가 기본 타입이거나 불변 객체를 참조한다면 `clone` 으로 복제해도 아무런 문제가 없다. 그러나 클래스가 가변 객체를 참조하는 필드가 있다면 문제가 발생한다. 원본과 복제본 모두 같은 객체를 참조하게 되므로 둘 중 하나를 수정하면 다른 하나도 수정되어 불변식을 해치게 된다. **따라서, `clone` 은 원본 객체에 아무런 해를 끼치지 않는 동시에 복제된 객체의 불변식을 보장해야 한다.**

이 문제를 해결하기 위한 가장 쉬운 방법은 Stack의 `clone` 방식이다.

~~~ java
@Override
public Stack clone() {
    try {
        Stack result = (Stack) super.clone();
        result.elements = elements.clone();	// 배열의 clone은 형변환할 필요 없다.
        return result;
    } catch (CloneNotSupportedException e) {
        throw new AssertionError();
    }
}
~~~

그러나 이 방식은 해당 필드가 final인 경우 작동하지 않는다. 또한 해당 필드가 컬렉션 객체이고, 내부에서 또다른 가변 객체를 참조한다면 같은 문제가 발생한다. 이를 해결하기 위해 깊은 복사를 지원하도록 메소드를 수정할 수 있지만 반복되는 재귀 호출로 인해 스택 오버플로를 일으킬 수 있다.

가변 객체를 복제하는 또다른 방법으로 `super.clone` 을 호출하여 얻은 객체의 모든 필드를 초기 상태로 설정한 다음, 원본 객체의 상태를 다시 생성하는 고수준 메소드들을 호출하는 방법이 있다. 그러나 저수준 메소드보다 느리며, 필드 단위 객체 복사가 아니기 때문에 전체 `Cloneable` 아키텍처와는 맞지 않다.



### clone에서는 재정의될 수 있는 메소드를 호출하지 않아야 한다.

만약 `clone` 이 하위 클래스에서 재정의한 메소드를 호출하면, 하위 클래스는 복제 과정에서 자신의 상태를 교정할 기회를 잃게 되어 원본과 복제본의 상태가 달라질 가능성이 크다.



### public인 clone에서는 throws 절을 없애야 한다.

재정의한 `clone` 에서는 Object의 `clone` 과 달리 `CloneNotSupportedException` 을 던지지 않아야 한다. Checked Exception을 던지지 않아야 사용하기 편하기 때문이다.



### 상속용 클래스는 Cloneable을 구현해서는 안 된다.

`Cloneable` 을 구현하더라도 Object의 `clone` 처럼 작성하여 `Cloneable` 구현 여부를 하위 클래스에서 선택하도록 하거나 아니면 `clone` 을 동작하지 않게 구현해서 하위 클래스에서 재정의하지 못하게 하면 된다.



### Cloneable을 구현한 스레드 안전 클래스를 작성할 때는 clone 역시 적절히 동기화해야 한다.

Object의 `clone` 은 동기화 처리를 하지 않았기 때문에 `clone` 을 재정의하여 동기화해줘야 한다.



### 복사 생성자와 복사 팩터리로 객체를 복사하자.

* 복사 생성자: 단순히 자신과 같은 클래스의 인스턴스를 인수로 받는 생성자
* 복사 팩토리: 복사 생성자를 모방한 정적 팩토리

`Cloneable` 을 구현하지 않은 클래스라면 복사 생성자와 복사 팩토리로 객체를 복사하는 것이 좋다. 이 방법은 `Cloneable` / `clone` 이 가진 모든 단점을 보완하면서도 해당 클래스가 구현한 인터페이스 타입의 인스턴스를 인수로 받을 수 있다. 이로 인해 클라이언트는 원본의 구현 타입에 얽매이지 않고 복제본의 타입을 직접 선택할 수 있다.

~~~ java
// 복사 생성자
public Yum(Yum yum) { ... };

// 복사 팩토리
public static Yum newInstance(Yum yum) { ... };
~~~

