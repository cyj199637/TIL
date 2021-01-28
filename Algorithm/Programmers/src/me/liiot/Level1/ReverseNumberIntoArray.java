package me.liiot.Level1;

import java.util.ArrayList;
import java.util.List;

/*
자연수 뒤집어 배열로 만들기
 */
public class ReverseNumberIntoArray {

    public static void main(String[] args) {

        List<Integer> result = solution(12345);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    private static List<Integer> solution(int n) {

        List<Integer> list = new ArrayList<>();
        while (n != 0) {
            list.add((n % 10));
            n /= 10;
        }

        return list;
    }
}
