package me.liiot.Level2;

import java.util.Arrays;

/*
H-Index
 */
public class HIndex {

    public static void main(String[] args) {

        int[] citations = {4, 4, 4};
        System.out.println(solution(citations));
    }

    public static int solution(int[] citations) {

        Arrays.sort(citations);
        int count = 0;
        int max = citations[citations.length-1];
        int i = citations.length-1;
        while (max >= 0) {
            if (i >= 0 && citations[i] >= max) {
                count++;
                i--;
            } else {
                max--;
            }

            if (count >= max) {
                return max;
            }
        }

        return 0;
    }
}
