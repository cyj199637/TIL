package me.liiot.Level1;

import java.util.*;

/*
예산

- d = [1,2,3], budget = 10 이라면, answer가 d의 길이보다 커져도
  sum < budget 조건은 만족하기 때문에 while문이 계속 돌아간다.
  따라서, answer가 d의 길이보다 커지지 않게 조건을 걸어야 한다.
 */
public class Budget {

    public static void main(String[] args) {

        int[] d = {1, 3, 2, 5, 4};
        int budget = 9;

        System.out.println(solution(d, budget));
    }

    public static int solution(int[] d, int budget) {

        Arrays.sort(d);

        int sum = 0;
        int answer = 0;
        while (sum < budget && answer < d.length) {
            sum += d[answer];
            answer++;
        }

        return sum <= budget ? answer : answer - 1;
    }
}
