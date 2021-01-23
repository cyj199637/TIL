public class InterestManager {
    public static void main(String args[]) {
        InterestManager manager = new InterestManager();

        for (int day=10; day<=365; day+=10) {
            double amount = manager.calculateAmount(day, 1000000);
            System.out.println("Day " + day + ": " + amount);
        }
    }

    public double getInterestRate(int day) {
        double interestRate;
        if (day <= 90) {
            interestRate = 0.005;
        } else if (day <= 180) {
            interestRate = 0.01;
        } else if (day <= 364) {
            interestRate = 0.02;
        } else {
            interestRate = 0.056;
        }
        return interestRate;
    }

    public double calculateAmount(int day, long amount) {
        double totalAmount;
        double interestRate = getInterestRate(day);
        double interest = interestRate * amount;
        totalAmount = amount + interest;
        return totalAmount;
    }
}