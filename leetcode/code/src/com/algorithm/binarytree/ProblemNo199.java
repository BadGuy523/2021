package com.algorithm.binarytree;

import java.util.*;

/**
 * @description: leetcode-199:二叉树的右视图
 * @author: BadGuy
 * @date: 2021-10-23 15:29
 **/
public class ProblemNo199 {

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

    /**
     * 大致思路，先遍历右子树，并记录最右边已有可看到的节点层数
     * @param root
     * @return
     */
    public List<Integer> rightSideView1(TreeNode root) {
        // 声明结果集合
        List<Integer> result = new ArrayList<>();
        // 判空
        if (root == null) {
            return result;
        }

        // 广度搜索记录每个节点对应的层数
        Map<TreeNode,Integer> levelMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Integer level = 1;
        while (!queue.isEmpty()) {
            // 把属于同一层的节点一起入队
            List<TreeNode> levelNodeList = new ArrayList<>();
            while(!queue.isEmpty()) {
                TreeNode topNode = queue.poll();
                if (topNode == null) {
                    continue;
                }
                levelMap.put(topNode,level);
                levelNodeList.add(topNode.left);
                levelNodeList.add(topNode.right);
            }
            level++;
            for (TreeNode treeNode : levelNodeList) {
                queue.add(treeNode);
            }
        }

        // 声明栈：用来存储节点，用于遍历
        Deque<TreeNode> nodeDeque = new ArrayDeque<>();
        // 当前节点
        TreeNode currentNode = root;
        while (currentNode != null || !nodeDeque.isEmpty()) {
            // 先遍历右节点
            while (currentNode != null) {
                nodeDeque.push(currentNode);
                if (levelMap.get(currentNode) > result.size()) {
                    result.add(currentNode.val);
                }
                currentNode = currentNode.right;
            }
            TreeNode topNode = nodeDeque.pop();
            currentNode = topNode.left;
        }
        return result;
    }

    /**
     * 大致思路，先遍历右子树，并记录最右边已有可看到的节点层数（优化计算层数）
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

        // 声明栈：用来存储节点层数，用于遍历
        Deque<Integer> levelDeque = new ArrayDeque<>();
        // 声明栈：用来存储节点，用于遍历
        Deque<TreeNode> nodeDeque = new ArrayDeque<>();
        // 当前节点
        TreeNode currentNode = root;
        // 当前层数
        Integer level = 1;
        while (currentNode != null || !nodeDeque.isEmpty()) {
            // 先遍历右节点
            while (currentNode != null) {
                nodeDeque.push(currentNode);
                levelDeque.push(level);
                // 层级大于当前集合容量才加入集合，否则该层已有最右的节点
                if (level > result.size()) {
                    result.add(currentNode.val);
                }
                currentNode = currentNode.right;
                level++;
            }
            TreeNode topNode = nodeDeque.pop();
            Integer topIndex = levelDeque.pop();
            level = topIndex + 1;
            currentNode = topNode.left;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node1.left = node2;
        ProblemNo199 obj = new ProblemNo199();
        List<Integer> list = obj.rightSideView(node1);
        System.out.println();
    }

}
