package me.liiot.Level2;

import java.util.HashSet;
import java.util.Set;

/*
소수 찾기
https://catnap-jo.tistory.com/102
 */
public class FindPrimeNumbers {

    public static void main(String[] args) {

        System.out.println(solution("011"));
    }

    public static int solution(String numbers) {

        if (numbers==null || numbers.length()==0)
            return 0;

        Set<Integer> result = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[numbers.length()];

        for (int i=0; i<numbers.length(); i++) {
            permutation(result, sb, visited, numbers.toCharArray());
        }

        return result.size();
    }

    private static void permutation(Set<Integer> result, StringBuilder sb, boolean[] visited, char[] nums) {

        if (!sb.toString().equals("")) {
            int n = Integer.parseInt(sb.toString());
            if (isPrime(n))
                result.add(n);
        }

        for (int i=0; i<nums.length; i++) {
            if (!visited[i]) {
                sb.append(nums[i]);
                visited[i] = true;
                System.out.println(sb.toString());
                permutation(result, sb, visited, nums);
                visited[i] = false;
                sb.delete(sb.length() - 1, sb.length());
            }
        }
    }

    private static boolean isPrime(int n) {

        if (n == 0 || n == 1) return false;
        if (n == 2) return true;

        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
