package me.liiot.LinkedList;

import java.util.Stack;

/*
23) Reverse LinkedLists
주어진 LinkedList를 거꾸로 정렬하세요.
 */
public class ReverseLinkedLists {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);

        print(solve(l1));
    }

    // 아래 풀이처럼 단순히 역순으로 가지고와서 이들을 연결시키면 서로가 서로를 가르키게 되어 무한반복 발생
    private static ListNode wrongSolve(ListNode l1) {

        ListNode result = new ListNode(0);
        ListNode p1 = l1;

        Stack<ListNode> stack = new Stack<>();
        while (p1 != null) {
            if (p1 != null) {
                stack.push(p1);
                p1 = p1.next;
            }
        }

        ListNode temp = result;
        while (stack.size() > 0) {
            temp.next = stack.pop();
            temp = temp.next;
        }

        return result.next;
    }

    private static ListNode solve(ListNode current) {

        ListNode prev = null;   // 현재 원소에 연결할 원소
        ListNode next = null;   // 다음 루프 대상 원소
        while (current != null) {
            if (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;     // 현재 원소를 다음 루프 대상 원소로 바꾸기
            }
        }

        return prev;
    }

    private static void print(ListNode target) {

        ListNode node = target;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}
