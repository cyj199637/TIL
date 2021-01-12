package me.liiot.Graph;

/*
34) Word Search
이차원 배열과 단어가 주어졌을 때, 배열에 해당 단어가 있는지 찾으세요.

단어는 순차적으로 인접한 칸의 문자로 구성 될 수 있습니다.
여기서 "인접한" 칸은 가로 또는 세로로 인접한 칸입니다.
동일한 칸의 문자를 두 번 이상 사용할 수 없습니다.
 */
public class WordSearch {

    public static void main(String[] args) {

        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        System.out.println(solve(board, "ABCCED"));
        System.out.println(solve(board, "SEE"));
        System.out.println(solve(board, "ABAB"));
    }

    private static boolean solve(char[][] board, String str) {

        boolean result = false;
        if (board == null || board.length == 0)
            return result;

        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (str.charAt(0) == board[i][j])
                    board[i][j] = '?';
                    if (search(board, str, 1, i, j))
                        return true;
            }
        }

        return false;
    }

    private static boolean search(char[][] board, String str, int s, int i, int j) {

        if (s == str.length())  // 끝까지 다 찾았다는 의미이므로 true
            return true;

        if (i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]=='?')
            return false;

        if (board[i][j] == str.charAt(s)) {
            board[i][j] = '?';
            return true;
        }

        s++;

        search(board, str, s, i - 1, j);
        search(board, str, s, i + 1, j);
        search(board, str, s, i, j - 1);
        search(board, str, s, i, j + 1);

        return false;
    }

    private static boolean solve2(char[][] board, String str) {

        boolean result = false;
        if (board == null || board.length == 0)
            return result;

        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (str.charAt(0) == board[i][j])
                    visited[i][j] = true;
                    if (search2(board, visited, str, 1, i, j))
                        return true;
            }
        }

        return false;
    }

    private static boolean search2(char[][] board, boolean[][] visited, String str, int s, int i, int j) {

        if (s == str.length())
            return true;

        if (i<0 || i>=board.length || j<0 || j>=board[0].length || visited[i][j])
            return false;

        if (board[i][j] != str.charAt(s)) {
            return false;
        }

        visited[i][j] = true;
        s++;

        if (search2(board, visited, str, s, i - 1, j))
            return true;
        if (search2(board, visited, str, s, i + 1, j))
            return true;
        if (search2(board, visited, str, s, i, j - 1))
            return true;
        if (search2(board, visited, str, s, i, j + 1))
            return true;

        visited[i][j] = false;

        return false;
    }
}
