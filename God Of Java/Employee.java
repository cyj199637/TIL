public class Employee {
    private String name;
    private int type;
    private long salary;

    public Employee(String name, int type, long salary) {
        this.name = name;
        this.type = type;
        this.salary = salary;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }
    public int getType() {
        return type;
    }
    public long getSalary() {
        return salary;
    }
}