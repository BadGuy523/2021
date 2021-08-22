package com.algorithm.depthfirstsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @Level：simple
 * @Description: 897. 递增顺序搜索树
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，
 * 使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * @Author: zhangjunqiang
 * @Date: 2021/6/22
 */
public class ProblemNo897 {


    public TreeNode increasingBST(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        collectValues(root, values);
        // 重新生成目标树
        TreeNode result = new TreeNode();
        TreeNode currNode = result;
        for (Integer value : values) {
            TreeNode treeNode = new TreeNode();
            treeNode.val = value;
            currNode.right = treeNode;
            currNode = treeNode;
        }
        return result.right;
    }

    /**
     * 按中序遍历树，按序保存节点数据
     *
     * @param root
     * @param values
     */
    private void collectValues(TreeNode root, List<Integer> values) {
        if (root == null)
            return;
        collectValues(root.left, values);
        values.add(root.val);
        collectValues(root.right, values);
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
