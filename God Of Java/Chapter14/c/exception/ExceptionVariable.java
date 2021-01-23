package c.exception;

public class ExceptionVariable {
    public static void main(String []args) {
        ExceptionVariable sample = new ExceptionVariable();
        // sample.checkVariable();
        sample.checkVariable2();
    }

    public void checkVariable() {
        int []intArray = new int[5];
        try {
            System.out.println(intArray[5]);
        } catch (Exception e) {
            System.err.println(intArray.length);
        }
        System.out.println("This code must run.");
    }

    public void checkVariable2() {
        int []intArray = null;
        try {
            intArray = new int[5];
            System.out.println(intArray[5]);
        } catch (Exception e) {
            System.err.println(intArray.length);
        }
        System.out.println("This code must run.");
    }
}