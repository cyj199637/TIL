public class CarManager {
    public static void main(String [] args) {
        Car dogCar = new Car();
        Car cowCar = new Car();

        dogCar.speedUp();
        dogCar.speedUp();
        System.out.println(dogCar.getCurrentSpeed());
        dogCar.breakDown();
        System.out.println(dogCar.getCurrentSpeed());
    }
}