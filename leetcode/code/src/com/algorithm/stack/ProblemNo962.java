package com.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: 最大宽度坡
 * @Author: zhangjunqiang
 * @Date: 2021/9/15
 */
public class ProblemNo962 {

    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int result = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        // 顺序入栈一次，比栈顶元素小的才入栈
        for (int i = 0; i < n; i++) {
            if (deque.isEmpty() || nums[deque.peek()] >= nums[i]) {
                deque.push(i);
            }
        }
        // 倒序入栈一次，若当前元素大于等于栈顶元素，就是栈顶元素为起点能到的最大宽度，出栈，继续，取最大值
        for (int i = n - 1; i >= 0 ; i--) {
            while (!deque.isEmpty() && nums[deque.peek()] <= nums[i]) {
                result = Math.max(i - deque.pop(),result);
            }
        }
        return result;
    }

}
