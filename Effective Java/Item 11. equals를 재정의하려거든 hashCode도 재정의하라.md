## Item 11. equals를 재정의하려거든 hashCode도 재정의하라

`**equals` 를 재정의한 클래스 모두에서 `hashCode` 도 재정의해야 한다.** 그렇지 않으면 `hashCode` 일반 규약을 어기게 되어 해당 클래스의 인스턴스를 `HashMap` 이나 `HashSet` 같은 컬렉션의 원소로 사용할 때 문제를 일으키기 때문이다.

다음은 Object 명세에서 발췌한 규약이다.

> * `equals` 비교에 사용되는 정보가 변경되지 않았다면, 애플리케이션이 실행되는 동안 그 객체의 `hashCode` 는 몇 번을 호출해도 일관되게 항상 같은 값을 반환해야 한다. 단, 애플리케이션을 다시 실행한다면 이 값이 달라져도 상관없다.
> * `equals(Object)` 가 두 객체를 같다고 판단했다면, 두 객체의 `hashCode` 는 똑같은 값을 반환해야 한다.
> * `equals(Object)` 가 두 객체를 다르다고 판단했더라도, 두 객체의 `hashCode` 가 서로 다른 값을 반환할 필요는 없다. 단, 다른 객체에 대해서는 다른 값을 반환해야 해시테이블의 성능이 좋아진다.

`hashCode` 재정의를 잘못했을 때 큰 문제가 되는 조항은 두 번째다. **즉, 논리적으로 같은 객체는 같은 해시코드를 반환해야 한다.** `equals` 는 물리적으로 다른 두 객체를 논리적으로 같다고 할 수 있다. 하지만 재정의하지 않은 Object의 기본 `hashCode`는 이 둘이 전혀 다르다고 판단하여, 규약과 달리 서로 다른 값을 반환한다.



### 올바르게 hashCode를 작성하는 방법

#### 1. int 변수 result를 선언한 후 해당 객체의 첫 번째 핵심 필드의 해시코드 c를 계산한 값으로 초기화한다.



#### 2. 해당 객체의 나머지 핵심 필드도 각각 해시코드 c를 계산하고, 계산된 해시코드 값으로 result를 갱신한다.

`result = 31 * result + c;`

필드를 곱하는 순서에 따라 result 값이 달라지게 하며, 이는 클래스에 비슷한 필드가 여러 개일 때 해시 효과를 크게 높여준다. 곱할 숫자를 31로 정한 이유는 31이 홀수이면서 소수이기 때문이다.

파생 필드는 해시코드 계산에서 제외해도 된다. 또한 `equals` 비교에 사용되지 않은 필드는 반드시 제외해야 한다. 그렇지 않으면 두 번째 `hashCode` 규약을 어기게 된다.

**성능을 높이기 위해 핵심 필드를 생략해서는 안 된다.** 해시 품지링 나빠져 해시테이블의 성능을 매우 떨어뜨릴 수 있다. 만약 인스턴스들의 해시코드를 넓게 퍼트려주는 필드를 생략한다면 수많은 인스턴스가 소수의 해시코드로 집중되어 해시테이블의 속도가 선형으로 느려질 것이다.



#### 3. result를 반환한다.

~~~ java
@Override
public int hashCode() {
    int result = Short.hashCode(areaCode);
    result = 31 * result + Short.hashCode(prefix);
    result = 31 * result + Short.hashCode(lineNum);
    return result
}
~~~



### Objects.hash()

Objects 클래스는 임의 개수만큼 객체를 받아 해시코드를 계산해주는 정적 메소드 `hash` 를 제공한다. 이 메소드로 위의 방법과 비슷한 `hashCode` 를 간단하게 작성할 수 있지만 속도는 더 느리다. 입력 인수를 담기 위한 배열이 만들어지고, 그 중 기본 타입이 있다면 박싱과 언박싱도 거쳐야 하기 때문이다. 따라서, `hash` 는 성능에 민감하지 않을 때만 사용해야 한다.

~~~ java
@Override
public int hashCode() {
    return Objects.hash(lineNum, prefix, areaCode);
}
~~~



### 지연 초기화 전략

클래스가 불변이고 해시코드를 계산하는 비용이 크다면, 캐싱하는 방식을 고려해야 한다. 해당 클래스가 해시 키로 자주 사용될 것 같다면 인스턴스가 만들어질 때 해시코드를 계산해둬야 한다. 그렇지 않은 경우라면 지연 초기화 전략을 고려할 수 있지만 스레드를 안전하게 만들도록 신경 써야 한다.

~~~ java
private int hashCode; //자동으로 0으로 초기화된다.

@Override
public int hashCode() {
    int result = hashCode;
    if (result == 0) {
        result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        hashCode = result;
    }
    return result;
}
~~~



**`hashCode` 가 반환하는 값의 생성 규칙을 API 사용자에게 자세히 알려주지 않는 것이 좋다. 그래야 클라이언트가 이 값에 의지하지 않게 되고, 추후에 계산 방식을 바꿀 수도 있다.**



### 실무 권장

`hashcode` 를 직접 작성하고 테스트하는 것보다 `HashCodeBuilder` 나 lombok 의 `@EqualsAndHashCode` 등을 이용해  `hashcode` 를 재정의하는 것이 좋다.