package e.thread;

public class RunEndlessThread {
    public static void main(String[] args) {
        RunEndlessThread sample = new RunEndlessThread();
        sample.endless();
    }

    public void endless() {
        EndlessThread thread = new EndlessThread();
        thread.start();
    }
}