package c.string;

public class StringExtract {
    public static void main(String []args) {
        StringExtract sample = new StringExtract();

        // sample.checkCharAt();
        // sample.checkCopyValueOf();
        // sample.checkToCharArray();
        // sample.checkSubstring();
        sample.checkSplit();
    }

    public void checkCharAt() {
        String text = "Java technology is both a programming language and a platform.";

        System.out.println(text.charAt(text.lastIndexOf('a')));
    }

    public void checkCopyValueOf() {
        char []values = new char[]{'J', 'a', 'v', 'a'};
        String javaText = String.copyValueOf(values);
        System.out.println(javaText);
    }

    public void checkToCharArray() {
        String text = "Java technology is both a programming language and a platform.";
        char []charArray = text.toCharArray();

        for (char data:charArray) {
            System.out.print(data+" ");
        }
        System.out.println();
    }

    public void checkSubstring() {
        String text = "Java technology";
        String technology = text.substring(text.indexOf('t'));
        System.out.println(technology);

        String tech = text.substring(5, 9);
        System.out.println(tech);
    }

    public void checkSplit() {
        String text = "Java technology is both a programming language and a platform.";
        String []splitArray = text.split(" ");

        for (String data:splitArray) {
            System.out.println(data);
        }
    }
}