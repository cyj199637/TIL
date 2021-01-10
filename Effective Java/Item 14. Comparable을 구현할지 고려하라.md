## Item 14. Comparable을 구현할지 고려하라

`Comparable` 을 구현했다는 것은 그 클래스의 인스턴스들에는 자연적인 순서가 있음을 뜻한다. 따라서, `Comparable` 을 구현한 객체들의 배열은 쉽게 정렬할 수 있다. 또한 검색, 극단값 계산, 자동 정렬되는 컬렉션 관리도 쉽게 할 수 있다. 따라서, 순서가 명확한 값 클래스를 작성한다면 반드시 `Comparable` 를 구현해야 한다.

~~~ java
public interface Comparable<T> {
	int compareTo(T t);	// 단순 동치성 비교뿐 아니라 순서까지 비교 가능
}
~~~



### compareTo의 일반 규약

> 이 객체와 주어진 객체의 순서를 비교한다. 이 객체가 주어진 객체보다 작으면 음의 정수(-1)를, 같으면 0을, 크면 양의 정수(1)를 반환한다. 이 객체와 비교할 수 없는 타입의 객체가 주어지면 ClassCastException을 던진다.
>
> * sgn(표현식): 수학에서 말하는 부호 함수
>
> 1. Comparable을 구현한 클래스는 모든 x, y에 대해 `sgn(x.compareTo(y)) == -sgn(y.compareTo(x))` 이다.
>
> 2. Comparable을 구현한 클래스는 `x.compareTo(y) > 0 && y.compareTo(z)` 이면, `x.compareTo(z) > 0` 이다.
>
> 3. Comparable을 구현한 클래스는 모든 z에 대해 `x.compareTo(y) == 0` 이면, `sgn(x.compareTo(z)) == sgn(y.compareTo(z))` 이다.
>
> 4. `(x.compareTo(y) == 0) == (x.equals(y))` 여야 한다. (필수는 아니지만 이 항목을 지키지 않은 클래스는 그 사실을 명시해야 한다.)
>
>    : 이 항목을 지키지 않은 클래스의 객체를 정렬된 컬렉션에 넣으면 해당 컬렉션이 구현한 인터페이스에 정의된 동작과 엇박자를 낼 것이다. 정렬된 컬렉션들은 동치성을 비교할 때 equals가 아닌 compareTo를 사용하기 때문이다.

`compareTo` 로 수행하는 동치성 검사도 `equals` 처럼 반사성, 대칭성, 추이성을 충족해야 하며 주의사항도 똑같다. 또한 기존 클래스를 확장한 구체 클래스에서 새로운 값을 추가했다면 `compareTo` 규약도 지킬 방법이 없다. 이때도 '상속 대신 컴포지션'을 사용하여 해결하면 된다.



### compareTo 작성 요령

`compareTo` 의 작성 요령도 `equals` 와 비슷하나 몇 가지 차이점이 있다.

1. 입력 인수의 타입을 확인하거나 형변환할 필요가 없다.

   : `Comparable` 이 제네릭 인터페이스라 `compareTo` 의 인수 타입은 컴파일타임에 정해진다. 인수의 타입이 잘못됐다면 컴파일 자체가 안 된다. null을 입력한 경우 `NullPointerException` 을 던져야 한다.

2. `compareTo` 는 각 필드의 동치 여부가 아닌 그 순서를 비교한다.

3. 객체 참조 필드를 비교하려면 `compareTo` 메서드를 재귀적으로 호출한다.

4. `Comparable` 을 구현하지 않은 필드나 표준이 아닌 순서로 비교해야 한다면 `Comparator` 를 대신 사용한다.

5. 자바 7부터는 기본 정수 타입을 비교할 때 관계 연산자 < 와 > 을 사용하지 않고 `compare` 를 사용한다.

6. 클래스에 핵심 필드가 여러 개라면 가장 핵심적인 필드부터 비교한다.

   : 순서가 결정되면 그 다음 필드를 비교할 필요없이 그 결과를 바로 반환한다.



### Comparator

자바 8부터는 `Comparator` 인터페이스가 많은 비교자 생성 메서드를 갖게 되었다. 간결하지만 약간의 성능 저하가 뒤따른다.

~~~ java
private static final Comparator<PhoneNumber> COMPARATOR =
    					// comparingInt(): 지정한 int 타입 키를 기준으로 순서를 정하는 비교자 반환
    					comparingInt((PhoneNumber pn) -> pn.areaCode)
       						 .thenComparingInt(pn -> pn.prefix)	// 지정한 키로 추가 비교 수행
    						 .thenComparingInt(pn -> pn.lineNum));

public int compareTo(PhoneNumber pn) {
    return COMPARATOR.compare(this, pn);
}
~~~