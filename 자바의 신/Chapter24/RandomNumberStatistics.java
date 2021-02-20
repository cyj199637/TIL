import java.util.Hashtable;
import java.util.Random;
import java.util.Set;
import java.util.Random;

public class RandomNumberStatistics {
    private final int DATA_BOUNDARY = 50;
    Hashtable<Integer, Integer> hashtable = new Hashtable<Integer, Integer>();

    public static void main(String[] args) {
        RandomNumberStatistics sample = new RandomNumberStatistics();
        sample.getRandomNumberStatistics();
    }

    public void getRandomNumberStatistics() {
        Random random = new Random();
        for (int loop=0; loop<5000; loop++) {
            int randomNumber = random.nextInt(DATA_BOUNDARY) + 1;
            putCurrentNumber(randomNumber);
        }
        printStatistics();
    }

    public void putCurrentNumber(int randomNumber) {
        if (hashtable.containsKey(randomNumber)) {
            hashtable.put(randomNumber, hashtable.get(randomNumber)+1);
        } else {
            hashtable.put(randomNumber, 1);
        }
    }

    public void printStatistics() {
        Set<Integer> keySet = hashtable.keySet();
        for (Integer tempKey: keySet) {
            System.out.print(tempKey+"="+hashtable.get(tempKey)+" ");
            if (tempKey % 10 == 1) {
                System.out.println();
            }
        }
    }
}