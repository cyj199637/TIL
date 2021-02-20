public class StaticBlock {
    static int data = 1;
    
    public StaticBlock() {
        System.out.println("StaticBlock Constructor");
    }

    static {
        System.out.println("*** First Static Block ***");
        data = 3;
    }

    static {
        System.out.println("*** Second Static Block ***");
        data = 5;
    }

    public static int getData() {
        return data;
    }
}