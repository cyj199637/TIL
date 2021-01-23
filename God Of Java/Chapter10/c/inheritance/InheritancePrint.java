package c.inheritance;

public class InheritancePrint {
    public static void main(String []args) {
        Child child1 = new Child();
        child1.printName();

        ChildArg child2 = new ChildArg();
        child2.printName();
    }
}