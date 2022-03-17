package com.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
  * @Description: leetcode120：三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
  * @Author: zhangjunqiang
  * @Date: 2021/11/6 15:55
  * @version v1.0
  */
public class ProblemNo120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int col = triangle.get(row - 1).size();
        int dp[][] = new int[row][col];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < triangle.size(); i++) {
            min = Integer.MAX_VALUE;
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (i == 0) {
                    dp[i][j] = triangle.get(i).get(j);
                } else if (j == triangle.get(i - 1).size()) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else if (j - 1 < 0) {
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1],dp[i - 1][j]) + triangle.get(i).get(j);
                }
                min = Math.min(dp[i][j],min);
            }
        }
        return min;
    }

    public static void main(String[] args) {
        ProblemNo120 obj = new ProblemNo120();
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        integers.add(-1);
        List<Integer> integers2 = new ArrayList<>();
        integers2.add(3);
        integers2.add(2);
        List<Integer> integers3 = new ArrayList<>();
        integers3.add(-3);
        integers3.add(1);
        integers3.add(-1);
        list.add(integers);
        list.add(integers2);
        list.add(integers3);
        int i = obj.minimumTotal(list);
        System.out.println(i);
    }

}
