package com.algorithm.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//TODO:Morris 遍历

/**
 * @description: leetcode-145:二叉树的后序遍历
 * @author: BadGuy
 * @date: 2021-10-23 10:54
 **/
public class ProblemNo145 {

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
    public List<Integer> postorderTraversal1(TreeNode root) {
        // 声明结果集合
        List<Integer> result = new ArrayList<>();
        // 判空
        if (root == null) {
            return result;
        }
        // 后序遍历：先遍历左子节点，再遍历右子节点，最后遍历自己
        result.addAll(postorderTraversal1(root.left));
        result.addAll(postorderTraversal1(root.right));
        result.add(root.val);
        return result;
    }

    /**
     * 利用栈，缺点：会改变原树的结构
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        // 声明结果集合
        List<Integer> result = new ArrayList<>();
        // 判空
        if (root == null) {
            return result;
        }
        // 声明栈
        Deque<TreeNode> deque = new ArrayDeque<>();
        // 根节点压栈
        deque.push(root);
        while (!deque.isEmpty()) {
            // 获取栈顶元素，但不弹出
            TreeNode topNode = deque.peek();
            // 若栈顶元素左右子节点都为空，则弹出，并加入结果集合
            if (topNode.left == null && topNode.right == null) {
                deque.pop();
                result.add(topNode.val);
            }
            // 若右子节点不为空，则右子节点压栈（先右后左）
            if (topNode.right != null) {
                deque.push(topNode.right);
                topNode.right = null;
            }
            // 若左子节点不为空，则左子节点压栈
            if (topNode.left != null) {
                deque.push(topNode.left);
                topNode.left = null;
            }
        }
        return result;
    }

    /**
     * 利用栈，不改变原树结构
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // 声明结果集合
        List<Integer> result = new ArrayList<>();
        // 判空
        if (root == null) {
            return result;
        }
        // 声明栈
        Deque<TreeNode> deque = new ArrayDeque<>();
        // 记录上一次出栈的节点
        TreeNode pre = null;
        // 当前遍历节点不为空，或者栈不为空，则继续迭代
        while (root != null || !deque.isEmpty()) {
            // 先将左子节点统统压栈
            while (root != null) {
                deque.push(root);
                root = root.left;
            }
            // 此时栈顶元素可能为下一个入结果集合的元素
            TreeNode topNode = deque.pop();
            // 如果栈顶元素的右子节点为空，或者栈顶元素的右子节点为上一个出栈的元素,则可以入结果集合
            if (topNode.right == null || topNode.right == pre) {
                result.add(topNode.val);
                pre = topNode;
                root = null; // 我下面没有子节点了
            } else { // 否则不可出栈，再入栈一次，我自己下面还有节点，不能先走
                deque.push(topNode);
                root = topNode.right;  // 我下面还有右节点
            }
        }
        return result;
    }

}
