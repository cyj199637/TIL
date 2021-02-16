package me.liiot.Level2;

import java.util.Arrays;

/*
최댓값과 최솟값
 */
public class MaxAndMin {

    public static void main(String[] args) {

        System.out.println(solution("-1 -2 -3 -4"));
    }

    public static String solution(String s) {

        String[] values = s.split(" ");
        int[] nums = new int[values.length];
        for (int i=0; i<values.length; i++) {
            nums[i] = Integer.parseInt(values[i]);
        }

        Arrays.sort(nums);
        return nums[0] + " " + nums[nums.length - 1];
    }
}
