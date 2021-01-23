package c.inheritance;

public class ChildOverriding extends ParentOverriding {
    public ChildOverriding() {
        System.out.println("ChildOverriding Cosntructor");
    }

    public void printName() {
        System.out.println("ChildOverriding printName()");
    }

    /*
    public String printName() {
        System.out.println("ChildOverriding printName()");
        return "Success";
    }
    */

    /*
    private void printName() {
        System.out.println("ChildOverriding printName()");
    }
    */
}