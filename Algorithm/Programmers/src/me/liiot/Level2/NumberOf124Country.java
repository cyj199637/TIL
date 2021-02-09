package me.liiot.Level2;

import java.util.Stack;

/*
124 나라의 숫자
 */
public class NumberOf124Country {

    public static void main(String[] args) {

        System.out.println(solution(15));
    }

    public static String solution(int n) {

        Stack<String> stack = new Stack<>();
        while (n != 0) {
            if (n % 3 == 0) {
                stack.add("4");
            } else if (n % 3 == 1) {
                stack.add("1");
            } else if (n % 3 == 2){
                stack.add("2");
            }

            n /= 3;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
