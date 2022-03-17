package com.algorithm.dynamicprogramming;

/**
  * @Description: 877. 石子游戏
 * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 *
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 *
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 *
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
 *
 *
 *
 * 示例：
 *
 * 输入：[5,3,4,5]
 * 输出：true
 * 解释：
 * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
 * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
 * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
 * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
 * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
 *
 *
 * 提示：
 *
 * 2 <= piles.length <= 500
 * piles.length 是偶数。
 * 1 <= piles[i] <= 500
 * sum(piles) 是奇数。
  * @Author: zhangjunqiang
  * @Date: 2021/11/13 9:43
  * @version v1.0
  */
public class ProblemNo877 {

    public boolean stoneGame(int[] piles) {
        int len = piles.length;
        // dp数组，记录从索引i到j之间取石头，二人相差石头的数量
        int[][] dp = new int[len][len];
        // 初始化i和j相等的情况
        for (int i = 0; i < len; i++) {
            dp[i][i] = piles[i];
        }
        // 从内而外进行遍历
        int left = len / 2 - 1;
        int right = left + 1;
        dfs(piles,dp,left,right,len);
        return dp[0][len - 1] > 0;
    }

    private void dfs(int[] piles, int[][] dp, int left, int right, int len) {
        // 超出边界，或者已经处理过的区间，跳过
        if (left < 0 || right >= len || dp[left][right] != 0) {
            return;
        }
        // 状态转移方程
        dp[left][right] = Math.max(piles[left] - dp[left + 1][right],piles[right] - dp[left][right - 1]);
        dfs(piles,dp,left - 1,right, len);
        dfs(piles,dp,left,right + 1, len);
    }

}
