package com.algorithm.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @description: leetcode-199:二叉树的右视图
 * @author: BadGuy
 * @date: 2021-10-23 15:29
 **/
public class ProblemNo199 {

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

    /**
     * 大致思路，先遍历右子树，并记录最右边已有可看到的节点层数
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        // 声明结果集合
        List<Integer> result = new ArrayList<>();
        // 判空
        if (root == null) {
            return result;
        }
        // 声明栈：用来存储节点，用于遍历
        Deque<TreeNode> nodeDeque = new ArrayDeque<>();
        // 声明栈：用来存储对应遍历的level值
        Deque<Integer> levelDeque = new ArrayDeque<>();
        // 当前节点
        TreeNode currentNode = root;
        // 当前层级
        Integer level = 0;
        while (currentNode != null || !nodeDeque.isEmpty()) {
            // 先遍历右节点
            while (currentNode != null) {
                nodeDeque.push(currentNode);
                result.add(currentNode.val);
                levelDeque.push(level);
                currentNode = currentNode.right;
                level++;
            }
            TreeNode topNode = nodeDeque.pop();
            currentNode = topNode.left;
        }
    }

}
