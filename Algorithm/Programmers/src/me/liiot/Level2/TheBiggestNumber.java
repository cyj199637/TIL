package me.liiot.Level2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

/*
가장 큰 수
 */
public class TheBiggestNumber {

    public static void main(String[] args) {

        int[] numbers = {0, 0, 0, 0, 0};
        System.out.println(solution(numbers));
    }

    public static String solution(int[] numbers) {

        int length = numbers.length;
        String[] strings = new String[length];
        for (int i=0; i<length; i++) {
            strings[i] = String.valueOf(numbers[i]);
        }

        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        };

        Arrays.sort(strings, comp);
        StringBuilder builder = new StringBuilder();
        for (String s : strings) {
            builder.append(s);
            if (Pattern.matches("0+", builder))
                return "0";
        }

        return builder.toString();
    }
}
