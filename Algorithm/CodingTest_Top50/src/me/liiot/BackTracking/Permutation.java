package me.liiot.BackTracking;

import java.util.ArrayList;
import java.util.List;

/*
44) Permutation
주어진 배열의 원소로 만들 수 있는 모든 배열을 구하세요.
 */
public class Permutation {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3};

        List<List<Integer>> result = solve(arr);

        System.out.print("Result: ");
        for (List<Integer> i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static List<List<Integer>> solve(int[] arr) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if (arr==null || arr.length==0)
            return result;

        permutate(result, list, arr);

        return result;
    }


    private static void permutate(List<List<Integer>> result, List<Integer> list, int[] arr) {

        if (list.size() == arr.length) {
            List<Integer> e = new ArrayList<>(list);
            result.add(e);
        }

        for (int i=0; i<arr.length; i++) {
            if (list.contains(arr[i]))
                continue;

            list.add(arr[i]);
            print(list);
            permutate(result, list, arr);
            list.remove(list.size()-1);
        }
    }

    private static void print(List<Integer> list) {

        System.out.print("Current list: ");
        for (int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
