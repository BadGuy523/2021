package com.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
  * @Description: leetcode-503:下一个更大元素II
  * @Author: zhangjunqiang
  * @Date: 2021/9/21 11:47
  * @version v1.0
  */
public class ProblemNo503 {

    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> deque = new ArrayDeque<>();
        int length = nums.length;
        int[] result = new int[length];
        // 遍历两次数组，即可按循环数组进行遍历
        for (int i = 0; i < 2; i ++) {
            for (int j = 0; j < length; j++) {
                // 栈底->栈顶，降序
                while(!deque.isEmpty() && nums[deque.peek()] < nums[j]) {
                    result[deque.peek()] = nums[j];
                    deque.pop();
                }
                deque.push(j);
                // 第一次遍历，初始化下一个更大元素索引为-1
                if (i == 0) {
                    result[j] = -1;
                }
            }
        }
        return result;
    }

}
