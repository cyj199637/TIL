package me.liiot.Graph;

import java.util.LinkedList;
import java.util.Queue;

/*
31) Maximum Depth Of BinaryTree with BFS
주어진 이진 트리의 최대 깊이를 찾으세요.
최대 깊이는 루트 노드부터 리프 노드까지의 경로 중 가장 긴 경로에 대한 노드 수 입니다.

- 노드들을 큐에 저장하여 너비 우선으로 Depth 카운트
- DFS와 달리 레벨 순으로 노드들의 레벨을 카운트한다.
 즉, 어떤 노드가 연결된 노드를 가지고 있더라도 현재 노드와 같은 레벨의 노드를 탐색할 때까지 반복이 끝나지 않는다.
- 따라서, 매번 Math.max()로 카운트 값을 비교할 필요 없이 루프가 끝나고 나면 카운트 값을 증가시키면 된다.
 */
public class MaximumDepthWithBFS {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(solve(root));
    }

    private static int solve(TreeNode root) {

        if (root == null)
            return 0;

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        int count = 0;

        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();    // 현재 Depth에 있는 노드 수

            for (int i=0; i<size; i++) {
                TreeNode node = nodeQueue.poll();
                if (node.left != null) {
                    nodeQueue.add(node.left);
                }
                if (node.right != null) {
                    nodeQueue.add(node.right);
                }
            }
            count++;
        }

        return count;
    }
}
