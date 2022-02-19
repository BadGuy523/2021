package com.algorithm.dynamicprogramming;

/**
 * @description: leetcode1049:最后一块石头的重量2
 * @author: BadGuy
 * @date: 2021-12-04 15:09
 **/
public class ProblemNo1049 {
    public static int lastStoneWeightII(int[] stones) {
        // 问题转换为求目标价值为sum/2的01背包问题
        int len = stones.length;
        int sum = 0;
        for (int i = 0; i < len; i ++) {
            sum += stones[i];
        }
        int bagSize = sum / 2;
        // 声明二维背包数组
        int[][] dp = new int[len + 1][bagSize + 1];
        // 遍历石头
        for (int i = 1; i <= len; i ++) {
            // 遍历背包容量
            for (int j = 1; j <= bagSize; j ++) {
                if (j < stones[i - 1]) {
                    continue;
                }
                // 状态转移方程
                dp[i][j] = Math.max(dp[i - 1][j],dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
            }
        }
        return sum - 2 * dp[len][bagSize];
    }

    public static void main(String[] args) {
        System.out.println(lastStoneWeightII(new int[]{1,2}));
    }
}
