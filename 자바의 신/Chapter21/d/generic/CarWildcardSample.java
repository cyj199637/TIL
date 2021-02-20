package d.generic;

public class CarWildcardSample {
    public static void main(String []args) {
        CarWildcardSample sample = new CarWildcardSample();
        sample.callBoundedWildcardMethod();
    }
    public void callBoundedWildcardMethod() {
        WildcardGeneric<Car> wildcard = new WildcardGeneric<Car>();
        wildcard.setWildcard(new Car("Mustang"));
        boundedWildcardMethod(wildcard);

        WildcardGeneric<Bus> wildcard2 = new WildcardGeneric<Bus>();
        wildcard2.setWildcard(new Bus("M5422"));
        boundedWildcardMethod(wildcard2);
    }
    public void boundedWildcardMethod(WildcardGeneric<? extends Car> c) {
        Car value = c.getWildcard();
        System.out.println(value);
    }
}