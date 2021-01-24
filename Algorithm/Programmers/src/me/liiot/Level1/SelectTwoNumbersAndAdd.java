package me.liiot.Level1;

import java.util.*;

/*
두 개 뽑아서 더하기
 */
public class SelectTwoNumbersAndAdd {

    public static void main(String[] args) {

        SelectTwoNumbersAndAdd select = new SelectTwoNumbersAndAdd();

        int[] nums1 = {2, 1, 3, 4, 1};
        int[] nums2 = {5, 0, 2, 7};

        Set<Integer> result = select.solution(nums2);
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public Set<Integer> solution(int[] nums) {

//        List<Integer> result = new ArrayList<>();
        Set<Integer> result = new TreeSet<>();
        if (nums==null || nums.length==0)
            return result;

        for (int i=0; i<nums.length-1; i++) {
            for (int j=i+1; j<nums.length; j++) {
                int sum = nums[i] + nums[j];
//                if (!result.contains(sum))
                    result.add(sum);
            }
        }

//        result.sort((a, b) -> a - b);

        return result;
    }
}
