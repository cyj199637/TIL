package me.liiot.Level2;

import java.util.Stack;

/*
올바른 괄호
 */
public class CorrectParenthesis {

    public static void main(String[] args) {

        System.out.println(solution("()()"));
        System.out.println(solution("(())()"));
        System.out.println(solution(")()("));
        System.out.println(solution("()))"));
    }

    public static boolean solution(String s) {

        char[] parenthesis = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char p : parenthesis) {
            if (!stack.isEmpty() && stack.peek() == '(' && p == ')') {
                stack.pop();
            } else {
                stack.add(p);
            }
        }

        return stack.isEmpty() ? true : false;
    }
}
