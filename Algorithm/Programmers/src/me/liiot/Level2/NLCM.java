package me.liiot.Level2;

import java.util.Arrays;

/*
N개의 최소공배수

<유클리드 호제법>
ex) 1071과 1029의 최대공약수를 구하면,
1071은 1029로 나누어 떨어지지 않기 때문에, 1071을 1029로 나눈 나머지를 구한다. ≫ 42
1029는 42로 나누어 떨어지지 않기 때문에, 1029를 42로 나눈 나머지를 구한다. ≫ 21
42는 21로 나누어 떨어진다. 따라서, 최대공약수는 21이다.
-> (1071, 1029) = (1029, 42) = (42, 21) = (21, 0) = 21
 */
public class NLCM {

    public static void main(String[] args) {

        int[] arr = {3,4,9,16};
        System.out.println(solution(arr));
    }

    public static int solution(int[] arr) {

        Arrays.sort(arr);
        int gcd = 0;
        int lcm = arr[0];
        for (int i=1; i<arr.length; i++) {
            gcd = gcd(lcm, arr[i]);
            lcm = (lcm * arr[i]) / gcd;
        }

        return lcm;
    }

    public static int gcd(int n, int m) {

        int max = Math.max(n, m);
        int min = Math.min(n, m);
        if (min == 0) return max;

        return min == 0 ? max : gcd(min, (max % min));
    }
}
