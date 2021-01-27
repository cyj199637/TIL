package me.liiot.Level1;

/*
시저 암호
 */
public class CaesarPassword {

    public static void main(String[] args) {

        String s = "AaZz";
        int n = 25;

        System.out.println(solution(s, n));
    }

    private static String solution(String s, int n) {

        if (s==null || s.length()==0 || n==0) return s;

        char[] c = s.toCharArray();
        for (int i=0; i<c.length; i++) {
            if (c[i] != ' ') {
                int before = (int) c[i];
                int after = before + n;
                if ((before >= 'A' && before <= 'Z' && after > 'Z') || after > 'z')
                    after -= 26;
                c[i] = (char) after;
            }
        }

        return String.valueOf(c);
    }
}
