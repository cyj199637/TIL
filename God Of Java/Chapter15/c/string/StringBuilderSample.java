package c.string;

public class StringBuilderSample {
    public static void main(String []args) {
        StringBuilder builder = new StringBuilder("Hello");
        System.out.println(builder);

        builder.append(" World ").append(2020).append("!!!");
        System.out.println(builder);
    }
}