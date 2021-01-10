package me.liiot.StringAndArray;

/*
13) Maximum Subarray
정수 배열 nums가 주어졌을 때, nums에서 합계가 가장 큰 연속된 부분 배열 (최소 하나의 숫자 포함)을 찾아 그 합계를 반환합니다.

- 원래 이 문제에서 어떤 값을 더해야 큰 지 알아볼 때에는 세 가지 경우가 있다.
  1. 현재 원소 뺴고 기존 원소(or 합)만 남기기 / 2. 기존 원소(or 합) + 현재 원소 / 3. 기존 원소(or 합) 빼고 현재 원소만 남기기
 그러나 1번의 경우, 그 값이 세 가지 경우에서 가장 크더라도 그 다음 원소와 비교할 때에는 연속적인 부분 집합이
 되지 못하기 때문에 문제 조건을 만족할 수 없다. 따라서, 여기서는 2, 3번 경우만 고려해야 한다.
 그리고 어차피 1번 경우가 가장 큰 값이었다면 max에 남아있기 때문에 현재 원소와 비교할 필요가 없음
 */
public class MaximumSubarray {

    public static void main(String[] args) {

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(solve(nums));
    }

    private static int solve(int[] nums) {

        int max = nums[0];
        int sum = nums[0];

        for (int i=1; i<nums.length; i++) {
            sum = Math.max(sum+nums[i], nums[i]);   // 기존 원소(or 합) + 현재 원소 vs 현재 원소
            max = Math.max(max, sum);   // 기존 원소(or 합)가 가장 큰 값이었다면 max값이 바뀌지 않을 것임
        }

        return max;
    }
}
