package com.algorithm.unionfindsets;



/**
 * @Description: leetcode-200：岛屿数量
 * @Author: zhangjunqiang
 * @Date: 2021/9/17
 */
public class ProblemNo200 {

    //class Coordinate {
    //    int x;
    //    int y;
    //
    //    public Coordinate(int x, int y) {
    //        this.x = x;
    //        this.y = y;
    //    }
    //}
    //
    ///**
    // * 广度优先搜索
    // *
    // * @param grid
    // * @return
    // */
    //public int numIslands(char[][] grid) {
    //    int row = grid.length;
    //    int col = grid[0].length;
    //    // 队列，用于广搜
    //    Queue<Coordinate> queue = new LinkedList<>();
    //    // 二维数组，用于记录该坐标是否被搜索过
    //    boolean[][] isSearch = new boolean[row][col];
    //    // 记录岛屿数量
    //    int result = 0;
    //    for (int i = 0; i < row; i++) {
    //        for (int j = 0; j < col; j++) {
    //            if (!ifCanThenSearched(queue,isSearch,i,j,row,col,grid)) {
    //                continue;
    //            }
    //            while (!queue.isEmpty()) {
    //                // 取队首节点，将其周围可搜索的节点加入队列
    //                Coordinate coordinate = queue.poll();
    //                ifCanThenSearched(queue,isSearch,coordinate.x - 1,coordinate.y,row,col,grid);
    //                ifCanThenSearched(queue,isSearch,coordinate.x + 1,coordinate.y,row,col,grid);
    //                ifCanThenSearched(queue,isSearch,coordinate.x,coordinate.y - 1,row,col,grid);
    //                ifCanThenSearched(queue,isSearch,coordinate.x,coordinate.y + 1,row,col,grid);
    //            }
    //            result++;
    //        }
    //    }
    //    return result;
    //}
    //
    //private boolean ifCanThenSearched(Queue<Coordinate> queue, boolean[][] isSearch, int i, int j,int row,int col,char[][] grid) {
    //    // 边界外跳过
    //    if (i < 0 || i >= row) {
    //        return false;
    //    }
    //    if (j < 0 || j >= col) {
    //        return false;
    //    }
    //    // 被搜索过或者是水，跳过
    //    if (isSearch[i][j] || grid[i][j] == '0') {
    //        return false;
    //    }
    //    // 将坐标加入队列，并且标记已被搜索过
    //    queue.offer(new Coordinate(i, j));
    //    isSearch[i][j] = true;
    //    return true;
    //}

    ///**
    // * 并查集处理类，将二维数组转化为一维数组进行处理
    // */
    //class UnionFind {
    //    // 记录岛屿数量
    //    int count;
    //    // 记录父级节点
    //    int[] parent;
    //    // 按秩合并所需数组
    //    int[] rank;
    //
    //    /**
    //     * 构造函数，初始化parent，rank，count
    //     * @param grid
    //     */
    //    public UnionFind(char[][] grid) {
    //        count = 0;
    //        int m = grid.length;
    //        int n = grid[0].length;
    //        parent = new int[m * n];
    //        rank = new int[m * n];
    //        for (int i = 0; i < m; ++i) {
    //            for (int j = 0; j < n; ++j) {
    //                // 非水的点才进行parent初始化和count计数
    //                if (grid[i][j] == '1') {
    //                    parent[i * n + j] = i * n + j;
    //                    ++count;
    //                }
    //                rank[i * n + j] = 0;
    //            }
    //        }
    //    }
    //
    //    public int find(int i) {
    //        // 路径压缩，若上级不是根节点，改为根节点
    //        if (parent[i] != i) parent[i] = find(parent[i]);
    //        return parent[i];
    //    }
    //
    //    /**
    //     * 合并两个节点的父节点
    //     * @param x
    //     * @param y
    //     */
    //    public void union(int x, int y) {
    //        int rootx = find(x);
    //        int rooty = find(y);
    //        if (rootx != rooty) {
    //            // 将矮的树合并到高的树中
    //            if (rank[rootx] > rank[rooty]) {
    //                parent[rooty] = rootx;
    //            } else if (rank[rootx] < rank[rooty]) {
    //                parent[rootx] = rooty;
    //            } else {
    //                parent[rooty] = rootx;
    //                rank[rootx] += 1;
    //            }
    //            // 合并后岛数量-1
    //            --count;
    //        }
    //    }
    //
    //    public int getCount() {
    //        return count;
    //    }
    //}
    //
    //public int numIslands(char[][] grid) {
    //    if (grid == null || grid.length == 0) {
    //        return 0;
    //    }
    //    int nr = grid.length;
    //    int nc = grid[0].length;
    //    // 初始化
    //    UnionFind uf = new UnionFind(grid);
    //    for (int r = 0; r < nr; ++r) {
    //        for (int c = 0; c < nc; ++c) {
    //            if (grid[r][c] == '1') {
    //                // 遍历后将其变为水，不进行后面的遍历
    //                grid[r][c] = '0';
    //                if (r - 1 >= 0 && grid[r-1][c] == '1') {
    //                    uf.union(r * nc + c, (r-1) * nc + c);
    //                }
    //                if (r + 1 < nr && grid[r+1][c] == '1') {
    //                    uf.union(r * nc + c, (r+1) * nc + c);
    //                }
    //                if (c - 1 >= 0 && grid[r][c-1] == '1') {
    //                    uf.union(r * nc + c, r * nc + c - 1);
    //                }
    //                if (c + 1 < nc && grid[r][c+1] == '1') {
    //                    uf.union(r * nc + c, r * nc + c + 1);
    //                }
    //            }
    //        }
    //    }
    //
    //    return uf.getCount();
    //}

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    result++;
                    convertSelfAndAroundOneToZero(i,j,grid,row,col);
                }
            }
        }
        return result;
    }

    private void convertSelfAndAroundOneToZero(int i, int j, char[][] grid, int row, int col) {
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            convertSelfAndAroundOneToZero(i - 1, j, grid, row, col);
        }
        if (i + 1 < row && grid[i + 1][j] == '1') {
            convertSelfAndAroundOneToZero(i + 1, j, grid, row, col);
        }
        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            convertSelfAndAroundOneToZero(i, j - 1, grid, row, col);
        }
        if (j + 1 < col && grid[i][j + 1] == '1') {
            convertSelfAndAroundOneToZero(i, j + 1, grid, row, col);
        }
    }

}

