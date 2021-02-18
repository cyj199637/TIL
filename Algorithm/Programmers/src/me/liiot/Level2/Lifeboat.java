package me.liiot.Level2;

import java.util.Arrays;

/*
구명보트
 */
public class Lifeboat {

    public static void main(String[] args) {

        int[] people = {70, 50, 80, 50};
        System.out.println(solution(people, 100));
    }

    public static int solution(int[] people, int limit) {

        Arrays.sort(people);
        int count = 0;
        int end = 0;
        for (int i=people.length-1; i>=end; i--) {  // 가장 큰 값부터 탐색
            count++;    // 어떤 경우든 보트 수는 증가
            if (people[i] + people[end] <= limit)   // 다만, 현재 사람이 현재 가장 가벼운 사람까지 같이 태울 수 있다면
                end++;                              // 현재 가장 가벼운 사람은 보트 수 계산에 제외시킴
        }

        return count;
    }
}
