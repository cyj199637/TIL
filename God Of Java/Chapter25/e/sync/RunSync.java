package e.sync;

public class RunSync {
    public static void main(String[] args) {
        RunSync runSync = new RunSync();
        runSync.runCommonCalculate();
    }

    public void runCommonCalculate() {
        CommonCalculate calc = new CommonCalculate();
        ModifyAmountThread thread1 = new ModifyAmountThread(calc, true);
        ModifyAmountThread thread2 = new ModifyAmountThread(calc, true);
        ModifyAmountThread thread3 = new ModifyAmountThread(calc, false);
        ModifyAmountThread thread4 = new ModifyAmountThread(calc, false);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            System.out.println("Final value is "+calc.getAmount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}