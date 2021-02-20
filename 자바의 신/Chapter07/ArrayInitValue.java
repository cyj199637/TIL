public class ArrayInitValue {
    public static void main(String [] args) {
        ArrayInitValue array = new ArrayInitValue();
        // array.primitiveTypes();
        array.referenceTypes();
    }
    
    public void primitiveTypes() {
        byte [] byteArray = new byte[1];
        short [] shortArray = new short[1];
        int [] intArray = new int[1];
        long [] longArray = new long[1];
        float [] floatArray = new float[1];
        double [] doubleArray= new double[1];
        char [] charArray = new char[1];
        boolean [] booleanArray = new boolean[1];
        System.out.println("byteArray[0]=" + byteArray[0]);
        System.out.println("shortArray[0]=" + shortArray[0]);
        System.out.println("intArray[0]=" + intArray[0]);
        System.out.println("longArray[0]=" + longArray[0]);
        System.out.println("floatArray[0]=" + floatArray[0]);
        System.out.println("doubleArray[0]=" + doubleArray[0]);
        System.out.println("charArray[0]=[" + charArray[0] + "]");
        System.out.println("booleanArray[0]=" + booleanArray[0]);
    }

    public void referenceTypes() {
        String [] strings = new String[2];
        ArrayInitValue [] array = new ArrayInitValue[2];
        strings[0] = "Please visit www.GodOfJava.com.";
        array[0] = new ArrayInitValue();
        System.out.println("Strings[0]=" + strings[0]);
        System.out.println("Strings[1]=" + strings[1]);
        System.out.println("array[0]=" + array[0]);
        System.out.println("array[1]=" + array[1]);
    }
}