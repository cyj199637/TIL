public class Operators {
    public static void main(String [] args) {
        Operators sample=new Operators();
        sample.additive();
        sample.multiplicative();
        sample.divideInt();
        sample.remainder();
    }

    public void additive() {
        int intValue1=5;
        int intValue2=10;
        int result=intValue1+intValue2;
        System.out.println(result);
        result=intValue2-intValue1;
        System.out.println(result);
    }

    public void multiplicative() {
        int intValue1=5;
        int intValue2=10;
        int result=intValue1*intValue2;
        System.out.println(result);
        result=intValue2/intValue1;
        System.out.println(result);
    }

    public void divideInt() {
        double doubleValue1=5;
        double doubleValue2=10;
        double result=doubleValue1/doubleValue2;
        System.out.println(result);
    }

    public void remainder() {
        int intValue1=53;
        int intValue2=10;
        int result=intValue1%intValue2;
        System.out.println(result);
    }
}