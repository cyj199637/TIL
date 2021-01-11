package me.liiot.QueueAndStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
26) BinaryTree Level Order

- 이 문제는 BFS 문제 중 기본 문제
- Queue는 BFS 문제 풀이에서, Stack은 DFS 문제 풀이에서 주로 사용된다.
 */
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode (int x) {
        this.val = x;
    }
}

public class BinaryTree {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(7);

        System.out.println(solve(root));
    }

    private static List<List<Integer>> solve(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();    // size를 미리 선언하지 않고 반복문에서 매번 불러와서 사용하면
            List<Integer> list = new LinkedList<>();
            for (int i=0; i<size; i++) {    // 반복문 중간에 queue에 노드를 추가하는 코드 때문에
                                            // queue 사이즈가 달라져 원하는 대로 동작하지 않게 된다.
                TreeNode node = queue.poll();
                list.add(node.val);

                if (node.left != null)
                    queue.add(node.left);

                if (node.right != null)
                    queue.add(node.right);
            }
            result.add(list);
        }

        return result;
    }
}
