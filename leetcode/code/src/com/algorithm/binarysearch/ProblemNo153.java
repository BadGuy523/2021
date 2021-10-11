package com.algorithm.binarysearch;

/**
  * @Description: leetcode-153:寻找旋转排序数组中的最小值
  * @Author: zhangjunqiang
  * @Date: 2021/9/21 10:57
  * @version v1.0
  */
public class ProblemNo153 {

    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            // 中间值小于右侧值：没有分段
            if (nums[mid] < nums[right]) {
                right = mid;
            } else { // 分段
                left = mid + 1;
            }
        }
        return nums[left];
    }

}
