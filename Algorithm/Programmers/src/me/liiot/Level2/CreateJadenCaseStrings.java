package me.liiot.Level2;

/*
JadenCase 문자열 만들기
*/
public class CreateJadenCaseStrings {

    public static void main(String[] args) {

        System.out.println(solution(" Hello M Y Friend "));
    }

    public static String solution(String s) {

        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.equals("")) {
                String lower = word.toLowerCase();
                char first = lower.charAt(0);
                if (first >= 97 && first <= 122) {
                    sb.append((char) (first - 32));
                    sb.append(lower.substring(1));
                } else {
                    sb.append(lower);
                }
            }
            sb.append(" ");
        }
        if (s.charAt(s.length()-1) != ' ')
            sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }
}
