package me.liiot.Level2;

import java.util.Arrays;
import java.util.Comparator;

/*
가장 큰 수
 */
public class TheBiggestNumber {

    public static void main(String[] args) {

        int[] numbers = {3, 30, 34, 5, 9};
        System.out.println(solution(numbers));
    }

    public static String solution(int[] numbers) {

        int length = numbers.length;
        String[] strings = new String[length];
        for (int i=0; i<length; i++) {
            strings[i] = String.valueOf(numbers[i]);
        }

        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                char[] c1 = String.valueOf(o1).toCharArray();
                char[] c2 = String.valueOf(o2).toCharArray();

                return 0;
            }
        };

        Arrays.sort(strings, Comparator.reverseOrder());
        StringBuilder builder = new StringBuilder();
        for (String s : strings) {
            builder.append(s);
        }

        return builder.toString();
    }
}
