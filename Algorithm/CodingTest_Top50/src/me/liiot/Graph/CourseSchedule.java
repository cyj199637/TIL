package me.liiot.Graph;

import java.util.HashMap;
import java.util.Map;

/*
38) Courese Schedule
주어진 2차원 배열은 어떤 학생이 선택한 과목들로 이루어져 있습니다.
배열의 각 원소는 [과목, 선수 과목]으로 이루어져 있습니다.
해당 과목의 선수 과목이 주어진 배열에 없으면 그 과목은 들을 수 없습니다.
이 학생이 배열에 들어 있는 모든 과목을 들을 수 있는 지에 대한 여부를 반환하세요.

<위상정렬(DAG)>
: 사이클이 없도록 나열하는 것
- 가장 마지막 원소부터 탐색하는 것이 좋다.
 */
public class CourseSchedule {

    public static void main(String[] args) {

        int[][] courses1 = {{1, 0}, {0, 1}};
        int[][] courses2 = {{1, 0}, {2, 3}, {3, 2}};

        System.out.println(solve(courses1));
        System.out.println(solve(courses2));
    }

    private static boolean solve(int[][] courses) {

        Map<Integer, Integer> schedule = new HashMap<>();
        for (int[] course : courses) {
            schedule.put(course[0], course[1]);
        }

        for (int i=courses.length-1; i>-1; i--) {
            // 첫 번째 과목은 선수 과목이 Map에 없어야 사이클이 만들어지지 않는다.
            if (i==0 && !schedule.containsKey(courses[i][1])) return true;

            // 다른 순서의 과목은 선수 과목이 Map에 있어야 하며,
            // 그 선수 과목의 선수 과목이 자신을 가르키지 않아야 사이클이 만들어지지 않는다.
            if (!schedule.containsKey(courses[i][1]) || schedule.get(courses[i][1])==courses[i][0]) break;
        }

        return false;
    }
}
