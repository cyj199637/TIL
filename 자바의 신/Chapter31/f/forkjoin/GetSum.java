package f.forkjoin;

import java.util.concurrent.RecursiveTask;

public class GetSum extends RecursiveTask<Long> {
    long from, to;
    
    public GetSum(long from, long to) {
        this.from = from;
        this.to = to;
    }

    public Long compute() {
        long gap = to - from;

        if (gap <= 3) {
            long tempSum = 0;
            for (long loop=from; loop<=to; loop++) {
                tempSum += loop;
            }
            return tempSum;
        }

        long middle = (from + to) / 2;
        GetSum sumPre = new GetSum(from, middle);
        sumPre.fork();
        GetSum sumPost = new GetSum(middle+1, to);
        return sumPost.compute() + sumPre.join();
    }
}