package com.algorithm.depthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Level：simple
 * @Description: leetcode104-二叉树的最大深度
 * @Author: zhangjunqiang
 * @Date: 2021/6/17
 */
public class ProblemNo104 {

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int maxLeft = maxDepth(root.left);
        int maxRight = maxDepth(root.right);
        return Math.max(maxLeft,maxRight) + 1;
    }

    /**
     * 广搜
     *
     * @param root
     * @return
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                // 取当前层节点出队，并将当前层所有子节点进行入队进行拓展，ans记录拓展次数，即为最大深度
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

    static class TreeNode {
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

}
