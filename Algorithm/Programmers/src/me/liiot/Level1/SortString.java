package me.liiot.Level1;

import java.util.*;

/*
문자열 내 맘대로 정렬하기
 */
public class SortString {

    public static void main(String[] args) {

        String[] strings = {"abce", "abcd", "cdx"};
        int n = 2;

        String[] result = solution(strings, n);
        for (String s : result) {
            System.out.print(s + " ");
        }
    }

    private static String[] solution(String[] strings, int n) {

        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                int result = o1.charAt(n) - o2.charAt(n);
                if (result == 0) {
//                    int i = 0;
//                    while (result == 0) {
//                        result = o1.charAt(i) - o2.charAt(i);
//                        i++;
//                    }
//                    return result;
                    return o1.compareTo(o2);
                }

                return result;
            }
        };

        Arrays.sort(strings, comp);

        return strings;
    }
}
