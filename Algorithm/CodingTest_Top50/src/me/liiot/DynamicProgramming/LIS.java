package me.liiot.DynamicProgramming;

import java.util.Arrays;

/*
42) Longest Increasing Subsequence
주어진 배열에서 증가하는 수로만 이루어진 가장 긴 부분 배열의 길이를 구하세요.
부분 배열에 들어있는 원소는 원본 배열에서 연속적이지 않아도 됩니다.
부분 배열에서 가장 큰 원소와 같은 값을 가지는 원소는 부분 배열에서 제외합니다.

- 이 문제는 다음과 같은 원리이다.
 seq[i]가 부분 배열에서 가장 큰 원소라고 했을 때, 그 부분 배열의 길이는
 (seq[i]보다 다음으로 큰 원소가 가장 큰 원소라고 가정했을 때의 부분 배열 길이 + 1)이다.
 */
public class LIS {

    public static void main(String[] args) {

        int[] seq = {1, 2, 3, 2, 5, 2, 6, 10, 4, 12};
        int[] seq2 = {9, 11, 2, 8, 4, 7, 28, 15};

        System.out.println(solve(seq));
        System.out.println(solve(seq2));
    }

    private static int solve(int[] seq) {

        if (seq==null || seq.length==0) return 0;

        int[] length = new int[seq.length];
        Arrays.fill(length, 1);
        int result = 1;

        for (int i=1; i<seq.length; i++) {
            for (int j=0; j<i; j++) {
                // seq[i]가 가장 큰 원소여야 하기 때문에 seq[i]보다 작은 값만 조건을 통과해야 한다.
                if (seq[j] < seq[i]) {
                    length[i] = Math.max(length[j]+1, length[i]);
                }
            }
            result = Math.max(result, length[i]);
        }

        return result;
    }
}
