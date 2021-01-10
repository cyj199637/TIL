package me.liiot.StringAndArray;

import java.util.ArrayList;
import java.util.List;

/*
20) Missing Ranges
범위 안에 있는 숫자들을 가진 정렬된 정수 배열 nums가 주어집니다.
이때 범위 안에 포함되어 있지만 배열에는 없는 범위의 수를 반환하세요.

- 이 문제에서는 세 가지 경우를 생각해야 한다.
1. lower와 배열의 첫 번째 원소간 의 비교
: lower가 첫 번재 원소보다 작은 경우, 누락 범위를 계산해야 한다.
2. 원소들 간의 비교
: 현재 원소가 다음 원소와 같지 않으면서 연속되지 않은 값인 경우, 누락 범위를 계산해야 한다.
3. 배열의 마지막 원소와 upper간의 비교
: 마지막 원소가 upper보다 작은 경우, 누락 범위를 계산해야 한다.
 */
public class MissingRanges {

    public static void main(String[] args) {

        int[] nums = {0, 1, 3, 50, 75};
        int lower = 0;
        int upper = 99;

        List<String> result = solve(nums, lower, upper);
        for (String str : result) {
            System.out.print(str + " ");
        }

        int[] nums2 = {2, 3, 5, 37, 68, 91};
        List<String> result2 = solve(nums2, lower, upper);
        for (String str : result2) {
            System.out.print(str + " ");
        }
    }

    private static List<String> solve(int[] nums, int lower, int upper) {

        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return result;

        if (lower < nums[0]) {
            result.add(makeRange(lower, nums[0]-1));
        }

        for (int i=0; i<nums.length-1; i++) {
            // 현재 원소가 다음 원소와 같지 않으면서 연속되지 않은 값인 경우
            if (nums[i] != nums[i+1] && nums[i]+1 < nums[i+1]) {
                result.add(makeRange(nums[i]+1, nums[i+1]-1));
            }
        }

        if (nums[nums.length-1] < upper) {
            result.add(makeRange(nums[nums.length-1]+1, upper));
        }

        return result;
    }

    private static String makeRange(int lower, int upper) {

        return lower==upper ? String.valueOf(lower) : (lower+"->"+upper);
    }
}
