package me.liiot.Level2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
카펫
 */
public class Carpet {

    public static void main(String[] args) {

        int[] result = solution(10, 2);
        System.out.println(result[0] + " " + result[1]);
    }

    public static int[] solution(int brown, int yellow) {

        List<Integer> factors = new ArrayList<>();
        int limit = (yellow % 2) == 0 ? (yellow / 2) : (yellow / 2) + 1;
        for (int i=1;  i<=limit; i++) {
            if ((yellow % i) == 0 && (yellow / i) >= i) {
                factors.add(yellow / i);
            }
        }

        factors.sort(Comparator.naturalOrder());
        int total = brown + yellow;
        for (int i=0; i<factors.size(); i++) {
            int h = factors.get(i) + 2;
            if ((total % h) == 0) {
                return new int[]{h, total / h};
            }
        }

        return new int[]{0, 0};
    }
}
