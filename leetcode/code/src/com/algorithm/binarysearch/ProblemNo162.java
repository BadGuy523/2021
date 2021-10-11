package com.algorithm.binarysearch;

/**
  * @Description: 寻找峰值
  * @Author: zhangjunqiang
  * @Date: 2021/9/21 11:01
  * @version v1.0
  */
class ProblemNo162 {
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            // 下降趋势，将右索引移到中间
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
