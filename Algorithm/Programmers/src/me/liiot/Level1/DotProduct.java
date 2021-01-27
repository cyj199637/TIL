package me.liiot.Level1;

/*
내적
 */
public class DotProduct {

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4};
        int[] b = {-3, -1, 0, 2};

        System.out.println(solution(a, b));
    }

    private static int solution(int[] a, int[] b) {

        int answer = 0;
        if (a==null || a.length==0 || b==null || b.length==0)
            return answer;

        int size = a.length;
        for (int i=0; i<size; i++) {
            answer += a[i] * b[i];
        }

        return answer;
    }
}
