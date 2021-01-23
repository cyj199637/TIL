package c.inheritance;

public class ChildArg extends ParentArg {
    public ChildArg() {
        // super("ChildArg");
        super(new InheritancePrint());
        System.out.println("Child Constructor");
    }
}