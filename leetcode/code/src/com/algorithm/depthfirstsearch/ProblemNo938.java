package com.algorithm.depthfirstsearch;

/**
 * @Level：simple
 * @Description: leetcode938-二叉搜索树的范围和
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 * @Author: zhangjunqiang
 * @Date: 2021/6/17
 */
public class ProblemNo938 {

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null)
            return 0;
        // 因二叉搜索树特性，若当前节点已大于最大值，则无需遍历右子树
        if (root.val > high)
            return rangeSumBST(root.left, low, high);
        if (root.val < low)
            return rangeSumBST(root.right, low, high);
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }

    static class TreeNode {
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

}
