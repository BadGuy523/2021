package com.algorithm.binarytree;

/**
 * @description: leetcode-110:平衡二叉树
 * @author: BadGuy
 * @date: 2021-10-24 10:20
 **/
public class ProblemNo110 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return maxDepth(root) != -1;
    }

    // 递归获取每个节点的最大深度，如果某两个节点的深度差大于1，则返回-1
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        if (leftDepth == -1 || rightDepth == -1 || Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        } else {
            return Math.max(leftDepth,rightDepth) + 1;
        }
    }

}
