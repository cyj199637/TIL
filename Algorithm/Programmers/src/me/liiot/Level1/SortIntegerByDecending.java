package me.liiot.Level1;

import java.util.*;

/*
정수 내림차순으로 정렬하기
 */
public class SortIntegerByDecending {

    public static void main(String[] args) {

        System.out.println(solution(118372));
    }

    private static long solution(long n) {

        String[] split = String.valueOf(n).split("");
        Arrays.sort(split, Collections.reverseOrder());
        String result = "";
        for (String s : split) {
            result += s;
        }

        return Long.valueOf(result);
    }
}
