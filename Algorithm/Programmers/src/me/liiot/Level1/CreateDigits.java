package me.liiot.Level1;

/*
자릿수 만들기
 */
public class CreateDigits {

    public static void main(String[] args) {

        System.out.println(solution(123));
    }

    private static int solution(int n) {

        int answer = 0;
        while (n != 0) {
            answer += (n % 10);
            n /= 10;
        }

        return answer;
    }
}
