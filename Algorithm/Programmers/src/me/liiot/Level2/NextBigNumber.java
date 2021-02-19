package me.liiot.Level2;

/*
다음 큰 숫자

- Integer.bitCount(int n): n을 이진수로 변환 후 1의 개수를 세어줌
 */
public class NextBigNumber {

    public static void main(String[] args) {

        System.out.println(solution2(78));
        System.out.println(solution2(15));
    }

    public static int solution(int n) {

        String target = Integer.toBinaryString(n);
        int targetCount = target.replace("0", "").length();

        while (true) {
            n++;
            String s = Integer.toBinaryString(n);
            if (s.replace("0", "").length() == targetCount)
                return n;
        }
    }

    public static int solution2(int n) {

        int targetCount = Integer.bitCount(n);
        while (true) {
            n++;
            if (Integer.bitCount(n) == targetCount)
                return n;
        }
    }
}
