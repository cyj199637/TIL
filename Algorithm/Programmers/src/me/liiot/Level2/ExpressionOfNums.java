package me.liiot.Level2;

/*
숫자의 표현
 */
public class ExpressionOfNums {

    public static void main(String[] args) {

        System.out.println(solution(15));
    }

    public static int solution(int n) {

        int count = 1;
        int sum = 0;
        int start = 1;
        while (start < n) {
            for (int i = start; i <= n-start; i++) {
                sum += i;

                if (sum == n) {
                    count++;
                    sum = 0;
                    break;
                }

                if (sum > n) {
                    sum = 0;
                    break;
                }
            }
            start++;
        }

        return count;
    }
}
