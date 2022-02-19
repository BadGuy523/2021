package com.algorithm.dynamicprogramming;

/**
 * @description: leetcode416:分割等和子集
 * @author: BadGuy
 * @date: 2021-12-05 10:25
 **/
public class ProblemNo416 {

    /**
     * 二维dp数组
     *
     * @param nums
     * @return
     */
    public static boolean canPartition1(int[] nums) {
        // 问题转换为求背包容量为sum/2的01背包问题
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if ((sum & 1) == 1) {
            return false;
        }
        boolean isOk = false;
        // 定义背包容量
        int size = sum / 2;
        // 定义dp数组
        int[][] dp = new int[len + 1][size + 1];
        // 遍历每个数
        a: for (int i = 1; i <= len; i++) {
            // 遍历每个容量
            for (int j = 1; j <= size; j++) {
                // 先默认等于放上一个物品时的价值，关键，忘记很多遍了
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] < 0) {
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j],dp[i - 1][j - nums[i - 1]] + nums[i - 1]);
                if (dp[i][j] == size) {
                    isOk = true;
                    break a;
                }
            }
        }
        return isOk;
    }

    /**
     * 优化为一维dp数组
     *
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        // 问题转换为求背包容量为sum/2的01背包问题
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if ((sum & 1) == 1) {
            return false;
        }
        boolean isOk = false;
        // 定义背包容量
        int size = sum / 2;
        // 定义一维dp数组
        int[] dp = new int[size];
        // 遍历数字
        a: for (int i = 1; i <= len; i++) {
            // 遍历容量
            for (int j = size; j >= 1 && j >= nums[i - 1]; j--) {
                dp[j] = Math.max(dp[j],dp[j - nums[i - 1]]);
                if (dp[j] == size) {
                    isOk = true;
                    break a;
                }
            }
        }
        return isOk;
    }

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1,5,10,6}));
    }

}
