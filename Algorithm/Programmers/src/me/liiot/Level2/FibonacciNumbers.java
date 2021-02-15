package me.liiot.Level2;

/*
피보나치 수

- (A + B) % C = ((A % C) + (B % C)) % C
- 단순히 재귀함수로 풀면 시간 초과됨
 */
public class FibonacciNumbers {

    public static void main(String[] args) {

        System.out.println(solution(50));
    }

    public static int solution(int n) {

        int[] fibonacci = new int[n+1];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        if (n >= 2) {
            for (int i=2; i<=n; i++) {
                fibonacci[i] = (fibonacci[i - 2] + fibonacci[i - 1]) % 1234567;
            }
        }

        return fibonacci[n];
    }
}
