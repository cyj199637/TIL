
public class UseStringMethods {
    public static void main(String []args) {
        UseStringMethods sample = new UseStringMethods();
        String str = "The String class represents character strings.";
        String findStr = "string";

        // sample.printWords(str);
        // sample.findString(str, findStr);
        // sample.findAnyCaseString(str, findStr);
        // sample.countChar(str, 's');
        sample.printContainWords(str, "ss");
    }

    public void printWords(String str) {
        String []stringArray = str.split(" ");

        for (String string:stringArray) {
            System.out.println(string);
        }
    }

    public void findString(String str, String findStr) {
        int index = str.indexOf(findStr);
        if (index != -1) {
            System.out.println(findStr+" is appeared at "+index);
        }
    }

    public void findAnyCaseString(String str, String findStr) {
        // int index = str.toLowerCase().indexOf(findStr);
        String lowerStr = str.toLowerCase();
        String lowerFindStr = findStr.toLowerCase();
        int index = lowerStr.indexOf(lowerFindStr);

        if (index != -1) {
            System.out.println(findStr+" is appeared at "+index);
        }
    }

    public void countChar(String str, char c) {
        char []charArray = str.toCharArray();
        int charCount = 0;

        for (char ch:charArray) {
            if (ch == c) {
                charCount++;
            }
        }

        System.out.println("char '"+c+"' count is "+charCount);
    }

    public void printContainWords(String str, String findStr) {
        String []stringArray = str.split(" ");
        for (String string:stringArray) {
            if (string.contains(findStr)) {
                System.out.println(string+" contains "+findStr);
            }
        }
    }
}