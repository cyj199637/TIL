package me.liiot.string_array;

import java.util.Stack;

/*
04) Daily Temperature
주어진 배열에는 일별 기온이 담겨 있습니다.
각 날마다 기온이 올라가는 가장 가까운 날까지의 기간을 배열에 담아 리턴하세요.
단, 주어진 배열 안에서 기온이 올라가는 날을 찾지 못했다면 0으로 대체하세요.
 */
public class DailyTemperature {

    public static void main(String[] args) {

        int[] daily = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = solve(daily);

        for (int i=0; i<result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    private static int[] solve(int[] daily) {

        Stack<Integer> stack = new Stack<>();
        int[] result = new int[daily.length];

        // 예를 들어, 현재 원소가 72인 경우, stack은 (4, 3, 2) 순으로 담겨 있다.
        for (int i=0; i<daily.length; i++) {
            // 72보다 작은 모든 원소들(71, 69)에 대해 기간을 계산해야 한다.
            while (!stack.isEmpty() && daily[stack.peek()] < daily[i]) {
                result[stack.peek()] = i - stack.peek();    // 기간 계산
                stack.pop();    // 기간이 계산된 원소는 제거
            }
            stack.push(i);
        }

        // 제거되지 못한 원소는 더 이상 기온이 오르는 날이 없다는 의미이다.
        while (stack.isEmpty()) {
            result[stack.peek()] = 0;   // 따라서, 기간을 0으로 대체
            stack.pop();
        }

        return result;
    }
}
