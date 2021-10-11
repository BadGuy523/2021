package com.algorithm.unionfindsets;

/**
 * @Description: leetcode-684：冗余连接
 * @Author: zhangjunqiang
 * @Date: 2021/9/16
 */
public class ProblemNo684 {

    public int[] findRedundantConnection(int[][] edges) {
        // 连通边的条数
        int nums = edges.length;
        // 记录各点的父级
        int[] pre = new int[nums + 1];
        // 初始化父级为本身
        for (int i = 1; i <= nums; i++) {
            pre[i] = i;
        }
        for (int i = 0; i < nums; i++) {
            int node0 = edges[i][0];
            int node1 = edges[i][1];
            int root0 = findRoot(node0,pre);
            int root1 = findRoot(node1,pre);
            if (root0 != root1) {
                union(root0,root1,pre);
            } else {
                return edges[i];
            }
        }
        return null;
    }

    private void union(int root0, int root1, int[] pre) {
        pre[root0] = root1;
    }

    private int findRoot(int node, int[] pre) {
        while (node != pre[node]) {
            node = pre[node];
        }
        return node;
    }

}
