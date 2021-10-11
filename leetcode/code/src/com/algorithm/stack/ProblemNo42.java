package com.algorithm.stack;

/**
  * @Description: leetcode-42:接雨水
  * @Author: zhangjunqiang
  * @Date: 2021/9/21 12:07
  * @version v1.0
  */
class ProblemNo42 {
    ///**
    // * 双指针
    // *
    // * @param height
    // * @return
    // */
    //public int trap(int[] height) {
    //    int left = 0;
    //    int right = height.length - 1;
    //    int leftMax = 0;
    //    int rightMax = 0;
    //    int result = 0;
    //    while (left < right) {
    //        // 左指针高度小于左边最高高度
    //        if (height[left] < leftMax) {
    //            result += leftMax - height[left];
    //        }
    //        // 更新左边最高高度
    //        leftMax = Math.max(leftMax,height[left]);
    //        // 右指针高度小于右边最高高度
    //        if (height[right] < rightMax) {
    //            result += rightMax - height[right];
    //        }
    //        // 更新右边最高高度
    //        rightMax = Math.max(rightMax,height[right]);
    //        if (leftMax < rightMax) {
    //            left++;
    //        } else {
    //            right--;
    //        }
    //    }
    //    return result;
    //}

    ///**
    // * 单调栈
    // *
    // * @param height
    // * @return
    // */
    //public int trap(int[] height) {
    //    Deque<Integer> deque = new ArrayDeque<>();
    //    int result = 0;
    //    for (int i = 0; i < height.length; i++) {
    //        while (!deque.isEmpty() && height[deque.peek()] <= height[i]) {
    //            Integer top = deque.pop();
    //            // 栈中有两个元素时才可进行计算
    //            if (deque.isEmpty()) {
    //                break;
    //            }
    //            result += (i - deque.peek() - 1) * (Math.min(height[deque.peek()],height[i]) - height[top]);
    //        }
    //        deque.push(i);
    //    }
    //    return result;
    //}

    /**
     * 动态规划
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int length = height.length;
        int[] leftMax = new int[length];
        int[] rightMax = new int[length];
        int result = 0;
        for (int i = 0; i < length; i++) {
            leftMax[i] = i == 0 ? height[i] : Math.max(leftMax[i - 1],height[i]);
        }
        for (int i = length - 1; i >= 0; i--) {
            rightMax[i] = i == length - 1 ? height[length - 1] : Math.max(rightMax[i + 1],height[i]);
        }
        for (int i = 0; i < length; i++) {
            result += Math.min(rightMax[i],leftMax[i]) - height[i];
        }
        return result;
    }
}
