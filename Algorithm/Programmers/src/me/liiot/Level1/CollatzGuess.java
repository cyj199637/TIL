package me.liiot.Level1;

/*
콜라츠 추측
 */
public class CollatzGuess {

    public static void main(String[] args) {

        System.out.println(solution(6));
        System.out.println(solution(16));
        System.out.println(solution(626331));
    }

    public static int solution(int n) {

        int count = 0;
        long sum = n;
        while (sum != 1 && count < 501) {
            count++;
            if ((sum % 2) != 0) {
                sum = (sum * 3) + 1;
                continue;
            }
            sum /= 2;
        }

        return count >= 501 ? -1 : count;
    }
}
