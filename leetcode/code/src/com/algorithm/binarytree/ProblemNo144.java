package com.algorithm.binarytree;

import java.util.*;

//TODO:Morris 遍历

/**
 * @description: leetcode-144:二叉树的前序遍历
 * @author: BadGuy
 * @date: 2021-10-23 10:42
 **/
public class ProblemNo144 {


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
     * 递归：万金油写法
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        // 新建节点值集合，若传入节点为null，则返回空集合
        List<Integer> nodeValList = new ArrayList<>();
        if (root == null) {
            return nodeValList;
        }
        // 二叉树前序遍历：先遍历当前节点，再遍历左子节点，最后遍历右子节点
        nodeValList.add(root.val);
        nodeValList.addAll(preorderTraversal1(root.left));
        nodeValList.addAll(preorderTraversal1(root.right));
        return nodeValList;
    }

    /**
     * 队列实现(貌似不行)，
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        // 新建节点值集合，若传入节点为null，则返回空集合
        List<Integer> nodeValList = new ArrayList<>();
        if (root == null) {
            return nodeValList;
        }
        // 节点按照前序遍历顺序放入队列
        Queue<TreeNode> queue = new LinkedList<>();
        // 先加入根节点
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 弹出队列头的元素，并加入节点值集合
            TreeNode currentNode = queue.poll();
            nodeValList.add(currentNode.val);
            // 若左右节点不为null，则加入队列
            while (currentNode.left != null) {
                queue.offer(currentNode.left);
                currentNode = currentNode.left;
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right);
            }
        }
        return nodeValList;
    }

    /**
     * 利用栈
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        // 新建节点值集合，若传入节点为null，则返回空集合
        List<Integer> nodeValList = new ArrayList<>();
        if (root == null) {
            return nodeValList;
        }
        // 声明栈，按弹出顺序为中序遍历入栈
        Deque<TreeNode> deque = new ArrayDeque<>();
        // 先对根节点进行压栈操作
        deque.push(root);
        while (!deque.isEmpty()) {
            // 弹出栈顶元素
            TreeNode topNode = deque.pop();
            // 将栈顶元素值加入集合
            nodeValList.add(topNode.val);
            // 先对右子节点压栈（因为栈先进后出）
            if (topNode.right != null) {
                deque.push(topNode.right);
            }
            // 再对左子节点压栈
            if (topNode.left != null) {
                deque.push(topNode.left);
            }
        }
        return nodeValList;
    }

}
