package me.liiot.Level2;

import java.util.*;

/*
프린터
 */
public class Printer {

    public static void main(String[] args) {

        int[] priorities = {2, 1, 3, 1};
        System.out.println(solution(priorities, 2));
    }

    public static int solution(int[] priorities, int l) {

        Queue<Integer> queue = new LinkedList<>();
        PriorityQueue<Integer> desc = new PriorityQueue<>(Comparator.reverseOrder());
        int length = priorities.length;

        for (int i=0; i<length; i++) {
            queue.add(i);
            desc.add(priorities[i]);
        }

        int order = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (priorities[cur] == desc.peek()) {
                order++;
                desc.poll();
                if (cur == l)
                    return order;
            }

            queue.add(cur);
        }

        return 0;
    }
}
