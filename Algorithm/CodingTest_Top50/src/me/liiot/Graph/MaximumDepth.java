package me.liiot.Graph;

/*
29) Maximum Depth Of BinaryTree
주어진 이진 트리의 최대 깊이를 찾으세요.
최대 깊이는 루트 노드부터 리프 노드까지의 경로 중 가장 긴 경로에 대한 노드 수 입니다.
 */
class TreeNode {

    int val;
    TreeNode left, right;

    TreeNode(int x) {
        this.val = x;
    }
}

public class MaximumDepth {

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

        int leftMax = solve(root.left);
        int rightMax = solve(root.right);

        return Math.max(leftMax, rightMax)+1;   // 해당 노드의 왼쪽과 오른쪽 중 더 긴 길이에 +1로 자신까지 더한다.
    }
}
