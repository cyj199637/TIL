package lambda;

public class RunnableSample {
    public static void main(String[] args) {
        RunnableSample sample = new RunnableSample();
        sample.runLambdaThread();
    }

    private void runCommonThread() {
        Runnable runnable = new Runnable(){
        
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        new Thread(runnable).start();
    }

    private void runLambdaThread() {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}