package com.algorithm.dynamicprogramming;

/**
  * @Description: leetcode-122:买卖股票的最佳时机II
  * @Author: zhangjunqiang
  * @Date: 2021/10/20
  */
public class ProblemNo122 {

    // TODO:动态规划思想，按照股票买卖流程逻辑解题
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }


    /**
     * 双指针求各个上升子序列之和
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int left = 0;
        int right = 1;
        int result = 0;
        // 右指针小于数组长度继续循环
        while (right < prices.length) {
            // 右指针小于数组长度，并且当前右指针指向数字大于上一个右指针指向数据，递增子数组，右指针继续右移
            while (right < prices.length && prices[right - 1] < prices[right]) {
                right++;
            }
            // 若为递减序列，right-1的数和left相同，正好为0，若出现了一段递增，left到right-1就是递增序列的最大差值
            result += prices[right - 1] - prices[left];
            // 更新左右指针，继续寻找
            left = right;
            right++;
        }
        return result;
    }

    /**
     * 双指针求各个上升子序列之和（可简化为贪心算法）
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(0,prices[i] - prices[i - 1]);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.maxProfit(new int[]{7,1,5,3,6,4});
    }

}
