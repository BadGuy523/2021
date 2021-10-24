package com.algorithm.binarytree;

/**
 * @description: leetcode-236:二叉树的最近公共祖先
 * @author: BadGuy
 * @date: 2021-10-24 09:59
 **/
public class ProblemNo236 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 根节点为null，或者要找的某一节点就为根节点，则它为最近公共祖先
        if (root == null || root == p || root == q) {
            return root;
        }
        // 以根节点的左右子节点作为根节点递归寻找
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 如果左右子节点均能找到其祖先，则说明root为其公共祖先
        if (left != null && right != null) {
            return root;
        }
        // 如果左子节点找不到其祖先，则说明两个节点全在右子树上，返回右子树
        if (left == null) {
            return right;
        }
        // 如果右子节点找不到其祖先，则说明两个节点全在左子树上，返回左子树
        if (right == null) {
            return left;
        }
        return null;
    }

}
