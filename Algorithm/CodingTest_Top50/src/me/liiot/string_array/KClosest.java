package me.liiot.string_array;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
09) K Closest Points to Origin
비행기의 포인트가 담겨진 리스트가 주어집니다. 원점 (0, 0)에 가장 가까운 K개의 점을 찾습니다.
여기에서 평면의 두 점 사이의 거리는 유클리드 거리입니다.
반환하는 리스트 안의 점의 순서는 상관없습니다.

- 보통 무언가의 최솟값/최댓값을 구할 때 min/max라는 변수를 두고 값들을 계속 비교하면서
 변수값을 갱신하는 방법으로 문제를 풀어나간다. 그러나 PriorityQueue를 사용하면 비교하거나
 변수값을 갱신할 필요없이 주어진 모든 원소를 PriorityQueue에 저장하면 알아서 정렬해주므로
 가장 위에 있는 값만 가져오면 된다.
 */
public class KClosest {

    public static void main(String[] args) {

        KClosest kClosest = new KClosest();

        int[][] points1 = {{1, 3}, {-2, 2}};
        int k1 = 1;

        kClosest.print(kClosest.solve(points1, k1));

        int[][] points2 = {{3, 3}, {5, -2}, {-2, 4}};
        int k2 = 2;

        kClosest.print(kClosest.solve(points2, k2));
    }

    private int[][] solve(int[][] points, int k) {

        int[][] result = new int[k][2];

        Queue<int[]> queue = new PriorityQueue<>(points.length, Comp);
        for (int[] point : points) {
            queue.offer(point);
        }

        int index=0;
        while (index<k) {
            result[index] = queue.poll();
            index++;
        }

        return result;
    }

    Comparator<int[]> Comp = new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return ((o1[0]*o1[0])+(o1[1]*o1[1])) - ((o2[0]*o2[0])+(o2[1]*o2[1]));
        }
    };

    private void print(int[][] result) {
        for (int i=0; i<result.length; i++) {
            System.out.print("(" + result[i][0] + ", " + result[i][1] + ") ");
        }
        System.out.println();
    }
}
