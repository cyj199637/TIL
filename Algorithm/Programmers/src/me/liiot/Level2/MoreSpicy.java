package me.liiot.Level2;

import java.util.PriorityQueue;

/*
더 맵게
 */
public class MoreSpicy {

    public static void main(String[] args) {

        int[] scoville = {1, 0};
        System.out.println(solution(scoville, 1));
    }

    public static int solution(int[] scoville, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : scoville) {
            pq.add(i);
        }

        int count = 0;
        while (pq.peek() < k && pq.size() >= 2) {
            int a = pq.poll();
            int b = pq.poll();
            pq.add(a + (b * 2));
            count++;
        }

        return pq.peek() < k && pq.size() < 2 ? -1 : count;
    }
}
