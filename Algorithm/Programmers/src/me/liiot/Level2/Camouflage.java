package me.liiot.Level2;

import java.util.*;

/*
위장
 */
public class Camouflage {

    public static void main(String[] args) {

        String[][] clothes = {
                {"yellow_hat", "headgear"},
                {"blue_sunglasses", "eyewear"},
                {"green_turban", "headgear"}
        };
        System.out.println(solution(clothes));
    }

    public static int solution(String[][] clothes) {

        Map<String, Integer> categories = new HashMap<>();
        for (String[] s : clothes) {
            String category = s[1];
            categories.put(category, categories.getOrDefault(category, 0)+1);
        }

        Collection<Integer> values = categories.values();
        int result = 1;
        for (Integer i : values) {
            result *= (i+1);
        }

        return result - 1;
    }
}
