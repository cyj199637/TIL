package me.liiot.string_array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
05) Merge Interval
주어진 배열의 각 원소는 시작 지점과 종료 지점을 포함하고 있으며, 이는 간격을 나타냅니다.
주어진 배열에서 간격이 겹치는 모든 원소들을 합쳐서 리턴하세요.

- 결과의 개수를 예측할 수 없을 때는 결과를 담을 그릇으로 ArrayList를 사용하자.
 */
public class MergeInterval {

    public static void main(String[] args) {

        Interval i1 = new Interval(1, 3);
        Interval i2 = new Interval(2, 6);
        Interval i3 = new Interval(8, 16);
        Interval i4 = new Interval(15, 18);

        List<Interval> intervals = new ArrayList<>();
        intervals.add(i1);
        intervals.add(i2);
        intervals.add(i3);
        intervals.add(i4);

        List<Interval> result = solve(intervals);
        for (int i=0; i<result.size(); i++) {
            System.out.print("(" + result.get(i).start + ", " + result.get(i).end + ") ");
        }
    }

    private static List<Interval> solve(List<Interval> intervals) {

        if (intervals.isEmpty()) return intervals;

        List<Interval> result = new ArrayList<>();

        // Comparator 구현 대신 람다 표현식으로 정렬 -> 오름차순 정렬
        Collections.sort(intervals, (a, b) -> a.start - b.start);

        Interval merge = intervals.get(0);
        for (int i=1; i<intervals.size(); i++) {
            Interval current = intervals.get(i);

            if (merge.end > current.start) {
                merge.end = Math.max(merge.end, current.end);
            } else {
                result.add(merge);
                merge = current;    // 마지막 원소는 result에 추가되지 못하고 반복문이 끝남
            }
        }

        result.add(merge);

        return result;
    }

    Comparator<Interval> comp = new Comparator<Interval>() {
        @Override
        public int compare(Interval o1, Interval o2) {
            return o1.start - o2.start;
        }
    };
}
