package com.algorithm.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//TODO:Morris 遍历

/**
 * @description: leetcode-94:二叉树的中序遍历
 * @author: BadGuy
 * @date: 2021-10-23 10:52
 **/
public class ProblemNo94 {

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
     * 递归写法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        // 声明结果集合
        List<Integer> result = new ArrayList<>();
        // 判空
        if (root == null) {
            return result;
        }
        // 中序遍历：先遍历左子节点，再遍历自己，最后遍历右子节点
        result.addAll(inorderTraversal1(root.left));
        result.add(root.val);
        result.addAll(inorderTraversal1(root.right));
        return result;
    }

    /**
     * 使用栈迭代
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        // 声明结果集合
        List<Integer> result = new ArrayList<>();
        // 判空
        if (root == null) {
            return result;
        }
        // 声明栈
        Deque<TreeNode> deque = new ArrayDeque<>();
        // 当前节点为根节点
        TreeNode current = root;
        // 当栈不为空或者当前节点不为null，则继续遍历
        while (!deque.isEmpty() || current != null) {
            // 当当前节点不为空时，入栈，并循环遍历左子节点
            while (current != null) {
                deque.push(current);
                current = current.left;
            }
            // 弹出栈顶元素
            TreeNode topNode = deque.pop();
            // 将栈顶元素值加入结果集合
            result.add(topNode.val);
            // 将当前节点赋值为当前节点的右子节点
            current = topNode.right;
        }
        return result;
    }

}
