package f;

public class JDK7Switch {
    public static void main(String[] args) {
        JDK7Switch sample = new JDK7Switch();
        System.out.println(sample.salaryIncreaseAmount(3));
        System.out.println(sample.salaryIncreaseAmount("Engineer"));
    }

    public double salaryIncreaseAmount(int employeeLevel) {
        switch (employeeLevel) {
            case 1:
                return 10.0;
            case 2:
                return 15.0;
            case 3:
                return 100.0;
            case 4:
                return 20.0;
        }
        return 0.0;
    }

    public double salaryIncreaseAmount(String employeeLevel) {
        switch (employeeLevel) {
            case "CEO":
                return 10.0;
            case "Manager":
                return 15.0;
            case "Engineer":
            case "Developer":
                return 100.0;
            case "Designer":
            case "Planner":
                return 20.0;
        }
        return 0.0;
    }
}