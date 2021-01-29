package me.liiot.Level1;

/*
정수 제곱근 판별
 */
public class IsIntegerSquareRoot {

    public static void main(String[] args) {

        System.out.println(solution(121l));
        System.out.println(solution(3l));
    }

    private static long solution(long n) {

        double answer = Math.sqrt(n);

        if ((answer % 1) == 0) {
            return (long) Math.pow((answer+1), 2);
        } else {
            return -1;
        }
    }
}
