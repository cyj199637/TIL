package me.liiot.StringAndArray;

/*
02) Move Zeros
주어진 배열에서 0이 아닌 요소들은 현재 순서를 유지하고
모든 0만 끝으로 이동시키는 함수를 작성하세요.
 */
public class MoveZeros {

    public static void main(String[] args) {

        int[] nums = {0, 3, 2, 0, 8, 5};
        int index = 0;

        for (int i=0; i<nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }

        for (int j=index; j<nums.length; j++) {
            nums[j] = 0;
        }

        for (int z=0; z<nums.length; z++) {
            System.out.print(nums[z] + " ");
        }
    }
}
