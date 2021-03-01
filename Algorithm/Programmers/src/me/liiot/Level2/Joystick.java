package me.liiot.Level2;

import java.util.Arrays;

/*
조이스틱
 */

class Move {

    int nextIndex;
    int count;

    public Move (int nextIndex, int count) {
        this.nextIndex = nextIndex;
        this.count = count;
    }
}

public class Joystick {

    static int length;

    public static void main(String[] args) {

        length = "BBBAAB".length();
        System.out.println(solution("BBBAAB"));
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

            Move leftMove = left(cur, isChanged, allTrue);
            Move rightMove = right(cur, isChanged, allTrue);

            if (leftMove.count < rightMove.count) {
                count += leftMove.count;
                cur = leftMove.nextIndex;
            } else {
                count += rightMove.count;
                cur = rightMove.nextIndex;
            }
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

    public static Move left(int cur, boolean[] isChanged, boolean[] allTrue) {

        int count = 0;
        while (isChanged[cur] && !Arrays.equals(isChanged, allTrue)) {
            cur = (cur == 0) ? length - 1 : cur - 1;
            count++;
        }
        return new Move(cur, count);
    }

    public static Move right(int cur, boolean[] isChanged, boolean[] allTrue) {

        int count = 0;
        while (isChanged[cur] && !Arrays.equals(isChanged, allTrue)) {
            cur = (cur == (length - 1)) ? 0 : cur + 1;
            count++;
        }
        return new Move(cur, count);
    }
}
