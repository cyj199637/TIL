package me.liiot.QueueAndStack;

import java.util.Stack;

/*
24) Baseball Game
 */
public class BaseballGame {

    public static void main(String[] args) {

        String[] points = {"5", "-2", "4", "C", "D", "9", "+", "+"};

        System.out.println(solve(points));
    }

    private static int solve(String[] points) {

        Stack<Integer> stack = new Stack<>();

        for (String point : points) {
            switch (point) {
                case "C" :
                    stack.pop();
                    break;

                case "D" :
                    stack.push(stack.peek()*2);
                    break;

                case "+" :
                    int x = stack.pop();
                    int y = stack.pop();
                    int z = x + y;
                    stack.push(y);
                    stack.push(x);
                    stack.push(z);
                    break;

                default:
                    stack.push(Integer.valueOf(point));
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}
