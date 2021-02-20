public class CalculateSalary {
    public static void main(String []args) {
        CalculateSalary calculator = new CalculateSalary();
        calculator.calculateSalaries();
    }

    public long getSalaryIncrease(Employee employee) {
        int type = employee.getType();
        long salary = employee.getSalary();
        double increase;
        
        switch(type) {
            case 1:
                increase = salary * -0.95;
                return (long) (salary + increase);

            case 2:
                increase = salary * 0.1;
                return (long) (salary + increase);
            
            case 3:
                increase = salary * 0.2;
                return (long) (salary + increase);
            
            case 4:
                increase = salary * 0.3;
                return (long) (salary + increase);

            case 5:
                increase = salary * 1;
                return (long) (salary + increase);

            default:
                return salary;
        }
    }

    public void calculateSalaries() {
        Employee []employees = new Employee[]{
            new Employee("LeeDaeRi", 1, 1000000000),
            new Employee("KimManager", 2, 100000000),
            new Employee("WhangDesign", 3, 70000000),
            new Employee("ParkArchi", 4, 80000000),
            new Employee("LeeDevelop", 5, 60000000)
        };

        double newSalary;
        
        for (Employee one:employees) {
            newSalary = getSalaryIncrease(one);
            String name = one.getName();
            System.out.println(name+"="+newSalary);
        }
    }
}