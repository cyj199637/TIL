package me.liiot.LinkedList;

/*
21) Add Two Numbers
음이 아닌 정수가 담긴 두 개의 Linked List가 주어집니다.
숫자는 역순으로 저장되며 각 노드에는 숫자 하나씩 포함됩니다.
두 개의 배열에 담긴 숫자를 더하고 이에 대한 결과를 Linked List에 역순으로 저장해서 반환하세요.
배열에 담긴 숫자는 0으로 시작하지 않습니다.
 */
class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        this.val = x;
    }
}

public class AddTwoNumbers {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        print(solve(l1, l2));
    }

    private static ListNode solve(ListNode l1, ListNode l2) {

        // Dummy Data
        ListNode newHead = new ListNode(0);
        ListNode p1 = l1, p2 = l2, p3 = newHead;    // ListNode 객체에 데이터 조작이 일어나기 때문에 복사해서 쓰는 것이 좋음

        int carry = 0;
        while (p1 != null || p2 != null) {      // 가장 마지막 노드의 next는 null이기 때문
            if (p1 != null) {
                carry += p1.val;
                p1 = p1.next;
            }

            if (p2 != null) {
                carry += p2.val;
                p2 = p2.next;
            }

            // p3에 newHead를 복사했기 때문에 이 둘은 서로 같은 객체를 바라보고 있으므로
            // p3가 변경되면 newHead도 변경된다.
            p3.next = new ListNode(carry % 10);
            print(newHead);
            p3 = p3.next;
            carry /= 10;
        }

        if (carry != 0) {
            p3.next = new ListNode(carry);
        }

        return newHead.next;
    }

    private static void print(ListNode target) {
        ListNode nodes = target;
        while (nodes != null) {
            System.out.print(nodes.val + " ");
            nodes = nodes.next;
        }
        System.out.println();
    }
}
