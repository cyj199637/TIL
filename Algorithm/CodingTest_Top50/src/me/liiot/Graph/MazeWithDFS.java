package me.liiot.Graph;

/*
37) Maze with DFS
미로 빈 공간과 벽에 공이 있습니다. 공은 위, 아래, 왼쪽, 오른쪽으로 굴러 빈 공간을 통과 할 수 있지만
벽에 부딪힐 때까지 굴러가는 것을 멈추지 않습니다. 공이 멈추면 다음 방향을 선택할 수 있습니다.
공의 시작 위치, 목적지 및 미로가 주어졌을 때, 공이 목적지에서 멈출 수 있는지 여부를 반환하세요.

미로는 이진 2D 배열로 표시됩니다. 1은 벽을 의미하고 0은 빈 공간을 의미합니다.
미로의 경계는 모두 벽이라고 가정 할 수 있습니다. 시작 및 대상 좌표는 행과 열의 인덱스로 표시됩니다.
 */
public class MazeWithDFS {

    public static void main(String[] args) {

        int[][] maze = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };
        int[] start = {0, 4};
        int[] finish = {3, 2};

        System.out.println(solve(maze, start, finish));
    }

    private static boolean solve(int[][] maze, int[] start, int[] finish) {

        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        return search(maze, visited, dirs, start, finish);
    }

    private static boolean search(int[][] maze, boolean[][] visited, int[][] dirs, int[] start, int[] finish) {

        for (int[] dir : dirs) {
            int x = start[0] + dir[0];
            int y = start[1] + dir[1];

            while (x>=0 && x<maze.length && y>=0 && y<maze[0].length && maze[x][y]==0) {
                x += dir[0];
                y += dir[1];
            }

            x -= dir[0];
            y -= dir[1];

            if (visited[x][y]) continue;

            visited[x][y] = true;

            if (x==finish[0] && y==finish[1]) return true;

            // search(maze, visited, dirs, new int[] {x, y}, finish) 만 하고 끝나는게 아니라 이거에 대한 결과도 던져줘야 함
            if (search(maze, visited, dirs, new int[] {x, y}, finish)) return true;
        }

        return false;
    }
}
