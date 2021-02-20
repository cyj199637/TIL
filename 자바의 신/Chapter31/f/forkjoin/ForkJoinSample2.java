package f.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinSample2 {
    static final ForkJoinPool mainPool = new ForkJoinPool();

    public static void main(String[] args) {
        ForkJoinSample2 sample = new ForkJoinSample2();
        sample.calculate();
    }

    public void calculate() {
        long from = 0;
        long to = 10;

        GetSum2 sum = new GetSum2(from, to);
        Long result = mainPool.invoke(sum);
        System.out.println("Fork Join:Total sum of "+from+" ~ "+to+" = "+result);
    }
}