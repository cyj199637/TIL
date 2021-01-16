package me.liiot.DynamicProgramming;

import java.util.Arrays;

/*
41) Coin Change
다른 종류의 동전과 총 금액이 주어집니다.
해당 금액을 구성하는데 필요한 최소 코인 수를 계산하는 함수를 작성하세요.
그 금액을 동전의 조합으로 만들 수 없으면 -1을 반환하세요.

ex)
 coins = [1, 2, 5]
          0  1   2   3   4   5   6   7   8   9   10  11
    dp = [0, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12 ,12]
result = [0, 1,  1,  2,  2,  1,  2,  2,  3,  3,  2,  3 ]

<주요 공식>
dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1)

dp[1] = Math.min(dp[1], dp[1 - coins[0]] + 1) = Math.min(12, dp[1 - 1] + 1) = 1

dp[2] = Math.min(dp[2], dp[2 - coins[0]] + 1) = Math.min(12, dp[2 - 1] + 1) = 2
dp[2] = Math.min(dp[2], dp[2 - coins[1]] + 1) = Math.min(2, dp[2 - 2] + 1) = 1
...
dp[7] = Math.min(dp[7], dp[7] - coins[0]] + 1) = Math.min(12, dp[7 - 1] + 1) = 3
dp[7] = Math.min(dp[7], dp[7] - coins[1]] + 1) = Math.min(3, dp[7 - 2] + 1) = 2
dp[7] = Math.min(dp[7], dp[7] - coins[2]] + 1) = Math.min(2, dp[7 - 5] + 1) = 2
...

- 이 문제는 대상 값이 주어졌을 때 DFS나 BFS처럼 처음부터 끝까지 모두 탐색하는 것이 아니다.
 탐색할 경우의 수가 주어진 동전 배열로 정해져있기 때문에 이 배열로 계산할 수 있는 경우만 탐색한다.
 이 문제에서는 동전 최소 개수를 구하는 것이다. 이를 역으로 이용해서 대상 값에서 동전 값을 한 번씩만 뺴고,
 계산한 값이 동전 값과 같지 않다면 다시 동전 값을 한 번씩 빼는 과정을 반복한다.
- 0번 방의 값은 0인 이유: 동전 값이 대상 값과 같다면 해당 대상 값에 대한 동전 조합은 1이어야 하기 때문이다.
                       다른 방처럼 똑같이 12였다면 13으로 나오기 때문에 최소 개수로 포함할 수 없다.
 */
public class CoinChange {

    public static void main(String[] args) {

        int[] coins = {3, 6};
        int amount = 11;

        System.out.println(solve(coins, amount));
    }

    private static int solve(int[] coins, int amount) {

        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i=1; i<max; i++) {
            for (int j=0; j<coins.length; j++) {
                if (i >= coins[j]) {    // i가 동전 값보다 작으면 인덱스가 음수로 나와 계산할 수 없으므로 조건문으로 걸러낸다.
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
