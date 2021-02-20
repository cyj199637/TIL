package c.inheritance;

public class ParentOverridingPrivate {
    public ParentOverridingPrivate() {
        System.out.println("ParentOverridingPrivate Cosntructor");
    }

    private void printName() {
        System.out.println("printName() - ParentOverridingPrivate ");
    }
}