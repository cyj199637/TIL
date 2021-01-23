package c.inheritance;

public class ChildOverridingPrivate extends ParentOverridingPrivate {
    public ChildOverridingPrivate() {
        System.out.println("ChildOverridingPrivate Cosntructor");
    }

    public void printName() {
        System.out.println("ChildOverridingPrivate printName()");
    }
}