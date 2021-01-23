package me.liiot.Level1;

import java.util.Stack;

/*
크레인 인형 뽑기 게임
 */
public class CraneDollsDrawGame {

    public static void main(String[] args) {

        CraneDollsDrawGame game = new CraneDollsDrawGame();

        int[][] board = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 3},
                {0, 2, 5, 0, 1},
                {4, 2, 4, 4, 2},
                {3, 5, 1, 3, 1}
        };

        int[][] board2 = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};

        int[] moves2 = {1, 5, 3, 5, 1, 2, 5, 1, 4, 3};

        System.out.println(game.solution(board, moves2));
    }

    public int solution(int[][] board, int[] moves) {

        int answer = 0;
        if (board==null || board.length==0 || moves==null || moves.length==0)
            return answer;

        Stack<Integer> stack = new Stack<>();

        int peek = 0;
        for (int i : moves) {
            int doll = get(board, i-1);

            if (doll == 0) continue;

            if (peek == doll) {
                answer += 2;
                pow(stack, answer);
                peek = stack.isEmpty() ? 0 : stack.pop();
            } else if (peek != doll) {
                if (peek != 0) {
                    stack.push(peek);
                }
                peek = doll;
            }
        }

        return answer;
    }

    private int get(int[][] board, int j) {

        for (int i=0; i<board.length; i++) {
            if (board[i][j] != 0) {
                int doll = board[i][j];
                board[i][j] = 0;
                return doll;
            }
        }
        return 0;
    }

    private void pow(Stack<Integer> stack, int answer) {

        if (stack.isEmpty() || stack.size()<2)
            return;

        while(stack.size()>=2 && stack.elementAt(stack.size()-1).equals(stack.elementAt(stack.size()-2))) {
            answer += 2;
            stack.pop();
            stack.pop();
        }
    }
}
