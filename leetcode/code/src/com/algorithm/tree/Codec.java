package com.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description: leetcode-297:二叉树的序列化与反序列化
 * @author: BadGuy
 * @date: 2021-10-17 15:00
 **/
public class Codec {

    /**
     * 树节点模型
     */
    static class TreeNode {
        // 节点值
        int val;
        // 左节点
        TreeNode left;
        // 右节点
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    public String serialize2(TreeNode root) {
        // 用于存储节点数值的字符串
        StringBuilder builder = new StringBuilder();
        // 队列，用于二叉树的广搜
        Queue<TreeNode> nodeList = new LinkedList<>();
        builder.append(root.val);
        nodeList.add(root.left);
        nodeList.add(root.right);
        while (!nodeList.isEmpty()) {
            TreeNode left = nodeList.poll();
            TreeNode right = nodeList.poll();
            if (left == null) {
                builder.append(",").append("n");
                // 左子树为空，但右子树不为空时，需补齐左子树占得数组位置
                if (!nodeList.isEmpty()) {
                    nodeList.add(null);
                    nodeList.add(null);
                }
            } else {
                builder.append(",").append(left.val);
                nodeList.add(left.left);
                nodeList.add(left.right);
            }
            if (right == null) {
                builder.append(",").append("n");
            } else {
                builder.append(",").append(right.val);
                nodeList.add(right.left);
                nodeList.add(right.right);
            }
        }
        return builder.toString();
    }

    public String serialize(TreeNode root) {
        // 用于存储数组形式的二叉树
        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root);
        for (int i = 0; i < nodeList.size(); i++) {
            TreeNode treeNode = nodeList.get(i);
            if (treeNode == null) {
                continue;
            }
            nodeList.add(treeNode.left);
            nodeList.add(treeNode.right);
        }
        return null;
    }

    public TreeNode deserialize(String data) {
        TreeNode result = new TreeNode(0);
        return result;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        node4.left = node6;
        Codec codec = new Codec();
        String serialize = codec.serialize(node1);
        System.out.println(serialize);
    }

}
