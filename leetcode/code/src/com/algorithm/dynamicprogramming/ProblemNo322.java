package com.algorithm.dynamicprogramming;

/**
 * @description: leetcode322:零钱兑换
 * @author: BadGuy
 * @date: 2021-12-05 11:19
 **/
public class ProblemNo322 {
    static class Node {
        int count;
        int amount;
        public Node(int count,int amount) {
            this.count = count;
            this.amount = amount;
        }
    }

    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        // 问题转换为完全背包问题，一件物品可以用多次，背包容量为amount
        int len = coins.length;
        // 声明dp数组记录总金额和硬币个数
        Node[][] dp = new Node[len + 1][amount + 1];
        // 初始化
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = new Node(0,0);
            }
        }
        int result = -1;
        // 遍历硬币
        for (int i = 1; i <= len; i ++) {
            // 遍历容量
            for (int j = 1; j <= amount; j++) {
                // 先默认不用当前这枚硬币
                dp[i][j] = new Node(dp[i - 1][j].count,dp[i - 1][j].amount);
                // 遍历使用次数,并记录
                int k;
                Node node = new Node(0,0);
                for (k = 1;;) {
                    // 如果当前硬币使用后容量不够，则跳过
                    if (j - k * coins[i - 1] < 0) {
                        break;
                    }
                    if (node.amount < dp[i - 1][j - k * coins[i - 1]].amount + k * coins[i - 1]){
                        node.amount = dp[i - 1][j - k * coins[i - 1]].amount + k * coins[i - 1];
                        node.count = dp[i - 1][j - k * coins[i - 1]].count + k;
                    } else if (node.amount == dp[i - 1][j - k * coins[i - 1]].amount + k * coins[i - 1]) {
                        node.count = Math.min(node.count,dp[i - 1][j - k * coins[i - 1]].count + k);
                    }
                    k++;
                }
                if (node.amount > dp[i][j].amount || (node.amount == dp[i][j].amount && node.count < dp[i][j].count)) {
                    dp[i][j].amount = node.amount;
                    dp[i][j].count = node.count;
                }
                if (dp[i][j].amount == amount) {
                    result = result == -1 ? dp[i][j].count : Math.min(result,dp[i][j].count);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println('a' - 'A');
    }
}
