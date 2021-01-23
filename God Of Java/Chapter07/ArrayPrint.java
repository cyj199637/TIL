public class ArrayPrint {
    public static void main(String [] args) {
        ArrayPrint array = new ArrayPrint();
        // array.printString();
        array.printPrimitiveArray();
    }

    public void printString() {
        System.out.println("strings=" + new String[0]);
        System.out.println("array=" + new ArrayPrint[0]);
    }

    public void printPrimitiveArray() {
        System.out.println("byteArray=" + new byte[1]);
        System.out.println("shortArray=" + new short[1]);
        System.out.println("intArray=" + new int[1]);
        System.out.println("longArray=" + new long[1]);
        System.out.println("floatArray=" + new float[1]);
        System.out.println("doubleArray=" + new double[1]);
        System.out.println("charArray=" + new char[1]);
        System.out.println("booleanArray=" + new boolean[1]);
    }
}