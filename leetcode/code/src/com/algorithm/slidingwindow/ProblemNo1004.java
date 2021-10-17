package com.algorithm.slidingwindow;

/**
  * @Description: leetcode-1004:最大连续1的个数III
  * @Author: zhangjunqiang
  * @Date: 2021/10/12
  */
public class ProblemNo1004 {
    
    public int longestOnes(int[] nums, int k) {
        int len = nums.length;
        int left = 0;
        int right = 0;
        // 记录最大连续1的个数
        int result = 0;
        // 记录滑动窗口内的0个数
        int zeroNum = 0;
        while (right < len) {
            // 当下一位数为0，则0计数器+1
            if (nums[right] == 0) {
                zeroNum++;
            }
            // 当滑动窗口中的0超过可翻转时，判断原左指针数是否为0，为0，0计数器-1。左指针右移
            while (zeroNum > k) {
                if (nums[left++] == 0) {
                    zeroNum--;
                }
            }
            // 取最大值
            result = Math.max(result, right++ - left + 1);
        }
        return result;
    }
    
}
