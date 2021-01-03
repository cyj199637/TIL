## Item 02. 생성자에 매개변수가 많다면 빌더를 고려하라

정적 팩토리 메소드와 public 생성자 모두 매개변수가 많이 필요한 경우에 불편해진다. 이런 경우, 다음과 같은 방법으로 해결한다.



### 1. 점층적 생성자 패턴

: 필수 매개변수만 받는 생성자 ~ 필수 매개변수와 선택 매개변수를 전부 받는 생성자까지 늘려가는 방식

- 매개변수 개수가 많아지면 클라이언트 코드를 작성하거나 읽기 어렵다. 
  - 코드를 읽을 때 각 값의 의미가 무엇인지 헷갈린다.
  - 매개변수가 몇 개인지도 주의해서 세어야 한다.
  - 타입이 같은 매개변수가 연달아 있으면 찾기 어려운 버그로 이어질 수 있다.
  - 클라이언트가 매개변수의 순서를 바꿔 건네줘도 컴파일러는 알아채지 못하므로 런타임에 엉뚱하게 동작하게 된다.



### 2. 자바빈즈 패턴

: 매개변수가 없는 생성자로 객체를 만든 후, `setter` 메서드들을 호출해 원하는 매개변수의 값을 설정하는 방식

- 코드가 길어지지만 인스턴스를 만들기 쉽고, 코드가 더 읽기 쉬워진다.
- 객체 하나를 만들려면 메서드를 여러 개 호출해야 한다.
- 객체가 완전히 생성되기 전까지는 일관성이 깨진 상태로 놓이게 된다.
  - 스레드 안전성을 얻으려면 추가 작업을 해야 한다.
- `getter` 와 `setter` 메서드들이 있으므로 클래스를 불변으로 만들 수 없게 된다.

위의 단점을 보완하고자 생성이 끝난 객체를 수동으로 얼리고, 얼리기 전에는 사용할 수 없도록 하기도 한다. 하지만 이 방법도 `freeze()` 메서드를 확실히 호출했는지 보장되지 않으므로 런타임 오류에 취약하다.



### 3. 빌더 패턴

: 클라이언트가 필요한 객체를 직접 만드는 대신, 필수 매개변수만으로 생성자나 정적 팩토리 메서드를 호출해 빌더 객체를 얻는다. 그 다음 빌더 객체가 제공하는 일종의 `setter` 메서드들로 원하는 선택 매개변수들을 설정한다. 마지막으로 매개변수가 없는 `build()` 메서드를 호출해 필요한 객체를 얻는다.

~~~ java
public class NutiritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;
    
    public static class Builder {
        // 필수 매개변수
        private final int servingSize;
        private final int servings;
        
        // 선택 매개변수 - 기본값으로 초기화
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;
        
        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }
        
        public Builder calories(int val) {
            calories = val;
            return this;
        }
        
        public Builder fat(int val) {
            fat = val;
            return this;
        }
        
        public Builder sodium(int val) {
            sodium = val;
            return this;
        }
        
        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }
        
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }
    
    public NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
~~~



~~~java
NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
    					.calories(100)
    					.sodium(35)
    					.carbohydrate(27)
    					.build();
~~~



빌더 패턴은 점층적 생성자 패턴의 안전성과 자바빈즈 패턴의 가독성을 모두 가지고 있다. 또한, 빌더의 생성자나 메소드에서 유효성 확인을 할 수도 있고 여러 매개변수를 혼합해서 확인해야 하는 경우에는 `build` 메소드에서 호출하는 생성자에서 할 수 있다. 빌더에서 매개변수를 객체로 복사해온 다음에 확인하고, 검증에 실패하면 어떤 매개변수가 잘못됐는지 알려주는 에러 메시지를 담아 `IllegalArgumentException`을 던진다.

✅빌더 패턴은 계층적으로 설계된 클래스와 함께 쓰기 좋다. 추상 빌더를 가지고 있는 추상 클래스를 만들고 하위 클래스에서는 추상 클래스를 상속받으며 각 하위 클래스용 빌더도 추상 빌더를 상속받아 만들 수 있다. 이때 추상 빌더는 `재귀적인 타입 매개변수`를 사용하고 `self`라는 메소드를 사용해 `self-type` 개념을 모방할 수 있다. 하위 클래스에서는 `build` 메소드의 리턴 타입으로 해당 하위 클래스 타입을 리턴하는 `Covariant 리턴 타이핑`을 사용하면 클라이언트 코드에서 타입 캐스팅을 할 필요가 없어진다.

✅빌더는 가변 인자 (vargars) 매개변수를 여러개 사용할 수 있다는 장점도 있다. (생성자나 팩토리 메서드는 가변인자를 맨 마지막 매개변수에 한 번 밖에 못 씀) 또한 토핑 예제에서 본것처럼 여러 메서드 호출을 통해 전달받은 매개변수를 모아 하나의 필드에 담는 것도 가능하다.

빌더는 꽤 유연해서 빌더 하나로 여러 객체를 생성할 수도 있고 만드는 객체에 시리얼 번호를 증가하는 식으로 매번 생성하는 객체를 조금씩 변화를 줄 수도 있다. 

단점으로 객체를 만들기 전에 먼저 빌더를 만들어야 하는데 성능에 민감한 상황에서는 그점이 문제가 될 수도 있다. 그리고 생성자를 사용하는 것보다 코드가 더 장황하다. 따라서 빌더 패턴은 매개변수가 많거나 또는 앞으로 늘어날 가능성이 있는 경우에 사용하는것이 좋다.