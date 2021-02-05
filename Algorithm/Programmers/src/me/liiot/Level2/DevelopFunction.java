package me.liiot.Level2;

import java.util.ArrayList;
import java.util.List;

/*
기능 개발
 */
public class DevelopFunction {

    public static void main(String[] args) {

        int[] progresses1 = {93, 30, 55};
        int[] speeds1 = {1, 30, 5};
        List<Integer> result1 = solution(progresses1, speeds1);
        for (int i : result1) {
            System.out.print(i + " ");
        }
        System.out.println();

        int[] progresses2 = {95, 90, 99, 99, 80, 99};
        int[] speeds2 = {1, 1, 1, 1, 1, 1};
        List<Integer> result2 = solution(progresses2, speeds2);
        for (int i : result2) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static List<Integer> solution(int[] progresses, int[] speeds) {

        int length = progresses.length;
        int[] days = new int[length];

        for (int i=0; i<length; i++) {
            int overtime = 100-progresses[i];
            days[i] = (overtime % speeds[i]) == 0 ? overtime / speeds[i] : (overtime/speeds[i]) + 1;
        }

        List<Integer> result = new ArrayList<>();
        int count = 1;
        int start = 0;
        for (int i=1; i<length; i++) {
            if (days[start] < days[i]) {
                result.add(count);
                count = 1;
                start = i;
                continue;
            }
            count++;
        }
        result.add(count);

        return result;
    }
}
