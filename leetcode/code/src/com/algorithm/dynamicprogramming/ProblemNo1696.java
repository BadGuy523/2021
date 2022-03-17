package com.algorithm.dynamicprogramming;

import java.util.ArrayDeque;
import java.util.Deque;

/**
  * @Description: 1696. 跳跃游戏 VI
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 *
 * 一开始你在下标 0 处。每一步，你最多可以往前跳 k 步，但你不能跳出数组的边界。也就是说，你可以从下标 i 跳到 [i + 1， min(n - 1, i + k)] 包含 两个端点的任意位置。
 *
 * 你的目标是到达数组最后一个位置（下标为 n - 1 ），你的 得分 为经过的所有数字之和。
 *
 * 请你返回你能得到的 最大得分 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,-1,-2,4,-7,3], k = 2
 * 输出：7
 * 解释：你可以选择子序列 [1,-1,4,3] （上面加粗的数字），和为 7 。
 * 示例 2：
 *
 * 输入：nums = [10,-5,-2,4,0,3], k = 3
 * 输出：17
 * 解释：你可以选择子序列 [10,4,3] （上面加粗数字），和为 17 。
 * 示例 3：
 *
 * 输入：nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
 * 输出：0
 *
 *
 * 提示：
 *
 *  1 <= nums.length, k <= 105
 * -104 <= nums[i] <= 104
  * @Author: zhangjunqiang
  * @Date: 2021/11/13 19:09
  * @version v1.0
  */
public class ProblemNo1696 {

    /**
     * k值过大，双层for循环超时，需要优化求[i - k,i - 1]滑动区间的最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxResult2(int[] nums,int k) {
        int len = nums.length;
        // dp数组存储跳到某个索引的最大得分
        int[] dp = new int[len];
        dp[0] = nums[0];
        // 循环遍历，状态转移方程，为当前索引得分与往前推k个索引相加的最大值
        for (int i = 1; i < nums.length; i++) {
            int count = 1;
            int max = Integer.MIN_VALUE;
            while (count <= k && i - count >= 0) {
                max = Math.max(dp[i - count++] + nums[i],max);
            }
            dp[i] = max;
        }
        return dp[len - 1];
    }

    /**
     * 利用双端队列记录[i - k,i - 1]滑动区间的最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxResult(int[] nums,int k) {
        // 双端队列
        Deque<Integer> deque = new ArrayDeque<>();
        int len = nums.length;
        // dp数组存储跳到某个索引的最大得分
        int[] dp = new int[len];
        dp[0] = nums[0];
        deque.addFirst(0);
        // 循环遍历，状态转移方程，为当前索引得分与往前推k个索引相加的最大值
        for (int i = 1; i < nums.length; i++) {
            // 如果队首和队尾索引差值大于K，则移除队首元素
            if (deque.getLast() - deque.getFirst() >= k) {
                deque.removeFirst();
            }
            // 当前索引最大得分为，当前点得分+队首得分
            dp[i] = nums[i] + dp[deque.getFirst()];
            while (!deque.isEmpty() && dp[i] > dp[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        return dp[len - 1];
    }

}
