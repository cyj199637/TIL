package me.liiot.string_array;

/*
03) TwoSum
여러 숫자가 담긴 배열과 타깃 값이 주어집니다.
주어진 배열에서 두 개의 숫자를 고르고 이 둘을 더했을 때의 결과가 타깃 값과 같다면
그 두 숫자의 인덱스를 리턴하세요.

결과가 타깃 값이 되는 두 숫자의 조합은 하나뿐이며, 같은 숫자를 두 번 더할 수는 없습니다.
 */

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {

        int[] nums = {2, 5, 8, 11, 21};
        int target = 10;

        TwoSum twoSum = new TwoSum();
        int[] result = twoSum.solve(nums, target);

        System.out.print(result[0] + " " + result[1]);
    }

    private int[] solve(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];

        for (int i=0; i<nums.length; i++) {
            int diff = target - nums[i];

            if (diff == nums[i]) {
                continue;
            }

            if (map.containsKey(nums[i])) {
                result[0] = map.get(nums[i]);
                result[1] = i;
                break;
            } else {
                map.put(diff, i);     // key: 현재 원소와 타깃 값의 차(앞으로 있는지 없는지 확인해야 하는 값)
                                      // value: 현재 원소 인덱스
            }
        }

        return result;
    }
}
