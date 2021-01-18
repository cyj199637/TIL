package me.liiot.BackTracking;

import java.util.ArrayList;
import java.util.List;

/*
43) Generate Parentheses
주어진 개수만큼의 괄호 쌍을 가진 모든 문자열을 구하세요.

- DFS로 문제 풀기
 */
public class GenerateParentheses {

    public static void main(String[] args) {

        int goal = 3;

        List<String> result = solve(goal);
        for (String s : result) {
            System.out.print(s+" ");
        }
        System.out.println();
    }

    private static List<String> solve(int goal) {

        List<String> result = new ArrayList<>();
        if (goal==0) return result;

        generate(result, goal, goal, "");

        return result;
    }

    private static void generate(List<String> result, int left, int right, String s) {

        System.out.println("s\t"+s+"\t left: "+left+" right: "+right);

        if (left<0 || left>right)
            return;

        System.out.println("=s\t"+s+"\t left: "+left+" right: "+right);
        if (left==0 && right==0) {
            result.add(s);
            return;
        }

        generate(result, left-1, right, s+"(");
        generate(result, left, right-1, s+")");
    }
}
