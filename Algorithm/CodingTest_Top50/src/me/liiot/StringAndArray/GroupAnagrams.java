package me.liiot.StringAndArray;

import java.util.*;

/*
17) Group Anagrams
문자열 배열이 주어졌을 때, 아나그램이 같은 문자열끼리 그룹으로 만드세요.
 */
public class GroupAnagrams {

    public static void main(String[] args) {

        String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> result = solve(strings);
        for (int i=0; i<result.size(); i++) {
            for (int j=0; j<result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    private static List<List<String>> solve(String[] strings) {

        List<List<String>> result = new ArrayList<>();
        if (strings == null && strings.length == 0)
            return result;

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);

            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                List<String> value = new ArrayList<>();
                value.add(str);
                map.put(key, value);
            }
        }
        result.addAll(map.values());

        return result;
    }
}
