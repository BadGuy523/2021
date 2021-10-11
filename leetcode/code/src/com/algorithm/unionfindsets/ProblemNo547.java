package com.algorithm.unionfindsets;



import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: leetcode-547：省份数量，BFS,DFS,并查集
 * @Author: zhangjunqiang
 * @Date: 2021/9/16
 */
public class ProblemNo547 {

    ///**
    // * 深度优先搜索
    // *
    // * @param isConnected
    // * @return
    // */
    //public int findCircleNum(int[][] isConnected) {
    //    // 城市数量
    //    int num = isConnected.length;
    //    // 用于记录该城市是否访问过
    //    boolean[] isVisted = new boolean[num];
    //    // 记录省份数量
    //    int result = 0;
    //    // 遍历每个城市
    //    for (int i = 0; i < num; i++) {
    //        if (!isVisted[i]) {
    //            dfs(isConnected,i,isVisted,num);
    //            result++;
    //        }
    //    }
    //    return result;
    //}
    //
    //private void dfs(int[][] isConnected, int i, boolean[] isVisted, int num) {
    //    // i城市被访问
    //    isVisted[i] = true;
    //    for (int j = 0; j < num; j++) {
    //        if (isConnected[i][j] == 1 && !isVisted[j]) {
    //            // 若是连接的且没有访问过，递归进行深度搜索
    //            dfs(isConnected,j,isVisted,num);
    //        }
    //    }
    //}

    ///**
    // * 广度优先搜索
    // *
    // * @param isConnected
    // * @return
    // */
    //public int findCircleNum(int[][] isConnected) {
    //    // 城市数量
    //    int num = isConnected.length;
    //    // 用于记录该城市是否被访问过
    //    boolean[] isVisted = new boolean[num];
    //    // 记录省份数量
    //    int result = 0;
    //    // 队列，用于广度优先搜索
    //    Queue<Integer> queue = new LinkedList<>();
    //    for (int i = 0; i < num; i++) {
    //        if (!isVisted[i]) {
    //            // 若没有访问过，则加入队列，结果+1
    //            queue.offer(i);
    //            result++;
    //            // 队列非空则继续搜索
    //            while (!queue.isEmpty()) {
    //                // 将队首元素设置为访问过
    //                Integer poll = queue.poll();
    //                isVisted[poll] = true;
    //                // 遍历是否连接数组，将连接的元素，且没有访问过的加入队列
    //                for (int j = 0; j < num; j++) {
    //                    if (isConnected[poll][j] == 1 && !isVisted[j]) {
    //                        queue.offer(j);
    //                    }
    //                }
    //            }
    //        }
    //    }
    //    return result;
    //}

    /**
     * 并查集
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        // 城市数量
        int num = isConnected.length;
        // 记录省份数量
        int result = 0;
        // 记录每个点的所属上级节点
        int[] pre = new int[num];
        // 初始化每个点的上级点为自己
        for (int i = 0; i < num; i++) {
            pre[i] = i;
        }
        // 遍历连通数组
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (isConnected[i][j] == 1) {
                    union(pre,i,j);
                }
            }
        }
        // 遍历每个点，若自己为根节点则记录+1
        for (int i = 0; i < num; i++) {
            if (pre[i] == i) {
                result++;
            }
        }
        return result;
    }

    /**
     * 将两个联通的点的根节点设置为相同的节点
     *
     * @param pre
     * @param i
     * @param j
     */
    private void union(int[] pre, int i, int j) {
        // 找到两个节点的根节点，进行比较，不同则统一成相同点
        int iRoot = findRootWithPathCompressionRecursion(pre,i);
        int jRoot = findRootWithPathCompressionRecursion(pre,j);
        if (iRoot != jRoot) {
            pre[iRoot] = jRoot;
        }
    }

    /**
     * 查找某节点的根节点
     *
     * @param pre
     * @param i
     * @return
     */
    private int findRoot(int[] pre, int i) {
        while (pre[i] != i) {
            i = pre[i];
        }
        // 路径压缩
        return i;
    }

    /**
     * 查找某节点的根节点(路径压缩)
     *
     * @param pre
     * @param i
     * @return
     */
    private int findRootWithPathCompression(int[] pre, int i) {
        int current = i;
        int tmp;
        while (pre[i] != i) {
            i = pre[i];
        }
        // 路径压缩,将属于某根节点的节点上级都修改为根节点，减少查询链路
        while (current != i) {
            tmp = pre[current];
            pre[current] = i;
            current = tmp;
        }
        return i;
    }

    /**
     * 查找某节点的根节点(路径压缩-递归)
     *
     * @param pre
     * @param i
     * @return
     */
    private int findRootWithPathCompressionRecursion(int[] pre, int i) {
        int current = i;
        while (pre[i] != i) {
            i = pre[i];
        }
        compressionRecursion(current,pre,i);
        return i;
    }

    private void compressionRecursion(int current, int[] pre, int root) {
        if (root != pre[current]) {
            compressionRecursion(pre[current],pre,root);
            pre[current] = root;
        }
    }

}

