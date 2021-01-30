package me.liiot.Level1;

/*
최대공약수와 최소공배수
 */
public class GCDAndLCM {

    public static void main(String[] args) {

        int[] result = solution(3, 12);
        System.out.println(result[0] + " " + result[1]);
    }

    public static int[] solution(int n, int m) {

        if (n==0 || m==0)
            return new int[]{0, 0};

        int gcm = gcm(n, m);

        return new int[]{gcm, ((n * m) / gcm)};
    }

    public static int gcm(int n, int m) {

        int max = 1;
        int limit = Math.min(n, m);
        for (int i=2; i<=limit; i++) {
            if ((n%i)==0 && (m%i)==0) {
                max = Math.max(max, i);
            }
        }

        return max;
    }
}
