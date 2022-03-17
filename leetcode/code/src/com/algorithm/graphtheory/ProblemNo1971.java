package com.algorithm.graphtheory;

/**
  * @Description: 1971. Find if Path Exists in Graph
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 *
 * You want to determine if there is a valid path that exists from vertex start to vertex end.
 *
 * Given edges and the integers n, start, and end, return true if there is a valid path from start to end, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], start = 0, end = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
  * @Author: zhangjunqiang
  * @Date: 2021/11/7 20:11
  * @version v1.0
  */
public class ProblemNo1971 {

    public boolean validPath(int n, int[][] edges, int start, int end) {
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            union(parents,edges[i][0],edges[i][1]);
        }
        return findParent(parents,start) == findParent(parents,end);
    }

    public int findParent(int[] parents,int cur) {
        if (parents[cur] != cur) {
            return findParent(parents,parents[cur]);
        } else {
            return cur;
        }
    }

    public void union(int[] parents,int one,int two) {
        int oneP = findParent(parents, one);
        int twoP = findParent(parents, two);
        if (oneP == twoP) {
            return;
        }
        parents[oneP] = twoP;
    }

    public static void main(String[] args) {
        ProblemNo1971 obj = new ProblemNo1971();
        obj.validPath(3,new int[][]{{0,1},{1,2},{2,0}},0,2);
    }

}
