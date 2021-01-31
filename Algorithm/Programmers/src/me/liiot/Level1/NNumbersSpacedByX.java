package me.liiot.Level1;

/*
x만큼 간격이 있는 n개의 숫자
 */
public class NNumbersSpacedByX {

    public static void main(String[] args) {

        long[] result = solution(2, 5);
        for (long l : result) {
            System.out.print(l + " ");
        }
    }

    public static long[] solution(int x, int n) {

        long[] result = new long[n];
        long sum = x;
        for (int i=0; i<n; i++) {
            result[i] = sum;
            sum += x;
        }

        return result;
    }
}
