package me.liiot.Level1;

import java.util.LinkedList;
import java.util.Queue;

/*
3진법 뒤집기

<진법으로 쉽게 바꾸는 법>
71을 5진법으로 바꾼다고 할 때,
         몫    나머지
71 / 5 = 14  |   1
14 / 5 = 2   |   4
71을 5진법으로 바꾸면 241이 된다.
 */
public class ReverseTernary {

    public static void main(String[] args) {

        ReverseTernary reverse = new ReverseTernary();

        System.out.println(reverse.solution(45));
        System.out.println(reverse.solution(125));
    }

    public int solution(int n) {

        if (n == 0)
            return 0;

        Queue<Integer> queue = new LinkedList<>();
        while (n!=1 && n!=2) {
            queue.add(n % 3);
            n /= 3;
        }
        queue.add(n);

        int result = 0;
        int size = queue.size();
        for (int i=size-1; i>=0; i--) {
            int curr = queue.poll();
            result += curr * (int)Math.pow(3, i);
        }

        return result;
    }
}
