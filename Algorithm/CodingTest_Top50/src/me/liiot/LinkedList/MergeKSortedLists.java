package me.liiot.LinkedList;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
22) Merge K Sorted Lists
배열에 담긴 정렬된 k개의 Linked List를 병합하여 하나의 정렬된 리스트로 반환하세요.
 */
public class MergeKSortedLists {

    public static void main(String[] args) {

        MergeKSortedLists mergeK = new MergeKSortedLists();

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] nodes = {l1, l2, l3};

        mergeK.print(mergeK.solve(nodes));
        mergeK.print(mergeK.solve2(nodes));
    }

    // 노드 하나하나 다 분리해서 PriorityQueue에 넣은 다음 작은 순서대로 빼내서 결과값에 저장하기
    private ListNode solve(ListNode[] nodes) {

        ListNode result = new ListNode(0);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comp);

        for (ListNode node : nodes) {
            ListNode temp = node;
            while (temp != null) {
                pq.offer(temp.val);
                temp = temp.next;
            }
        }

        ListNode temp = result;
        while (pq.size() > 0) {
            temp.next = new ListNode(pq.poll());
            temp = temp.next;
        }

        return result.next;
    }

    Comparator<Integer> Comp = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    };

    // 연결된 노드 통째로 PriorityQueue에 넣고 작은 순서대로 빼내는데
    // 빼낸 노드의 가장 상위 노드만 분리시켜 결과값에 저장하고 나머지 연결된 노드들은 아직 정렬이 안된 상태이므로
    // 다시 PriorityQueue에 저장
    private ListNode solve2(ListNode[] nodes) {

        ListNode result = new ListNode(0);
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comp2);

        for (ListNode node : nodes) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode temp = result;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            temp.next = node;
            temp = temp.next;

            if (node.next != null) {
                pq.offer(node.next);
            }
        }

        return result.next;
    }

    Comparator<ListNode> Comp2 = new Comparator<ListNode>() {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    };

    private void print(ListNode target) {

        ListNode node = target;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}
