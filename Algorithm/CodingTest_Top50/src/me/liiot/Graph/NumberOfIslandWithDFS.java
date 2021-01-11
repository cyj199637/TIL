package me.liiot.Graph;

/*
27) Number Of Island with DFS
'1'(토지)과 '0'(물)로 이루어진 그리드 맵이 주어졌을 때 섬의 수를 반환하세요.
섬은 물로 둘러싸여 있으며 인접한 땅을 수평 또는 수직으로 연결하여 형성됩니다.
그리드 맵의 네 가장자리는 모두 물로 둘러싸여 있다고 가정합니다.
 */
public class NumberOfIslandWithDFS {

    public static void main(String[] args) {

        int[][] map = {{1, 1, 1, 0, 1},
                       {1, 1, 0, 0, 0},
                       {1, 1, 0, 0, 1},
                       {0, 0, 0, 0, 1}};

        System.out.println(solve(map));

        int[][] map2 = {{1, 1, 0, 0, 0},
                        {1, 1, 0, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 0, 1, 1}};

        System.out.println(solve(map2));
    }

    private static int solve(int[][] map) {

        int result = 0;
        if (map == null || map.length == 0 || map[0].length == 0)
            return result;

        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map[0].length; j++) {
                if (map[i][j] == 1) {   // 섬의 시작점을 발견
                    result++;
                    dfs(map, i, j);     // 시작점을 기준으로 사방에 다른 육지가 있는지 탐색
                }
            }
        }

        print(map);

        return result;
    }

    private static void dfs(int[][] map, int i, int j) {

        int row = map.length;
        int col = map[0].length;

        // 지도에 벗어나거나 이미 탐색된 육지인 경우에는 탐색하지 않음음
       if (i<0 || i>=row || j<0 || j>=col || map[i][j] != 1)
            return;

        map[i][j] = 9;      // 탐색된 육지는 다른 숫자로 표시

        // 해당 점을 기준으로 사방에 다른 육지가 있는지 탐색
        dfs(map, i-1, j);
        dfs(map, i+1, j);
        dfs(map, i, j-1);
        dfs(map, i, j+1);
    }

    private static void print(int[][] map) {

        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
