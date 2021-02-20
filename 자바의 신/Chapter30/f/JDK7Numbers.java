package f;

public class JDK7Numbers {
    public static void main(String[] args) {
        JDK7Numbers numbers = new JDK7Numbers();
        // numbers.jdk6();
        // numbers.jdk7();
        numbers.jdk7Underscore();
    }

    public void jdk6() {
        int decVal = 1106;
        int octVal = 02122;
        int hexVal = 0x452;
        System.out.println(decVal);
        System.out.println(octVal);
        System.out.println(hexVal);
    }

    public void jdk7() {
        int binaryVal = 0b10001010010;
        System.out.println(binaryVal);
    }

    public void jdk7Underscore() {
        int binaryVal = 0b0100_0101_0010;
        int million = 1_000_000;
        System.out.println(binaryVal);
        System.out.println(million);
    }
}