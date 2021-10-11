package com.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: leetcode-84:柱状图中最大的矩形（单调栈）
 * @Author: zhangjunqiang
 * @Date: 2021/9/7
 */
public class ProblemNo84 {

    /**
     * 左入栈一次，右入栈一次
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int result = 0;
        // 求每一个高度左右出现比它矮的第一个高度索引
        int[] lefts = new int[heights.length];
        int[] rights = new int[heights.length];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < heights.length; i++) {
            // 每个高度左边比其小的第一个高度索引
            // 如果栈非空且栈顶元素大于当前高度
            while (!deque.isEmpty() && heights[deque.peek().intValue()] >= heights[i]) {
                deque.pop();
            }
            // 栈为空，直接入栈索引，左边比它矮的为-1
            if (deque.isEmpty()) {
                deque.push(i);
                lefts[i] = -1;
            } else {
                lefts[i] = deque.peek();
                deque.push(i);
            }
        }
        deque.clear();
        for (int i = heights.length - 1; i >= 0; i--) {
            // 每个高度右边比其小的第一个高度索引
            // 如果栈非空且栈顶元素大于当前高度
            while (!deque.isEmpty() && heights[deque.peek().intValue()] >= heights[i]) {
                deque.pop();
            }
            // 栈为空，直接入栈索引，左边比它矮的为-1
            if (deque.isEmpty()) {
                deque.push(i);
                rights[i] = heights.length;
            } else {
                rights[i] = deque.peek();
                deque.push(i);
            }
        }
        for (int i = 0; i < heights.length; i++) {
            result = Math.max((rights[i] - lefts[i] - 1) * heights[i],result);
        }
        return result;
    }

    /**
     * 仅入栈一次
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int result = 0;
        // 求每一个高度左右出现比它矮的第一个高度索引
        int[] lefts = new int[heights.length];
        int[] rights = new int[heights.length];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < heights.length; i++) {
            // 每个高度左边右边一起求比其小的第一个高度索引
            // 如果栈非空且栈顶元素大于当前高度
            while (!deque.isEmpty() && heights[deque.peek().intValue()] >= heights[i]) {
                rights[deque.pop()] = i; // 索引出栈时，当前索引即为右边比它小的第一个
            }
            // 栈为空，直接入栈索引，左边比它矮的为-1，右边的入栈时默认为右边界最小
            if (deque.isEmpty()) {
                deque.push(i);
                lefts[i] = -1;
                rights[i] = heights.length;
            } else {
                lefts[i] = deque.peek();
                deque.push(i);
                rights[i] = heights.length;
            }
        }
        for (int i = 0; i < heights.length; i++) {
            result = Math.max((rights[i] - lefts[i] - 1) * heights[i],result);
        }
        return result;
    }

}
