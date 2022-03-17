package com.algorithm.dynamicprogramming;

/**
  * @Description: 1749. 任意子数组和的绝对值的最大值
 * 给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
 *
 * 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
 *
 * abs(x) 定义如下：
 *
 * 如果 x 是负整数，那么 abs(x) = -x 。
 * 如果 x 是非负整数，那么 abs(x) = x 。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,-3,2,3,-4]
 * 输出：5
 * 解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
 * 示例 2：
 *
 * 输入：nums = [2,-5,1,-4,3,-2]
 * 输出：8
 * 解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
  * @Author: zhangjunqiang
  * @Date: 2021/11/13 10:35
  * @version v1.0
  */
public class ProblemNo1749 {

    /**
     * 遍历子数组长度，略微暴力
     *
     * @param nums
     * @return
     */
    public int maxAbsoluteSum2(int[] nums) {
        int len = nums.length;
        int max = 0;
        int tmp = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i; j++) {
                tmp = 0;
                for (int k = j; k <= j + i; k++) {
                    tmp += nums[k];
                }
                max = Math.max(max,Math.abs(tmp));
            }
        }
        return max;
    }

    /**
     * 尝试利用前缀和进行优化
     *
     * @param nums
     * @return
     */
    public int maxAbsoluteSum3(int[] nums) {
        int len = nums.length;
        int max = 0;
        // 初始化前缀和
        int[] prefix = new int[len + 1];
        prefix[0] = 0;
        for (int i = 1; i <= len; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < len; i++) {
            for (int j = 1; j <= len - i; j++) {
                max = Math.max(max,Math.abs(prefix[j + i] - prefix[j - 1]));
            }
        }
        return max;
    }

    /**
     * 动态规划：一次求最小子序列和，一次求最大子序列和
     *
     * @param nums
     * @return
     */
    public int maxAbsoluteSum4(int[] nums) {
        int len = nums.length;
        int max = 0;
        // 动态规划求最大子序列，由于是求绝对值最大子序列，则求一次最大值，求一次最小值，取最大
        return Math.max(Math.abs(calculateSubSequence(nums,0)),Math.abs(calculateSubSequence(nums,1)));
    }

    /**
     * 求子序列和
     *
     * @param nums
     * @param flag 0表示求最小子序列和，1表示求最大子序列和
     */
    private int calculateSubSequence(int[] nums, int flag) {
        int length = nums.length;
        int preSum = nums[0];
        int maxMin = preSum;
        for (int i = 1; i < length; i++) {
            if (flag == 0) {
                preSum = Math.min(preSum + nums[i],nums[i]);
                maxMin = Math.min(maxMin,preSum);
            } else {
                preSum = Math.max(preSum + nums[i],nums[i]);
                maxMin = Math.max(maxMin,preSum);
            }
        }
        return maxMin;
    }

    /**
     * 前缀和的正确姿势
     *
     * @param nums
     * @return
     */
    public int maxAbsoluteSum(int[] nums) {
        int len = nums.length;
        // 初始化前缀和
        int[] prefix = new int[len];
        prefix[0] = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < len; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
            max = Math.max(prefix[i],max);
            min = Math.min(prefix[i],min);
        }
        return Math.max(max - min,Math.max(Math.abs(min),Math.abs(max)));
    }

}
