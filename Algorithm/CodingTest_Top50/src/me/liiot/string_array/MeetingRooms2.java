package me.liiot.string_array;

import java.util.*;

/*
06) Meeting Room2
주어진 배열에는 하나의 회의실에 예약된 모든 사람들의 회의 시작 시간과 종료 시간이 담겨져 있습니다.
각 원소는 [시작 시간, 종료 시간](시작 시간 < 종료 시간)을 뜻하며,
모든 회의의 시간 간격을 고려하여 모든 회의를 진행시킬 수 있는 최소한의 회의실 수를 찾으세요.

- PriorityQueue는 기존에 저장되어 있던 원소를 꺼내 어떠한 작업을 한 후 다시 저장하는 구조에 많이 쓰인다.
- PriorityQueue는 내부적으로 알아서 오름차순(minHeap)/내림차순(maxHeap)으로 정렬해주기 때문에
 꺼내진 원소를 다시 저장해도 내가 PriorityQueue에 접근할 때는 항상 최솟값/최댓값에 접근할 수 있는 것이다.
- 이 문제에서는 이전 회의의 끝나는 시간과 현재 회의의 시작하는 시간을 계속해서 비교하고
 두 회의 시간이 겹치지 않는다면 하나의 회의실에서 이어서 할 수 있는 하나의 회의로 생각해야 된다.(최소 회의실 수를 구하는 것이기 때문)
 따라서, 회의 종료 시간이 작은 것부터 차례대로 비교하는 것이 좋으므로 PriorityQueue를 사용하는 것이다.
 (하나의 회의로 합쳐져서 끝나는 시간이 달라지더라도 PriorityQueue에서 다시 정렬해주므로)
 */
public class MeetingRooms2 {

    public static void main(String[] args) {

        Interval i1 = new Interval(15, 20);
        Interval i2 = new Interval(5, 10);
        Interval i3 = new Interval(0, 30);

        Interval[] intervals1 = {i1, i2, i3};
        System.out.println("solve1: " + solve(intervals1));

        Interval i4 = new Interval(7, 10);
        Interval i5 = new Interval(2, 4);

        Interval[] intervals2 = {i4, i5};
        System.out.println("solve2: " + solve(intervals2));

        Interval i6 = new Interval(1, 4);
        Interval i7 = new Interval(4, 6);
        Interval i8 = new Interval(4, 5);

        Interval[] intervals3 = {i6, i7, i8};
        System.out.println("solve3: " + solve(intervals1));
    }

    private static int solve(Interval[] intervals) {

        if (intervals == null || intervals.length == 0) return 0;

        // 시작 시간으로 오름차순 정렬
        Arrays.sort(intervals, (a, b) -> a.start - b.start);

        // 종료 시간으로 오름차순 정렬
        Queue<Interval> minHeap = new PriorityQueue<>(intervals.length, (a, b) -> (a.end - b.end));
        int count = 0;

        for (int i=0; i<intervals.length; i++) {
            // 기존 저장된 회의와 현재 회의가 겹치지 않는다면 하나의 회의실에서 이어서 할 수 있는 하나의 회의로 간주
            while (!minHeap.isEmpty() && minHeap.peek().end <= intervals[i].start) {
                minHeap.poll();     // 기존 회의는 제거하고
            }
            minHeap.offer(intervals[i]);    // 현재 회의를 저장
            count = Math.max(count, minHeap.size());
        }

        return count;
    }
}
