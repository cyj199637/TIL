package me.liiot.DynamicProgramming;

/*
41) Coin Change
다른 종류의 동전과 총 금액이 주어집니다.
해당 금액을 구성하는데 필요한 최소 코인 수를 계산하는 함수를 작성하세요.
그 금액을 동전의 조합으로 만들 수 없으면 -1을 반환하세요.
 */
public class CoinChange {

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println(solve(coins, amount));
    }

    private static int solve(int[] coins, int amount) {

        return 0;
    }
}
