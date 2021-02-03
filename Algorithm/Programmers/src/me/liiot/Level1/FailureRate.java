package me.liiot.Level1;

import java.util.*;

/*
실패율
스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
 */
public class FailureRate {

    public static void main(String[] args) {

        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] result = solution(5, stages);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(int n, int[] stages) {

        int[] clearCounts = new int[n];
        int[] failCounts = new int[n];

        for (int i : stages) {
            int index = 0;
            while (index < n && index < i) {
                clearCounts[index]++;
                index++;
            }

            if (i != n + 1) {
                failCounts[i - 1]++;
            }
        }

        Comparator<float[]> comp = new Comparator<float[]>() {
            @Override
            public int compare(float[] o1, float[] o2) {
                float result = o2[1] - o1[1];
                if (result < 0) {
                    return -1;
                } else if (result == 0) {
                    return (int)o1[0] - (int)o2[0];
                } else {
                    return 1;
                }
            }
        };

        PriorityQueue<float[]> pq = new PriorityQueue<float[]>(comp);
        for (int i=0; i<n; i++) {
            if (clearCounts[i] == 0 || failCounts[i] == 0) {
                pq.add(new float[]{i+1, (float) 0});
            } else {
                pq.add(new float[]{i+1, failCounts[i]/(float)clearCounts[i]});
            }
        }


        int[] result = new int[n];
        int index = 0;
        while (!pq.isEmpty()) {
            float[] cur = pq.poll();
            result[index] = (int) cur[0];
            index++;
        }

        return result;
    }
}
