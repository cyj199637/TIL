package me.liiot.Level2;

import java.util.Arrays;

/*
조이스틱
 */
public class Joystick {

    static int length;

    public static void main(String[] args) {

        length = "JAZ".length();
        System.out.println(solution("JAZ"));
    }

    public static int solution(String name) {

        char[] after = name.toCharArray();

        boolean[] isChanged = new boolean[length];
        boolean[] allTrue = new boolean[length];
        Arrays.fill(allTrue, true);

        for (int i=0; i<length; i++) {
            if (after[i] == 'A')
                isChanged[i] = true;
        }

        int count = 0;
        int cur = 0;
        while(!Arrays.equals(isChanged, allTrue)) {

            count += Math.min(up('A', after[cur]), down('A', after[cur]));
            isChanged[cur] = true;
            count += Math.min(left(cur, isChanged), right(cur, isChanged));
            cur++;
        }

        return count;
    }

    public static int up(char before, char after) {

        int count = 0;
        while (before != after) {
            before = before == 'Z' ? 'A' : (char) (before+1);
            count++;
        }
        return count;
    }

    public static int down(char before, char after) {

        int count = 0;
        while (before != after) {
            before = before == 'A' ? 'Z' : (char) (before-1);
            count++;
        }
        return count;
    }

    public static int left(int cur, boolean[] isChanged) {

        int count = 0;
        while (isChanged[cur]) {
            cur = (cur == 0) ? length - 1 : cur - 1;
            count++;
        }
        return count;
    }

    public static int right(int cur, boolean[] isChanged) {

        int count = 0;
        while (isChanged[cur]) {
            cur = (cur == (length - 1)) ? 0 : cur + 1;
            count++;
        }
        return count;
    }
}
