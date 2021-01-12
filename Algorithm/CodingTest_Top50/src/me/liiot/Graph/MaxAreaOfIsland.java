package me.liiot.Graph;

/*
32) Max Area Of Island
'1'(토지)과 '0'(물)로 이루어진 그리드 맵이 주어졌을 때 섬의 최대 면적을 반환하세요.
섬은 물로 둘러싸여 있으며 인접한 땅을 수평 또는 수직으로 연결하여 형성됩니다.
그리드 맵의 네 가장자리는 모두 물로 둘러싸여 있다고 가정합니다.
 */
public class MaxAreaOfIsland {

    public static void main(String[] args) {

        int[][] map = {
                {1, 1, 0, 1, 1},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 0, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 1}
        };

        System.out.println(solve(map));
    }

    private static int solve(int[][] map) {

        if (map == null || map.length == 0)
            return 0;

        int max = 0;

        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map[0].length; j++) {
                if (map[i][j] == 1) {
                    int area = dfs(map, i, j, 0);
                    max = Math.max(max, area);
                }
            }
        }

        return max;
    }

    private static int dfs(int[][] map, int i, int j, int count) {

        int row = map.length;
        int col = map[0].length;

        if (i<0 || i>=row || j<0 || j>=col || map[i][j] != 1)   // 이 구문은 dfs()를 탈출하기 위한 조건이기 때문에
            return count;   // 만약 탈출하게 된다면 0이 아니라 지금까지 계산한 카운트 값을 리턴해줘야 함

        // 사방으로 돌리기 전에 9로 바꿔주지 않으면 다음과 같은 문제가 생긴다.
        // ex) (2, 1) = 1이고 (1, 1)도 1일 때 이 위치에서 9로 바꾸지 않으면
        //     이미 탐색한 값이지만 값은 그대로 1이라 위의 탈출 조건에 부합하지 않기 때문에
        //     계속해서 서로를 dfs()에 호출한게 된다.
        map[i][j] = 9;
        count++;

        count = dfs(map, i-1, j, count);    // count 변수에 할당하지 않으면 해당 메소드에서 계산한 값이 반영되지 않음
        count = dfs(map, i+1, j, count);
        count = dfs(map, i, j-1, count);
        count = dfs(map, i, j+1, count);

        return count;
    }
}
