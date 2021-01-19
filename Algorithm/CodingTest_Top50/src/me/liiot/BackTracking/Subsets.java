package me.liiot.BackTracking;

import java.util.ArrayList;
import java.util.List;

/*
45) Subsets
주어진 배열에 대한 모든 부분집합을 구하세요.
 */
public class Subsets {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3};

        List<List<Integer>> result = solve(nums);

        for (List<Integer> l : result) {
            for (int i : l) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    private static List<List<Integer>> solve(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if (nums==null || nums.length==0)
            return result;
        
        subsets(result, list, nums, 0);

        return result;
    }

    private static void subsets(List<List<Integer>> result, List<Integer> list, int[] nums, int start) {

        List<Integer> subset = new ArrayList<>(list);
        result.add(subset);

        for (int i=start; i<nums.length; i++) {
            list.add(nums[i]);
            subsets(result, list, nums, i+1);
            list.remove(list.size()-1);
        }
    }
}
