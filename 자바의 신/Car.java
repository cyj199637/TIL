public class Car {
    int speed;
    int distance;
    String color;

    public Car() {
    }
    public void speedUp() {
        speed += 5;
    }
    public void breakDown() {
        speed -= 10;
    }
    public int getCurrentSpeed() {
        return speed;
    }
}