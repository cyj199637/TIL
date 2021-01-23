package c.inheritance;

public class ToString {
    public static void main(String []args) {
        ToString thisObject = new ToString();
        // thisObject.toStringMethod(thisObject);
        thisObject.toStringMethod2();
    }

    public void toStringMethod(Object obj) {
        System.out.println(obj);
        System.out.println(obj.toString());
        System.out.println("plus "+obj);
    }

    public void toStringMethod2() {
        System.out.println(this);
        System.out.println(toString());
        System.out.println("plus "+this);
    }

    public String toString() {
        return "ToString class";
    }
}