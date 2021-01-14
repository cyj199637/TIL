package me.liiot.DynamicProgramming;

/*
39) Unique Paths
로봇은 m x n 그리드의 왼쪽 상단 모서리에 있습니다 (아래 다이어그램에서 '시작'으로 표시됨).
로봇은 아래 또는 오른쪽으로만 이동할 수 있습니다.
로봇이 그리드의 오른쪽 하단 모서리에 도달하려고합니다 (아래 다이어그램에서 '완료'로 표시됨) /
가능한 고유 경로는 몇 개입니까?
 */
public class UniquePaths {

    public static void main(String[] args) {

        int m = 7;
        int n = 3;

        System.out.println(solve(m, n));
    }

    private static int solve(int m, int n) {

        if (m==0 || n==0) return 0;

        int[][] grid = new int[m][n];

        for (int i=0; i<n; i++) {
            grid[0][i] = 1;
        }

        for (int i=0; i<m; i++) {
            grid[i][0] = 1;
        }

        // [0, i] 와 [j, 0] 은 위에서 1로 이미 다 채웠으므로 제외
        for (int i=1; i<m; i++) {
            for (int j=1; j<n; j++) {
                grid[i][j] = grid[i-1][j] + grid[i][j-1];
            }
        }

        return grid[m-1][n-1];
    }
}
