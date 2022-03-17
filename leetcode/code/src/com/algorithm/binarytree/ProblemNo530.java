package com.algorithm.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
  * @Description: TODO(一句话描述该类的功能)
  * @Author: zhangjunqiang
  * @Date: 2021/11/23 22:23
  * @version v1.0
  */
public class ProblemNo530 {


   public static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }
   }


    static int max = Integer.MAX_VALUE;

    public static int getMinimumDifference(TreeNode root) {
        if (root.left != null) {
            max = Math.min(root.val - root.left.val,getMinimumDifference(root.left));
        }
        if (root.right != null) {
            max = Math.min(root.right.val - root.val,getMinimumDifference(root.right));
        }
        return max;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(543);
        TreeNode node2 = new TreeNode(384);
        TreeNode node3 = new TreeNode(652);
        TreeNode node4 = new TreeNode(445);
        TreeNode node5 = new TreeNode(699);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.right = node5;
        System.out.println(getMinimumDifference(node1));
        Map<Integer,Integer> map = new HashMap<>();
    }
}
