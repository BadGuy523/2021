package com.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
  * @Description: 每日温度（单调栈，找第一个比自己大的数）
  * @Author: zhangjunqiang
  * @Date: 2021/9/21 11:15
  * @version v1.0
  */
class ProblemNo739 {

    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> deque = new ArrayDeque<>();
        int length = temperatures.length;
        int[] result = new int[length];
        // 倒序遍历，保证栈底->栈顶，降序，入栈时记录天数
        for(int i = length - 1; i >= 0; i--) {
            // 栈不为空，且栈顶元素小于当前值，栈顶元素出栈
            while(!deque.isEmpty() && temperatures[deque.peek()] <= temperatures[i]) {
                deque.pop();
            }
            if (deque.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = deque.peek() - i;
            }
            deque.push(i);
        }
        return result;
    }

}
