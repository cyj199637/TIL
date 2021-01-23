public class OperatorIncrement {
    public static void main(String [] args) {
        OperatorIncrement sample=new OperatorIncrement();
        sample.increment();
    }

    public void increment() {
        int intValue=1;
        System.out.println(intValue++);
        System.out.println(intValue);
        System.out.println(++intValue);
    }
}