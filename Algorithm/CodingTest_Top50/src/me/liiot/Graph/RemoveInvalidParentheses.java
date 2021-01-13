package me.liiot.Graph;

import java.util.*;

/*
35) Remove Invalid Parentheses
주어진 문자열에서 최소한의 괄호만을 제거하여 올바른 괄호를 반환하세요.
가능한 모든 결과를 반환하세요.
참고:
올바른 괄호란 ( 와 )가 서로 개수가 같고, (())() 처럼 짝이 맞는 괄호를 말합니다.
문자열에는 괄호 ( 나 ) 이외의 문자가 포함될 수 있습니다.

1. 최초 문자열을 큐에 저장하고 시작
2. 큐에서 문자열을 꺼내고 해당 문자열이 올바른 괄호를 가졌는지 확인
3-1. 올바른 괄호라면 더 이상 추가 탐색 작업을 하지 않고 결과에 저장
3-2. 올바르지 않은 괄호라면 추가 탐색 작업 시작
4. 대상 문자열에서 문자를 하나씩 제거하여 새로운 문자열 생성
5. 새로운 문자열이 이미 탐색 대상으로 저장되어 있는지 확인
6-1. Set에 이미 들어 있으면 스킵
6-2. Set에 들어있지 않으면 탐색 대상으로써 큐에 저장
 */
public class RemoveInvalidParentheses {

    public static void main(String[] args) {

        String p1 = "()())()";
        String p2 = "(a)())()";
        String p3 = ")(";

        print(solve(p1));
        print(solve(p2));
        print(solve(p3));
    }

    private static List<String> solve(String p) {

        List<String> result = new ArrayList<>();
        if (p == null)
            return result;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(p);
        visited.add(p);     // 큐에는 중복 제거 기능이 없으므로 Set을 하나 만들어 여기서 중복 여부를 판단
        boolean found = false;      // 최소한의 괄호만 제거하는 것이므로 결과에 추가된 문자열은 더 이상 탐색 작업에 들어가면 안된다.
                                    // 따라서, found 값으로 추가 탐색 작업에 들어갈지 말지 판단해야 한다.

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                String str = queue.poll();

                if (isValid(str)) {     // 큐에서 꺼낸 문자열이 올바른 괄호를 가졌다면 결과에 추가
                    result.add(str);
                    found = true;
                }

                if (found) continue;    // 결과에 추가된 문자열은 추가 탐색 작업 안함

                for (int j=0; j<str.length(); j++) {
                    if (str.charAt(j)!='(' && str.charAt(j)!=')')
                        continue;

                    String newStr = str.substring(0, j) + str.substring(j+1);
                    if (!visited.contains(newStr)) {
                        queue.add(newStr);
                        visited.add(newStr);
                    }
                }
            }
        }

        return result;
    }

    private static boolean isValid(String str) {

        char[] chars = str.toCharArray();
        int count = 0;  // 남아있는 괄호가 있는지 없는지 판단하는 용도
        for (char c : chars) {
            if (c == '(') {
                count++;
            } else if (c == ')') {  // 바깥 조건문은 ()도 true, )(도 true가 된다. 그러나 )(는 짝이 안 맞는 경우이므로
                count--;
                if (count < 0)  // 이 조건문을 추가하여 )가 먼저 나오는 경우, 즉 음수가 되는 경우를 걸러낸다.
                    return false;
            }
        }

        return (count == 0);
    }

    private static void print(List<String> result) {

        for (String i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
