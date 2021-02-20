package d.generic;

public class Car {
    protected String name;
    public Car(String name) {
        this.name = name;
    }
    public String toString() {
        return "Car name = "+name;
    }
}