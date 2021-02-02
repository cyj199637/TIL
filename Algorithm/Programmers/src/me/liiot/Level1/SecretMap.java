package me.liiot.Level1;

import java.util.Stack;

/*
비밀지도

- 이진수 만드는 메소드: Integer.toBinaryString
 */
public class SecretMap {

    public static void main(String[] args) {

        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};
        String[] result = solution(5, arr1, arr2);
        for (String s : result) {
            System.out.println(s);
        }
    }
    public static String[] solution(int n, int[] arr1, int[] arr2) {

        String[] result = new String[n];
        if (n==0 || arr1.length==0 || arr1==null || arr2.length==0 || arr2==null)
            return result;

        String[] str1 = decode(n, arr1);
        String[] str2 = decode(n, arr2);

        result = merge(n, str1, str2);

        return result;
    }

    private static String[] decode(int n, int[] arr) {

        String[] str = new String[n];
        int index = 0;
        Stack<String> stack = new Stack<>();
        for (int i : arr) {
            while (i != 0 || stack.size() < n) {
                if (i == 0)
                    stack.add(" ");
                else {
                    String s = i % 2 == 0 ? " " : "#";
                    stack.add(s);
                    i /= 2;
                }
            }

            String decode = "";
            while (!stack.isEmpty()) {
                decode += stack.pop();
            }
            str[index] = decode;
            stack.clear();
            index++;
        }

        return str;
    }

    private static String[] merge(int n, String[] str1, String[] str2) {

        String[] result = new String[n];
        for (int i=0; i<n; i++) {
            char[] c = new char[n];
            for (int j=0; j<n; j++) {
                c[j] = (str1[i].charAt(j) == ' ' && str2[i].charAt(j) == ' ') ? ' ' : '#';
            }
            result[i] = String.valueOf(c).replaceAll("[^#]{2,16}", " ");
        }

        return result;
    }
}
