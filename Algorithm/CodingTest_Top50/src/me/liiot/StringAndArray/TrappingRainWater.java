package me.liiot.StringAndArray;

/*
18) Trapping Rain Water
위의 그래프에 그려진 검정색 블록은 벽을 의미합니다.
각 블록은 너비가 1이며, 위의 그래프는 [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1] 배열로 표시됩니다.
블록이 음이 아닌 정수 n개로 주어졌을 때, 비가 내린 후 가둘 수 있는 물의 양을 계산하세요.
예를 들어, 위 그래프는 6개의 빗물(파란색 부분)이 가둘 수 있습니다.

- 원리: 물을 채울 수 있는 경우는 두 가지이다. 왼쪽 벽과 오른쪽 벽 사이에 아무 블록도 없거나 블록이 놓여져 있거나.
1. 각 블록마다 가질 수 있는 왼쪽 벽과 오른쪽 벽의 최대 높이를 구한다.
    L = {0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3}
    R = {3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 1]
2. 왼쪽 벽과 오른쪽 벽 중 더 작은 크기를 고른다. 물을 가득 채워도 더 작은 쪽의 높이까지만 채울 수 있기 때문
    M = {0, 1, 1, 2, 2, 2, 2, 3, 2, 2, 2, 1}
3. 2번 값에서 각 블록 개수를 뺀다. 원래 블록이 놓여져 있다면 그만큼은 물로 채울 수 없기 때문
    W = {0, 0, 1, 0, 1, 2, 1, 0, 0, 1, 0, 0}
 */
public class TrappingRainWater {

    public static void main(String[] args) {

        int[] blocks = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        System.out.println(solve(blocks));
    }

    private static int solve(int[] blocks) {

        int result = 0;
        if (blocks == null || blocks.length <= 2)   // 벽 사이에 물을 채우려면 최소 3개의 수가 필요하기 때문
            return result;

        int[] left = new int[blocks.length];
        int[] right = new int[blocks.length];

        int max = blocks[0];
        left[0] = max;
        for (int i=1; i<blocks.length; i++) {
            if (max < blocks[i]) {
                left[i] = blocks[i];
                max = blocks[i];
            } else {
                left[i] = max;
            }
        }

        max = blocks[blocks.length-1];
        right[blocks.length-1] = max;
        for (int i=blocks.length-2; i>=0; i--) {
            if (max < blocks[i]) {
                right[i] = blocks[i];
                max = blocks[i];
            } else {
                right[i] = max;
            }
        }

        for (int i=0; i<left.length; i++) {
            int rain = Math.min(left[i], right[i]) - blocks[i];
            result += rain;
        }

        return result;
    }
}
