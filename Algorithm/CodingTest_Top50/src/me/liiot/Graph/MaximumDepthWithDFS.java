package me.liiot.Graph;

import java.util.Stack;

/*
30) Maximum Depth Of BinaryTree with DFS
주어진 이진 트리의 최대 깊이를 찾으세요.
최대 깊이는 루트 노드부터 리프 노드까지의 경로 중 가장 긴 경로에 대한 노드 수 입니다.

- 노드들을 스택에 저장하여 깊이 우선으로 Depth 카운트
- 스택에 저장된 노드와 같은 순서로 각 노드 레벨을 스택에 저장
why? 각 노드의 레벨 순이 아니라 나중에 들어온 노드부터 탐색하기 때문에 각 노드의 레벨을 따로 저장해줘야 함
     스택은 맵처럼 키-값 형식이 아니니 레벨을 담을 다른 스택을 제공해야 하는 것
     루프마다 Math.max()로 카운트 값을 비교하는 이유도 이 때문.
- 각 노드가 가진 노드 레벨 값을 바탕으로 카운트 값 갱신
 */
public class MaximumDepthWithDFS {

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

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> valueStack = new Stack<>();
        nodeStack.push(root);
        valueStack.push(1);
        int max = 0;

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int count = valueStack.pop();
            max = Math.max(max, count);

            if (node.left != null) {
                nodeStack.push(node.left);  // 현재 노드에 연결된 노드가 있다면 반목문을 계속 하기 위해 연결된 노드를 스택에 다시 저장
                valueStack.push(count + 1);  // nodeStack에 담긴 노드들과 상태를 같이 맞춰야 하므로 연결된 노드에 대한 레벨도 다시 저장
                                                  // 연결된 노드가 없다면 if 문을 통과하지 못하므로 노드도 추가되지 않고, 레벨도 증가하지 않음
            }
            if (node.right != null) {
                nodeStack.push(node.right);
                valueStack.push(count + 1);
            }
        }

        return max;
    }
}
