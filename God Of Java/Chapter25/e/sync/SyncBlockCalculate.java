package e.sync;

public class SyncBlockCalculate {
    private int amount;
    private int interest;

    Object amountLock = new Object();
    Object interestLock = new Object();

    public SyncBlockCalculate() {
        amount = 0;
    }

    public synchronized void plus(int value) {
        synchronized(amountLock) {
            amount += value;
        }
    }

    public synchronized void minus(int value) {
        synchronized(amountLock) {
            amount -= value;
        }
    }

    public void addInterest(int value) {
        synchronized(interestLock) {
            interest += value;
        }
    }

    public int getAmount() {
        return amount;
    }
}