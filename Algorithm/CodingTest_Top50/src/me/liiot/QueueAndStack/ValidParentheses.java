package me.liiot.QueueAndStack;

import java.util.Stack;

/*
25) Valid Parentheses
작성된 괄호가 짝이 맞는지 맞지 않는지에 대한 결과를 반환하세요.
단, ( 와 ), [ 와 ], { 와 }가 서로 개수가 같아도 ({})][ 처럼 짝이 맞지 않는다면 결과는 false 입니다.

- 이런 유형의 문제는 주로 Stack을 사용해서 풀게 된다.
 왜냐하면 특히 괄호같은 경우에는 나중에 열린 괄호부터 차례로 닫혀야 올바른 괄호의 형태라고 할 수 있기 때문이다.
 따라서, LIFO 특성을 가진 Stack으로 나중에 들어온 값부터 처리한다.
 */
public class ValidParentheses {

    public static void main(String[] args) {

        String p1 = "{[]}", p2 = "()[]{}", p3 = "([}]", p4 = "(]", p5 = ")]}}";

        System.out.println(solve(p1));
        System.out.println(solve(p2));
        System.out.println(solve(p3));
        System.out.println(solve(p4));
        System.out.println(solve(p5));
    }

    private static boolean solve(String p) {

        if (p.length()%2 != 0)
            return false;

        Stack<Character> stack = new Stack<>();
        for (int i=0; i<p.length(); i++) {
            switch (p.charAt(i)) {
                case ')' :
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                        break;
                    }

                case '}' :
                    if (!stack.isEmpty() && stack.peek() == '{') {
                        stack.pop();
                        break;
                    }

                case ']' :
                    if (!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                        break;
                    }

                default:
                    stack.push(p.charAt(i));
                    break;
            }
        }

        return stack.isEmpty() ? true : false;
    }
}
