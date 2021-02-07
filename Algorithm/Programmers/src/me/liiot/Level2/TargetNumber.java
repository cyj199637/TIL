package me.liiot.Level2;

/*
타깃 넘버
 */
public class TargetNumber {

    public static void main(String[] args) {

        int[] numbers = {1, 1, 1, 1, 1};
        System.out.println(solution(numbers, 3));
    }

    public static int solution(int[] numbers, int target) {

        int result = 0;
        result = dfs(numbers, target, 0, 0);

        return result;
    }

    private static int dfs(int[] numbers, int target, int sum, int index) {

        if (index == numbers.length) {
            if (sum == target) {
                return 1;
            } else {
                return 0;
            }
        }

        return dfs(numbers, target, sum+numbers[index], index+1) + dfs(numbers, target, sum-numbers[index], index+1);
    }
}
