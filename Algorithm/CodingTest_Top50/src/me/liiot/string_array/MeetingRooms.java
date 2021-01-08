package me.liiot.string_array;

import java.util.Arrays;
import java.util.Comparator;

/*
01) Meeting Room
주어진 배열에는 하나의 회의실에 예약된 모든 사람들의 회의 시작 시간과 종료 시간이 담겨져 있습니다.
각 원소는 [시작 시간, 종료 시간](시작 시간 < 종료 시간)을 뜻하며,
모든 회의의 시간 간격을 고려하여 하나의 회의실에서 모든 회의가 열릴 수 있는지 확인합니다.
*/
class Interval {
    int start;
    int end;

    public Interval() {
        this.start = 0;
        this.end = 0;
    }

    public Interval(int s, int e) {
        this.start = s;
        this.end = e;
    }
}

public class MeetingRooms {
    public static void main(String[] args) {
        MeetingRooms room = new MeetingRooms();

        Interval i1 = new Interval(15, 20);
        Interval i2 = new Interval(5, 10);
        Interval i3 = new Interval(0, 30);

        Interval[] intervals1 = {i1, i2, i3};
        System.out.println(room.solve(intervals1));

        Interval i4 = new Interval(7, 10);
        Interval i5 = new Interval(2, 4);

        Interval[] intervals2 = {i4, i5};
        System.out.println(room.solve(intervals2));
    }

    public boolean solve(Interval[] intervals) {
        // null 체크 후, start 값으로 오름차순 정렬(회의가 시작되는 순으로)
        if (intervals == null) {
            return false;
        }
        print(intervals);

        Arrays.sort(intervals, Comp);
        print(intervals);

        // 현재 원소의 종료 시간보다 다음 원소의 시작 시간이 앞서면 회의 시간이 겹쳐 회의실이 하나 더 필요한 것이다.
        for (int i=0; i<intervals.length-1; i++) {
            if (intervals[i].end > intervals[i+1].start) {
                return false;
            }
        }

        return true;
    }

    Comparator<Interval> Comp = new Comparator<Interval>() {
        @Override
        public int compare(Interval o1, Interval o2) {
            return o1.start - o2.start;     // 오름차순 정렬
        }
    };

    public void print(Interval[] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            System.out.println(intervals[i].start + " " +intervals[i].end);
        }
    }
}
