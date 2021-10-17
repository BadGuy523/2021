package com.algorithm.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
  * @Description: leetcode-560：和为K的子数组
  * @Author: zhangjunqiang
  * @Date: 2021/10/14
  */
public class ProblemNo560 {

    public int subarraySum(int[] nums, int k) {
        // 计算前缀和并且记录每种和出现的次数
        Map<Integer,Integer> countSumMap = new HashMap<>();
        int[] sums = new int[nums.length + 1];
        sums[0] = 0;
        countSumMap.put(0, 1);
        int result = 0;
        // 边计算前缀和边遍历求解，以i-1结尾的数为目标数组右边界，则满足条件的数组个数为countSumMap.get(sums[i] - k)
        // 因为sums[i] - sums[j - 1] = k(i >= j) 表示i-j的子数组为目标数组，所以有多少个满足条件的sums[j - 1]，就有多少个满足条件的子数组
        // 因为要满足i >= j,所以边计算前缀和边计数
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
            // 先计算result再计算当前和的计数，因为i >= j,则 i > j - 1,当前数的前缀和不能算入其中
            result += countSumMap.get(sums[i] - k) == null ? 0 : countSumMap.get(sums[i] - k);
            if (countSumMap.containsKey(sums[i])) {
                countSumMap.put(sums[i], countSumMap.get(sums[i]) + 1);
            } else {
                countSumMap.put(sums[i], 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }

}
