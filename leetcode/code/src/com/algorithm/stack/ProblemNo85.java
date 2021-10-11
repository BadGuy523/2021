package com.algorithm.stack;



import java.util.Deque;
import java.util.LinkedList;

/**
 * @Description: leetcode-85：最大矩形
 * @Author: zhangjunqiang zwx925931
 * @Date: 2021/9/9
 */
public class ProblemNo85 {

    ///**
    // * 枚举矩形左上角与右下角，暴力求解
    // *
    // * @param matrix
    // * @return
    // */
    //public static int maximalRectangle(char[][] matrix) {
    //    int result = 0;
    //    int row = matrix.length;
    //    if (row == 0) {
    //        return result;
    //    }
    //    int col = matrix[0].length;
    //    // 外层左上角坐标
    //    for (int i = 0; i < row; i++) {
    //        for (int j = 0; j < col; j++) {
    //            if (matrix[i][j] == '0') {
    //                continue;
    //            }
    //            // 内层右下角坐标
    //            for (int k = i; k < row; k++) {
    //                for (int l = j; l < col; l++) {
    //                    // 判断是否满足要求
    //                    boolean flag = true;
    //                    for (int m = i; m <= k ; m++) {
    //                        for (int n = j; n <= l; n++) {
    //                            if (matrix[m][n] == '0') {
    //                                flag = false;
    //                            }
    //                        }
    //                    }
    //                    if (flag) {
    //                        result = Math.max(result,(k - i + 1) * (l - j + 1));
    //                    }
    //                }
    //            }
    //        }
    //    }
    //    return result;
    //}

    ///**
    // * 枚举每个矩形右下角，先求出每个右下角的最大宽度，也就是每个矩形前有多少个连续的1
    // *
    // * @param matrix
    // * @return
    // */
    //public static int maximalRectangle(char[][] matrix) {
    //    int result = 0;
    //    int row = matrix.length;
    //    if (row == 0) {
    //        return result;
    //    }
    //    int col = matrix[0].length;
    //    int[][] left = new int[row][col];
    //    // 求每个右下角左边有多少个连续1，最大宽度
    //    for (int i = 0; i < row; i++) {
    //        for (int j = 0; j < col; j++) {
    //            if (matrix[i][j] == '0') {
    //                continue;
    //            }
    //            left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
    //        }
    //    }
    //    // 枚举每个矩形右下角
    //    for (int i = 0; i < row; i++) {
    //        for (int j = 0; j < col; j++) {
    //            if (matrix[i][j] == '0') {
    //                continue;
    //            }
    //            int width = left[i][j];
    //            int area = width;
    //            // 从下往上中，遇到为0的就终止
    //            for (int k = i - 1; k >= 0; k--) {
    //                if (left[k][j] == 0) {
    //                    break;
    //                }
    //                width = Math.min(left[k][j],width);
    //                area = Math.max(area,width * (i - k + 1));
    //            }
    //            result = Math.max(area,result);
    //        }
    //    }
    //    return result;
    //}

    public static int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];

        // 求每个右下角左边有多少个连续1，最大宽度
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int ret = 0;
        for (int j = 0; j < n; j++) { // 对于每一列，使用基于柱状图的方法
            int[] up = new int[m];  // 向上比自己宽度小的的第一个数索引
            int[] down = new int[m];  // 向下比自己宽度小的第一个数索引

            Deque<Integer> stack = new LinkedList<Integer>();
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = m - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                down[i] = stack.isEmpty() ? m : stack.peek();
                stack.push(i);
            }

            for (int i = 0; i < m; i++) {
                // 最大宽度*最大高度
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

    //public static void main(String[] args) {
    //    char[][] a = new char[][]{{'1','0','1','0'},{'1','0','1','1'},{'0','1','1','1'},{'1','0','1','0'}};
    //    maximalRectangle(a);
    //}

}

