package me.liiot.StringAndArray;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
19) Kth Largest Element In An Array
정렬되지 않은 배열에서 k 번째로 큰 요소를 찾으세요.
정렬된 순서에서 k번째 원소가 아니라 k 번째로 큰 요소라는 점을 주의하세요.
k는 1부터 주어진 배열의 크기 사이의 값입니다.
 */
public class KthLargest {

    public static void main(String[] args) {

        KthLargest kthLargest = new KthLargest();

        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;

        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;

        System.out.println(kthLargest.solve(nums1, k1));
        System.out.println(kthLargest.solve(nums2, k2));

        System.out.println(kthLargest.solve2(nums1, k1));
        System.out.println(kthLargest.solve2(nums2, k2));
    }

    private int solve(int[] nums, int k) {

        if (nums == null || nums.length == 0)
            return 0;

        Arrays.sort(nums);

        return nums[nums.length - k];
    }

    private int solve2(int[] nums, int k) {

        if (nums == null || nums.length == 0)
            return 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comp);
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        return pq.peek();
    }

    Comparator<Integer> Comp = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    };
}
