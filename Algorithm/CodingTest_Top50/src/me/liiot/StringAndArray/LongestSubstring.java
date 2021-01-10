package me.liiot.StringAndArray;

import java.util.HashMap;
import java.util.Map;

/*
12) Longest Substring With At Most Two Distinct
String s가 주어졌을 때, s에서 최대 2개의 문자만 포함하면서 가장 긴 연속된 문자열 t의 길이를 찾으세요.
 */
public class LongestSubstring {

    public static void main(String[] args) {

        String s1 = "eceba";
        String s2 = "ccaabbb";
        String s3 = "bdkekefiee";

        System.out.println(solve(s1));
        System.out.println(solve(s2));
        System.out.println(solve(s3));
    }

    private static int solve(String s) {

        // counter: 문자열에 포함된 문자 개수
        int start = 0, end = 0, length = 0, counter = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (end<s.length()) {
            char endChar = s.charAt(end);
            map.put(endChar, (map.getOrDefault(endChar, 0) + 1));
            if (map.get(endChar) == 1) counter++;   // 새로운 문자가 추가되었다는 의미이므로 counter 증가
            end++;

            while (counter > 2) {
                char startChar = s.charAt(start);
                map.put(startChar, (map.get(startChar) - 1));   // 앞에서 세었던 문자를 제거
                if (map.get(startChar) == 0) counter--;     // 앞에서 센 문자를 다 제거하면 문자 개수 감소
                start++;
            }

            // 이전에 세었던 문자가 map에 없어도 나중에 저장된 문자가 포함된 문자열보다 길었다면 이 값은 갱신되지 않음
            // 문자열 자체를 리턴하는게 아니라 가장 긴 문자열의 길이만 리턴하면 되기 때문에 가능
            length = Math.max(length, end-start);
        }

        return length;
    }
}
