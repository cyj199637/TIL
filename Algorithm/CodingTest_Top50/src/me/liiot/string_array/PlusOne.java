package me.liiot.string_array;

/*
10) Plus One
음이 아닌 정수가 들어 있는 숫자 배열이 주어집니다. 이 배열의 현재 정수 값에서 1을 더한 정수 값으로 나타내세요.
배열의 각 요소는 한 자리 숫자이며 배열의 가장 앞에 있는 숫자가 가장 큰 자릿수를 의미합니다.
배열이 의미하는 정수는 0으로 시작하지 않는다고 가정합니다.
 */
public class PlusOne {

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3};
        int[] nums2 = {9, 9, 9};
        int[] nums3 = {2, 9, 9};

        print(solve(nums1));
        print(solve(nums2));
        print(solve(nums3));
    }

    private static int[] solve(int[] nums) {

        int carry = 1;
        for (int i=(nums.length-1); i>=0; i--) {
            if (carry == 0) break;

            nums[i] = (nums[i] + 1) % 10;

            if (nums[i] == 0) {
                carry = 1;
            } else {
                carry = 0;
            }
        }

        if (carry == 1) {
            nums = new int[nums.length + 1];
            nums[0] = 1;
        }

        return nums;
    }

    private static void print(int[] result) {
        for (Integer i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
