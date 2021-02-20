import java.util.Date;

public class TimerThread extends Thread{
    public void run() {
        // printCurrentTime1();
        printCurrentTime2();
    }

    public void printCurrentTime1() {
        Date date = new Date();

        try {
            for (int loop=0; loop<10; loop++) {
                System.out.println(date.toString()+" "+System.currentTimeMillis());
                Thread.sleep(1000);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public void reduceTimeGap() {
        long current = System.currentTimeMillis();
        long timeMod = current % 1000;
        try {
            Thread.sleep(1000-timeMod);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public void printCurrentTime2() {
        Date date = new Date();

        try {
            for (int loop=0; loop<10; loop++) {
                System.out.println(date.toString()+" "+System.currentTimeMillis());
                Thread.sleep(900);
                reduceTimeGap();
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}