public class OperatorCasting {
    public static void main(String args[]) {
        OperatorCasting sample=new OperatorCasting();
        // sample.casting();
        sample.casting2();
    }

    public void casting() {
        byte byteValue=127;
        short shortValue=byteValue;

        shortValue++;
        System.out.println(shortValue);
        byteValue=(byte)shortValue;
        System.out.println(byteValue);
    }

    public void casting2() {
        short shortValue=256;
        byte byteValue=(byte)shortValue;
        System.out.println(byteValue);

        shortValue=255;
        byteValue=(byte)shortValue;
        System.out.println(byteValue);
    }
}