package com.algorithm.depthfirstsearch;

/**
 * @description: leetcode-542:01矩阵
 * @author: BadGuy
 * @date: 2021-10-31 19:34
 **/
class ProblemNo542 {
    static int row;
    static int col;
    public static int[][] updateMatrix(int[][] mat) {
        // 矩阵长宽
        row = mat.length;
        col = mat.length;
        // 记录是否访问过
        boolean[][] isVisted = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 若访问过，或值为0，跳过
                if (isVisted[i][j] || mat[i][j] == 0) {
                    continue;
                }
                // dfs求距离
                mat[i][j] = dfs(isVisted,mat,i,j);
            }
        }
        return mat;
    }

    public static int dfs(boolean[][] isVisted,int[][] mat,int x,int y) {
        // 如果该点超出边界，返回-1
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return -1;
        }
        // 如果已访问过或者原本值为0，直接返回
        if (isVisted[x][y] || mat[x][y] == 0) {
            return mat[x][y];
        }
        isVisted[x][y] = true;
        // 记录距离
        int dis = 1;
        // dfs周边点
        int a = dfs(isVisted,mat,x - 1,y);
        dis = a == -1 ? dis : a;
        int b = dfs(isVisted,mat,x + 1,y);
        dis = b == -1 ? dis : Math.min(dis,b);
        int c = dfs(isVisted,mat,x,y - 1);
        dis = c == -1 ? dis : Math.min(dis,c);
        int d = dfs(isVisted,mat,x,y + 1);
        dis = d == -1 ? dis : Math.min(dis,d);
        return dis + 1;
    }

    public static void main(String[] args) {
        updateMatrix(new int[][]{{0,0,0},{0,1,0},{1,1,1}});
    }
}
