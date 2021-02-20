package c.string;

public class StringChange {
    public static void main(String []args) {
        StringChange sample = new StringChange();
        // sample.checkTrim();
        // sample.checkReplace();
        sample.checkFormat();
    }

    public void checkTrim() {
        String []strings = new String[]{
            " a", " b ", "     c", "d    ", "e   f", "   ",
        };

        for (String data:strings) {
            System.out.print("["+data+"] ");
            System.out.println("["+data.trim()+"]");
        }

        String text = " a ";
        if (text != null && text.trim().length() > 0) {
            System.out.println("OK");
        }
    }

    public void checkReplace() {
        String text = "The String class represents character strings.";

        System.out.println(text.replace('s', 'z'));
        System.out.println(text);
        System.out.println(text.replace("tring", "trike"));
        System.out.println(text.replaceAll(" ", "|"));
        System.out.println(text.replaceFirst(" ", "|"));
    }

    public void checkFormat() {
        String text = "제 이름은 %s입니다. 지금까지 %d권의 책을 썼고, 하루에 %f%%의 시간을 책을 쓰는데 할애하고 있습니다.";
        String realText = String.format(text, "Lora", 0, 0.0);
        System.out.println(realText);
    }
}