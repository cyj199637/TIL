package me.liiot.StringAndArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
15) Find All Anagrams
문자열 s와 문자열 p가 주어질 때, s에서 p의 모든 아나그램의 시작 인덱스를 찾습니다.
문자열은 소문자로만 구성되며 두 문자열 s와 p의 길이는 20, 100보다 크지 않습니다.
출력 순서는 중요하지 않습니다.

- 이 문제에서는 출력 순서에 상관없다고 했기 때문에 p를 구성하는 문자가 모두 있는지 없는지만 확인하면 된다.
 */
public class FindAllAnagrams {

    public static void main(String[] args) {

        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> result = solve(s, p);

        for (int i=0; i<result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
        System.out.println();

        List<Integer> result2 = solve2(s, p);

        for (int i=0; i<result2.size(); i++) {
            System.out.print(result2.get(i) + " ");
        }
        System.out.println();
    }

    private static List<Integer> solve(String s, String p) {

        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() ==0 || p == null || p.length() == 0 || s.length() < p.length()) return result;

        // 아스키 코드로 변환하여 각 값과 일치하는 인덱스에 각각의 개수를 저장 -> 순서와 상관없이 비교 가능
        int[] asciiP = new int[256];
        for (int i=0; i<p.length(); i++) {
            asciiP[p.charAt(i)]++;
        }

        // p의 아나그램을 찾는 것이므로 s에 남아있는 문자열이 p의 길이보다 작으면 안됨
        for (int i=0; i<s.length()-p.length(); i++) {
            int[] asciiS = new int[256];
            for (int j=0; j<p.length(); j++) {
                asciiS[s.charAt(i+j)]++;
            }
            if (Arrays.equals(asciiP, asciiS)) {
                result.add(i);
            }
        }

        return result;
    }

    private static List<Integer> solve2(String s, String p) {

        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() ==0 || p == null || p.length() == 0 || s.length() < p.length()) return result;

        char[] arrayP = p.toCharArray();
        Arrays.sort(arrayP);
        char[] arrayS = s.toCharArray();

        // p의 아나그램을 찾는 것이므로 s에 남아있는 문자열이 p의 길이보다 작으면 안됨
        for (int i=0; i<s.length()-p.length(); i++) {
            char[] subarray = Arrays.copyOfRange(arrayS, i, (i+p.length()));
            Arrays.sort(subarray);
            if (Arrays.equals(arrayP, subarray)) {
                result.add(i);
            }
        }

        return result;
    }
}
