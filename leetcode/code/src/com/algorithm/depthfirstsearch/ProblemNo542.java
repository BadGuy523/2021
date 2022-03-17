package com.algorithm.depthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: leetcode-542:01矩阵
 * @author: BadGuy
 * @date: 2021-10-31 19:34
 **/
class ProblemNo542 {
        // 便于向四周广搜
    int[][] dict = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

    public int[][] updateMatrix(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        // 记录离最近的0距离
        int[][] dist = new int[row][col];
        // 记录是否访问过
        boolean[][] isVisted = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<>();
        // 先将0全部入队
        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i,j});
                    isVisted[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] head = queue.poll();
            for (int i = 0; i < dict.length; i++) {
                int x = head[0] + dict[i][0];
                int y = head[1] + dict[i][1];
                // 如果已访问过，或越界，则跳过
                if (x < 0 || x >= row || y < 0 ||y >= col || isVisted[x][y]) {
                    continue;
                }
                // 否则入队，标记访问，并且距离为当前出队点的距离+1
                queue.offer(new int[]{x,y});
                isVisted[x][y] = true;
                dist[x][y] = dist[head[0]][head[1]] + 1;
            }
        }
        return dist;
    }
}
