package me.liiot.Level2;

import java.util.Stack;

/*
주식 가격
 */
public class StockPrice {

    public static void main(String[] args) {

        int[] prices = {1, 2, 3, 2, 3, 1};
        int[] result = solution(prices);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(int[] prices) {

        Stack<Integer> stack = new Stack<>();
        int length = prices.length;
        int[] result = new int[length];

        for (int i=0; i<length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                result[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.add(i);
        }

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            result[cur] = (length - 1) - cur;
        }

        return result;
    }
}
