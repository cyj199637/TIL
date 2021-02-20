package e.thread;

public class RunMultiThreads {
    public static void main(String[] args) {
        RunMultiThreads sample = new RunMultiThreads();
        sample.RunMultiThreads();
    }

    public void RunMultiThreads() {
        RunnableSample[] runnables = new RunnableSample[5];
        ThreadSample[] threads = new ThreadSample[5];

        for (int loop=0; loop<5; loop++) {
            runnables[loop] = new RunnableSample();
            threads[loop] = new ThreadSample();

            new Thread(runnables[loop]).start();
            threads[loop].start();
        }
        System.out.println("RunMultiThreads.runMultiThread() method is ended.");
    }
}