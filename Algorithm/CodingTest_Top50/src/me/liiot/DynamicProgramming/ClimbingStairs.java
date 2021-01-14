package me.liiot.DynamicProgramming;

/*
40) Climbing Stairs
당신은 계단을 오르고 있습니다. 정상에 도달하려면 n개의 계단이 필요합니다.
매번 1 계단 또는 2 계단씩 오를 수 있습니다. 얼마나 많은 방법으로 정상에 오를 수 있습니까?
참고 : 주어진 n은 양의 정수입니다.

- n 계단까지의 방법의 수 = (n-1) 계단까지의 방법의 수 + (n-2) 계단까지의 방법의 수
 */
public class ClimbingStairs {
    public static void main(String[] args) {

        int n = 6;

        System.out.println(solve(n));
    }

    private static int solve(int n) {

        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        return solve(n-1) + solve(n-2);
    }
}
