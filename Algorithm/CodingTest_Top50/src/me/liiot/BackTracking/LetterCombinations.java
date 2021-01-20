package me.liiot.BackTracking;

import java.util.ArrayList;
import java.util.List;

/*
46) Letter Combinations of Phonenumber
주어진 숫자 문자열의 한 숫자당 주어진 문자열 배열의 인덱스를 가리킵니다.
지정된 인덱스의 문자열 간 만들 수 있는 두 개의 문자 조합을 모두 구하세요.
 */
public class LetterCombinations {

    public static void main(String[] args) {

        String digit = "23";
        String[] digitletter = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        List<String> result = solve(digit, digitletter);
        for (String s : result) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    private static List<String> solve(String digit, String[] digitletter) {

        List<String> result = new ArrayList<>();
        if (digitletter==null | digitletter.length==0)
            return result;

        result.add("");
        for (int i=0; i<digit.length(); i++) {
            result = combine(result, digitletter[digit.charAt(i) - '0']);
        }

        return result;
    }

    private static List<String> combine(List<String> outer, String s) {

        List<String> result = new ArrayList<>();
        for (int i=0; i<s.length(); i++) {
            for (String outerStr : outer) {
                result.add(outerStr + s.charAt(i));
            }
        }
        return result;
    }
}
