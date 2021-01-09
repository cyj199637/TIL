package me.liiot.string_array;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
14) Find Anagrams Mapping
두 개의 배열 A와 B가 주어지고, 여기서 B는 A의 애너그램입니다.
'B는 A의 애너그램이다'라는 것은 B는 A의 원소 순서를 무작위로 섞어 만들어졌다는 것을 의미합니다.
A에서 B로의 인덱스 매핑 P를 찾고 싶습니다. 매핑 P[i] = j는 A의 i번째 요소가 B의 j번째에 나타남을 의미합니다.
A와 B는 중복된 원소를 포함할 수 있습니다. 답변이 여러 개인 경우 그중 하나를 출력합니다.
 */
public class FindAnagramsMapping {

    public static void main(String[] args) {

        int[] a = {12, 28, 46, 32, 50};
        int[] b = {50, 12, 32, 46, 28};

        int [] result = solve(a, b);
        print(result);

        int [] result2 = solve2(a, b);
        print(result2);
    }

    private static int[] solve(int[] a, int[] b) {

        List<Integer> list = new ArrayList<>();
        for (int i : b) {
            list.add(i);
        }

        int[] result = new int[a.length];
        for (int i=0; i<a.length; i++) {
            result[i] = list.indexOf(a[i]);
        }

        return result;
    }

    private static int[] solve2(int[] a, int[] b) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<b.length; i++) {
            map.put(b[i], i);
        }

        int[] result = new int[a.length];
        for (int i=0; i<a.length; i++) {
            result[i] = map.get(a[i]);
        }

        return result;
    }

    private static void print(int[] result) {

        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
