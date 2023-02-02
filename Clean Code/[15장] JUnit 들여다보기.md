# 15장. JUnit 들여다보기

의도를 명확히 표현하려면 조건문을 캡슐화해야 한다.

즉 조건문을 메서드로 뽑아내 적절한 이름을 붙인다.

ex)

```java
// Before
public String compact(String message) {
    if (expected == null || actual == null || areStringsEqual())
        return Assert.format(message, expected, actual);
		
    ...
}

// After
public String compact(String message) {
    if (shouldNotCompact())
        return Assert.format(message, expected, actual);
		
    ...
}

private boolean shouldNotCompact() {
    return expected == null || actual == null || areStringsEqual()
}
```

<br/>

함수에서 멤버 변수와 이름이 똑같은 변수를 사용하지 말자. 이름은 명확하게 붙인다.

<br/>

부정문은 긍정문보다 이해하기 약간 더 어렵다. 따라서 웬만하면 if 조건문은 긍정문으로 작성한다.

```java
// Before
public String compact(String message) {
    if (expected == null || actual == null || areStringsEqual())
        return Assert.format(message, expected, actual);
		
    ...
}

// After
public String compact(String message) {
    if (canBeCompacted())
        return Assert.format(message, expected, actual);
		
    ...
}

private boolean canBeCompacted() {
    return expected != null && actual != null && !areStringsEqual()
}
```

<br/>

함수 이름에 인수를 고려하면 가독성이 훨씬 더 좋아진다.

<br/>

함수 내에서도 최대한 의존성을 줄이도록 코드를 작성한다.

ex)

```java
// 상황: prefixIndex, suffixIndex가 인스턴스 변수로 정의되어 있음
private void compactExpectedAndActual() {
    prefixIndex = findCommonPrefix();
    suffixIndex = findCommonSuffix();
    ...
}

// Before -> findCommonPrefix()가 prefixIndex를 먼저 계산하지 않으면 잘못된 결과가 나옴(숨겨진 시간적인 결합)
private int findCommonSuffix() {
    int expectedSuffix = expected.length() - 1;
    int actualSuffix = actual.length() - 1;
    for (; actualSuffix >= prefixIndex && expectedSuffix >= prefixIndex; actualSuffix--, expectedSuffix--) {
        if (expected.charAt(expectedSuffix) != actual.charAt(actualSuffix))
            break;
    }
		
    ...
}

// After
private int findCommonSuffix(int prefixIndex) {
    int expectedSuffix = expected.length() - 1;
    int actualSuffix = actual.length() - 1;
    for (; actualSuffix >= prefixIndex && expectedSuffix >= prefixIndex; actualSuffix--, expectedSuffix--) {
        if (expected.charAt(expectedSuffix) != actual.charAt(actualSuffix))
            break;
    }
		
    ...
}
```

그러나 이 방법도 함수 호출 순서는 정해지지만 prefixIndex가 필요한 이유는 명확하지 않다.

<br/>

따라서, 아래처럼 아예 명시적으로 `findCommonPrefix()` 함수를 내부에서 먼저 호출한다.

```java
// 상황: prefixIndex, suffixIndex가 인스턴스 변수로 정의되어 있음
private void compactExpectedAndActual() {
    findCommonPrefixAndSuffix()
    ...
}

private void findCommonPrefixAndSuffix() {
    findCommonPrefix();
    int expectedSuffix = expected.length() - 1;
    int actualSuffix = actual.length() - 1;
    for (; actualSuffix >= prefixIndex && expectedSuffix >= prefixIndex; actualSuffix--, expectedSuffix--) {
        if (expected.charAt(expectedSuffix) != actual.charAt(actualSuffix))
            break;
    }
		
    ...
}

private void findCommonPrefix() {
    ...
}
```

<br/>

ex) 동일한 로직의 boolean 반환 함수의 이름을 긍정/부정 둘 다 짓고 싶을 때

```java
private boolean shouldBeCompacted() {
    return !shouldNotBeCompacted();
}

private boolean shouldNotBeCompacted() {
    return expected == null || actual == null || areStringsEqual()
}
```

<br/>

모듈은 일련의 분석 함수와 일련의 조합 함수로 나뉜다.

전체 함수는 위상적으로 정렬했으므로 각 함수가 사용된 직후에 정의된다.

분석 함수가 먼저 나오고 조합 함수가 그 뒤를 이어서 나온다.