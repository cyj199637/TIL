package f.forkjoin;

import java.util.concurrent.RecursiveTask;

public class GetSum2 extends RecursiveTask<Long> {
    long from, to;
    
    public GetSum2(long from, long to) {
        this.from = from;
        this.to = to;
    }

    public Long compute() {
        long gap = to - from;

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log("From = "+from+" To = "+to);

        if (gap <= 3) {
            long tempSum = 0;
            for (long loop=from; loop<=to; loop++) {
                tempSum += loop;
            }
            log("Return!! "+from+" ~ "+to+" = "+tempSum);
            return tempSum;
        }

        long middle = (from + to) / 2;
        GetSum2 sumPre = new GetSum2(from, middle);
        log("Pre    From = "+from+" To = "+middle);
        sumPre.fork();
        GetSum2 sumPost = new GetSum2(middle+1, to);
        log("Post   From = "+(middle+1)+" To = "+to);
        return sumPost.compute() + sumPre.join();
    }

    public void log(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.println("["+threadName+"] "+message);
    }
}