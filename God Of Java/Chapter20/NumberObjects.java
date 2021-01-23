public class NumberObjects {
    public static void main(String []args) {
        NumberObjects sample = new NumberObjects();
        sample.parseLong("1024");
        sample.printOtherBase(1024);
    }

    public long parseLong(String data) {
        long l = -1;

        try {
            l = Long.parseLong(data);
            System.out.println(l);
        } catch (NumberFormatException e) {
            System.out.println(data+" is not a number.");
        } catch (Exception e) {
        }

        return l;
    }

    public void printOtherBase(long value) {
        System.out.println("Original: "+value);
        System.out.println("Binary: "+Long.toBinaryString(value));
        System.out.println("Hex: "+Long.toHexString(value));
        System.out.println("Octal: "+Long.toOctalString(value));
    }
}