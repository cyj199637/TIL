package me.liiot.Level2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
큰 수 만들기
 */
public class CreateBigNumber {

    public static void main(String[] args) {

        System.out.println(solution("1231234", 3));
    }

    public static String solution(String s, int k) {

        List<Long> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] nums = s.toCharArray();

        subsets(result, sb, nums, 0, k);

        result.sort(Comparator.naturalOrder());
        return result.get(result.size()-1).toString();
    }

    private static void subsets(List<Long> result, StringBuilder sb, char[] nums, int start, int k) {

        if (sb.length() > 0) {
            long num = Long.parseLong(sb.toString());
            if ((int) (Math.log10(num) + 1) == nums.length - k)
                result.add(num);
        }

        for (int i=start; i<nums.length; i++) {
            sb.append(nums[i]);
            subsets(result, sb, nums, i+1, k);
            sb.delete(sb.length()-1, sb.length());
        }
    }
}
