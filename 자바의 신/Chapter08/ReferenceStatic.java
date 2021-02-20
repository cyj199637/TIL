public class ReferenceStatic {
    public static String name = "Lora";

    public static void main(String [] args){
        ReferenceStatic.staticMethod();    
    }

    public static void staticMethod() {
        System.out.println("This is a staicMethod");
    }

    public static void staticMethodCallVariable() {
        System.out.println(name);
    }
}