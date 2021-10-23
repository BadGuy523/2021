package com.algorithm.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * 序列化二叉树 # 表示空节点
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        // 用于存储数组形式的二叉树
        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root);
        // 一边遍历一边向数组中插入节点
        for (int i = 0; i < nodeList.size(); i++) {
            TreeNode treeNode = nodeList.get(i);
            if (treeNode == null) {
                continue;
            }
            nodeList.add(treeNode.left);
            nodeList.add(treeNode.right);
        }
        // 去除数组中末尾为空的节点
        for (int i = nodeList.size() - 1; i >= 0 ; i--) {
            if (nodeList.get(i) != null) {
                break;
            } else {
                nodeList.remove(i);
            }
        }
        // 转换为字符串
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nodeList.size(); i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(nodeList.get(i) == null ? "#" : nodeList.get(i).val);
        }
        return builder.toString();
    }

    public TreeNode deserialize(String data) {
        // 判空
        if (data == null || "".equals(data)) {
            return null;
        }
        // 字符串转集合
        List<String> nodeVals = Arrays.asList(data.split(","));
        // 记录节点的集合
        List<TreeNode> treeNodes = new ArrayList<>();
        // 设置根节点
        TreeNode result = new TreeNode(Integer.parseInt(nodeVals.get(0)));
        treeNodes.add(result);
        // 从集合第二位开始遍历,isLeft表示当前遍历的节点为左节点
        boolean isLeft = true;
        // 记录当前放置节点的父节点索引
        int index = 0;
        for (int i = 1; i < nodeVals.size(); i++) {
            // 当前节点值不为空，则赋值左节点或右节点
            if (!"#".equals(nodeVals.get(i))) {
                TreeNode currentNode = new TreeNode(Integer.parseInt(nodeVals.get(i)));
                treeNodes.add(currentNode);
                if (isLeft) {
                    // 获取当前放置节点的父节点
                    treeNodes.get(index).left = currentNode;
                } else {
                    treeNodes.get(index).right = currentNode;
                }
            }
            // 若果当前放置的不是左节点，那么当前放置节点的父节点索引+1
            if (!isLeft) {
                index++;
            }
            // 循环依次则切换一次左右节点标志
            isLeft = !isLeft;
        }
        return result;
    }

//    public static void main(String[] args) {
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node4 = new TreeNode(4);
//        TreeNode node5 = new TreeNode(5);
//        TreeNode node6 = new TreeNode(6);
//        node1.left = node2;
//        node1.right = node3;
//        node3.left = node4;
//        node3.right = node5;
//        node4.left = node6;
//        Codec codec = new Codec();
//        String serialize = codec.serialize(node1);
//        System.out.println(serialize);
//        TreeNode deserialize = codec.deserialize(serialize);
//        System.out.println();
//    }

}
