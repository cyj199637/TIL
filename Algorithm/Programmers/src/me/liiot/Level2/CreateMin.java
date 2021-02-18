package me.liiot.Level2;

import java.util.Arrays;

/*
최솟값 만들기
 */
public class CreateMin {

    public static void main(String[] args) {

        int[] a = {1, 2};
        int[] b = {3, 4};
        System.out.println(solution(a, b));
    }

    public static int solution(int[] a, int[] b) {

        Arrays.sort(a);
        Arrays.sort(b);
        int sum = 0;
        for (int i=0; i<a.length; i++) {
            sum += a[i] * b[b.length - 1 - i];
        }

        return sum;
    }
}
