package com.algorithm.dynamicprogramming;

/**
 * @Level：simple
 * @Description: leetcode121-买卖股票的最佳时期
 * 题目分析：求数组中最大的差值，且前面的数比后面的数小
 * @Author: zhangjunqiang
 * @Date: 2021/5/27
 */
public class ProblemNo121 {

    /**
     * 暴力求解，时间复杂度过高
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                if (prices[j] - prices[i] > result)
                    result = prices[j] - prices[i];
            }
        }
        return result;
    }

    /**
     * 每天考虑当前卖出利润是多少，通过当天前的最低位与当天价格计算当天最高利润，若当天比以前的都要低，则替换最低值
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int result = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                result = prices[i] - min > result ? prices[i] - min : result;
            }
        }
        return result;
    }
    
    /**
     * 动态规划思想，上面的方法为此方法的优化，因为其不需要维护dp数组
     * 
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int minPrice;
        // dp数组
        int[] dp = new int[prices.length];
        dp[0] = 0;
        minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 有最小值则更新
            minPrice = Math.min(minPrice,prices[i]);
            // 每天的卖出的最大价格为前一天卖出的最大价格和当前价格-最小值中取最大
            dp[i] = Math.max(dp[i - 1],prices[i] - minPrice);
        }
        return dp[prices.length - 1];
    }

}
