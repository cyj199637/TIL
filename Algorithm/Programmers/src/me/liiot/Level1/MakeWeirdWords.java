package me.liiot.Level1;

import java.util.Iterator;

/*
이상한 문자 만들기
 */
public class MakeWeirdWords {

    public static void main(String[] args) {

        String s = "try hello world";
        System.out.println(solution(s));
    }

    private static String solution(String s) {

        if (s==null || s.length()==0) return s;

        char[] c = s.toCharArray();
        int index = 0;
        for (int i=0; i<c.length; i++) {
            if (c[i] == ' ') {
                index = 0;
                continue;
            }

            c[i] = (index % 2) == 0 ? Character.toUpperCase(c[i]) : Character.toLowerCase(c[i]);
            index++;
        }

        return String.valueOf(c);
    }
}
