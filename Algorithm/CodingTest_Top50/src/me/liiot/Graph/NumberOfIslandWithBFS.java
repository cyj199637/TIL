package me.liiot.Graph;

import java.util.LinkedList;
import java.util.Queue;

/*
28) Number Of Island with BFS
'1'(토지)과 '0'(물)로 이루어진 그리드 맵이 주어졌을 때 섬의 수를 반환하세요.
섬은 물로 둘러싸여 있으며 인접한 땅을 수평 또는 수직으로 연결하여 형성됩니다.
그리드 맵의 네 가장자리는 모두 물로 둘러싸여 있다고 가정합니다.

- DFS는 깊이 우선이라 만나는 포인트마다 dfs()를 계속해서 재귀호출했지만
 BFS는 너비 우선이라 재귀호출이 아니라 Queue에 저장된 순서대로 포인트를 꺼내와 bfs() 처리함
 처리하는 중에 만나는 다른 포인트는 다시 Queue에 저장
 */
public class NumberOfIslandWithBFS {

    public static void main(String[] args) {

        int[][] map = {
                {1, 1, 1, 0, 1},
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 1}
        };

        System.out.println(solve(map));

        int[][] map2 = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}
        };

        System.out.println(solve(map2));
    }

    private static int solve(int[][] map) {

        int result = 0;
        if (map == null || map.length == 0 || map[0].length == 0)
            return result;

        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map[0].length; j++) {
                if (map[i][j] == 1) {
                    result++;
                    bfs(map, i, j);
                }
            }
        }

        print(map);

        return result;
    }

    private static void bfs(int[][] map, int i, int j) {
        map[i][j] = 9;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        queue.add(new int[] {i, j});

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int[] dir : dirs) {
                int x = point[0] + dir[0];
                int y = point[1] + dir[1];

                if (x<0 || x>=map.length || y<0 || y>=map[0].length || map[x][y]!=1)
                    continue;   // return이나 break로 처리해버리면 다른 연결된 포인트를 처리하기 전에
                                // 반복문과 bfs() 자체가 끝나게 되어 결국 1을 가진 점마다 섬 개수 카운트에 들어간다.
                map[x][y] = 9;
                queue.add(new int[] {x, y});
            }
        }
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
