package com.algorithm.unionfindsets;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: leetcode924-尽量减少恶意软件的传播
 * @Author: zhangjunqiang
 * @Date: 2021/9/22
 */
public class ProblemNo924 {

    public int minMalwareSpread(int[][] graph, int[] initial) {
        // 节点网络数组长宽
        int graphRow = graph.length;
        // 记录每个节点的联通分量,初始化为-1
        int[] unionType = new int[graphRow];
        for (int i = 0; i < graphRow; i++) {
            unionType[i] = -1;
        }
        // 深度搜索，确定每个节点的联通分量
        int type = 0;
        for (int i = 0; i < graphRow; i++) {
            if (unionType[i] == -1) {
                dfs(unionType,graph,graphRow,i,type++);
            }
        }
        // 统计联通分量的数量
        int[] typeNums = new int[type];
        for (int i = 0; i < graphRow; i++) {
            typeNums[unionType[i]]++;
        }
        // 3. Find unique colors.
        int[] colorCount = new int[type];
        for (int node: initial)
            colorCount[unionType[node]]++;

        // 4. Answer
        int ans = Integer.MAX_VALUE;
        for (int node: initial) {
            int c = unionType[node];
            if (colorCount[c] == 1) {
                if (ans == Integer.MAX_VALUE)
                    ans = node;
                else if (typeNums[c] > typeNums[unionType[ans]])
                    ans = node;
                else if (typeNums[c] == typeNums[unionType[ans]] && node < ans)
                    ans = node;
            }
        }

        if (ans == Integer.MAX_VALUE)
            for (int node: initial)
                ans = Math.min(ans, node);

        return ans;
    }

    public void dfs(int[] unionType, int[][] graph, int graphRow,int node,int type) {
        unionType[node] = type;
        for (int i = 0; i < graphRow; i++) {
            if (graph[node][i] == 1 && unionType[i] == -1) {
                dfs(unionType,graph,graphRow,i,type);
            }
        }
    }
//
//     public static void main(String[] args) {
//         ProblemNo924 solution = new ProblemNo924();
//         solution.minMalwareSpread(new int[][]{{1,1,0},{1,1,0},{0,0,1}},new int[]{0,1});
//     }

}
