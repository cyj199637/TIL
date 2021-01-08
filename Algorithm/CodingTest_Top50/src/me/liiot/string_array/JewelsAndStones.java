package me.liiot.string_array;

import java.util.HashSet;
import java.util.Set;

/*
07) Jewels and Stones
보석의 종류를 나타내는 문자열 J, 내가 보유한 스톤을 나타내는 문자열 S가 주어집니다.
S의 각 문자는 내가 보유한 스톤의 한 종류입니다. 당신은 얼마나 많은 스톤이 보석인지 알고 싶어 합니다.
J와 S의 문자는 대소문자를 구분하므로 "a"는 "A"와 다른 유형의 스톤으로 간주됩니다.
 */
public class JewelsAndStones {

    public static void main(String[] args) {
        String j = "aA";
        String s = "aCbbAdA";
        System.out.print(solve(j, s));
    }

    private static int solve(String j, String s) {

        int count = 0;

        Set<Character> jewel = new HashSet<>();
        char[] tempJ = j.toCharArray();
        for (char e : tempJ) {
            jewel.add(e);
        }

        char[] tempS = s.toCharArray();
        for (char e : tempS) {
            if (jewel.contains(e)) {
                count++;
            }
        }

        return count;
    }
}
