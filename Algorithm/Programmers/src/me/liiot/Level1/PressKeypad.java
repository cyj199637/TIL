package me.liiot.Level1;

import java.util.*;

/*
키패드 누르기
 */
public class PressKeypad {

    public static void main(String[] args) {

        int[] nums1 = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand1 = "right";
        System.out.println(solution(nums1, hand1));

        int[] nums2 = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String hand2 = "left";
        System.out.println(solution(nums2, hand2));

        int[] nums3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        String hand3 = "right";
        System.out.println(solution(nums3, hand3));
    }

    public static String solution(int[] numbers, String hand) {

        int[][] location = {
                {3, 1},
                {0, 0},
                {0, 1},
                {0, 2},
                {1, 0},
                {1, 1},
                {1, 2},
                {2, 0},
                {2, 1},
                {2, 2}
        };

        int[] leftCur = {3, 0};
        int[] rightCur = {3, 2};
        String result = "";

        for (int i : numbers) {
            if (i==1 || i==4 || i==7) {
                result += "L";
                leftCur = location[i];
            } else if (i==3 || i==6 || i==9) {
                result += "R";
                rightCur = location[i];
            } else {
                int leftPath = bfs(leftCur, location[i]);
                int rightPath = bfs(rightCur, location[i]);

                if (leftPath < rightPath) {
                    result += "L";
                    leftCur = location[i];
                } else if (rightPath < leftPath) {
                    result += "R";
                    rightCur = location[i];
                } else {
                    if (hand.equals("left")) {
                        result += "L";
                        leftCur = location[i];
                    } else {
                        result += "R";
                        rightCur = location[i];
                    }
                }
            }
        }

        return result;
    }

    private static int bfs(int[] cur, int[] finish) {

        boolean[][] visited = new boolean[4][3];
        int[][] distance = new int[4][3];
        int[][] location = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        int[] start = cur;

        queue.add(start);
        while (!queue.isEmpty()) {
            start = queue.poll();
            if (Arrays.equals(start, finish))
                break;

            for (int[] l : location) {
                int[] next = {(start[0]+l[0]), (start[1]+l[1])};

                if (next[0]<0 || next[0]>=4 || next[1]<0 || next[1]>=3 || visited[next[0]][next[1]])
                    continue;

                visited[next[0]][next[1]] = true;
                distance[next[0]][next[1]] = distance[start[0]][start[1]] + 1;
                queue.add(next);
            }
        }

        return distance[finish[0]][finish[1]];
    }
}
