public class SalaryManager {
    public static void main(String args[]) {
        SalaryManager sample=new SalaryManager();
        double realMonthlySalary=sample.getMonthlySalary(20000000);

        System.out.println(realMonthlySalary);
    }

    public double getMonthlySalary(int yearlySalary) {
        double months=12.0;
        double monthSalary=yearlySalary/months;
        double tax=calculateTax(monthSalary);
        double nationalPension=calculateNationalPension(monthSalary);
        double healthInsurance=calculateHealthInsurance(monthSalary);
        double sum=tax+nationalPension+healthInsurance;

        monthSalary-=sum;

        return monthSalary;
    }

    public double calculateTax(double monthSalary) {
        double percentOfTax=0.125;
        double tax=monthSalary*percentOfTax;
        System.out.println("Tax: "+tax);
        return tax;
    }

    public double calculateNationalPension(double monthSalary) {
        double percentOfNationalPension=0.081;
        double nationalPension=monthSalary*percentOfNationalPension;
        System.out.println("National Pension: "+nationalPension);
        return nationalPension;
    }

    public double calculateHealthInsurance(double monthSalary) {
        double percentOfHealthInsurance=0.135;
        double healthInsurance=monthSalary*percentOfHealthInsurance;
        System.out.println("Health Insurance: "+healthInsurance);
        return healthInsurance;
    }
}