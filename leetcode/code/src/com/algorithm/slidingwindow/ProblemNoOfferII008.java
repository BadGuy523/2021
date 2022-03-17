package com.algorithm.slidingwindow;

import java.util.Arrays;

/**
  * @Description: 剑指 Offer II 008. 和大于等于 target 的最短子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
  * @Author: zhangjunqiang
  * @Date: 2021/11/14 18:40
  * @version v1.0
  */
public class ProblemNoOfferII008 {
    public int minSubArrayLen(int target, int[] nums) {
        // 声明左右指针，数组长度，初始化长度最小的连续子数组长度为数组长度，初始化子数组长度为0
        int left = 0;
        int right = 0;
        int len = nums.length;
        int result = Integer.MAX_VALUE;
        int sum = 0;
        // 当右指针小于数组长度
        while (right < len) {
            sum += nums[right];
            while (sum >= target) {
                result = Math.min(result,right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
