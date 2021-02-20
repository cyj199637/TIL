import java.util.HashSet;
import java.util.Random;

public class RandomNumberMaker {
    public static void main(String[] args) {
        RandomNumberMaker maker = new RandomNumberMaker();
        for (int loop=0; loop<10; loop++) {
            HashSet<Integer> set = maker.getSixNumber();
            System.out.println(set);
        }
    }

    public HashSet<Integer> getSixNumber() {
        HashSet<Integer> numbers = new HashSet<Integer>();
        Random random = new Random();
        while (numbers.size() < 6) {
            numbers.add(random.nextInt(45));
        }
        return numbers;
    }
}