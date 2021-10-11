package com.algorithm.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: leetcode-239：滑动窗口最大值
 * @Author: zhangjunqiang
 * @Date: 2021/9/14
 */
public class ProblemNo239 {

    /**
     * 定义对象保存每个数的值和索引
     */
    static class Node {
        int num;
        int index;

        public Node(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }
    //
    ///**
    // * 优先队列
    // *
    // * @param nums
    // * @param k
    // * @return
    // */
    //public int[] maxSlidingWindow(int[] nums, int k) {
    //    int n = nums.length;
    //    // 优先队列：大的数在队首，相同数，索引小的在队首
    //    PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.num == o2.num ? o1.index - o2.index : o2.num - o1.num);
    //    int[] result = new int[n - k + 1];
    //    // 预先将第一个滑动窗口中的数加入队列
    //    for (int i = 0; i < k; i++) {
    //        queue.offer(new Node(nums[i],i));
    //    }
    //    // 第一个滑动窗口最大值为优先队列队首元素
    //    result[0] = queue.peek().num;
    //    for (int i = k; i < n; i++) {
    //        // 滑动窗口一次右移，加入新元素
    //        queue.offer(new Node(nums[i],i));
    //        // 若队首元素索引不在滑动窗口范围内，则出队
    //        while (queue.peek().index <= i - k) {
    //            queue.poll();
    //        }
    //        // 第i-k+1个滑动窗口的最大值为队首元素
    //        result[i - k + 1] = queue.peek().num;
    //    }
    //    return result;
    //}

    ///**
    // * 双端队列：保证队列内元素单调，并且队首元素在滑动窗口范围内
    // *
    // * @param nums
    // * @param k
    // * @return
    // */
    //public static int[] maxSlidingWindow(int[] nums,int k) {
    //    int n = nums.length;
    //    int[] result = new int[n - k + 1];
    //    // 声明双端队列
    //    Deque<Node> deque = new ArrayDeque<>();
    //    // 先将第一个滑动窗口的数入队，保证队首->队尾是依次递减的
    //    for (int i = 0; i < k; i++) {
    //        while (!deque.isEmpty() && deque.peekLast().num < nums[i]) {
    //            deque.pollLast();
    //        }
    //        deque.addLast(new Node(nums[i],i));
    //    }
    //    // 首个滑动窗口的最大值为队首元素
    //    result[0] = deque.peekFirst().num;
    //    for (int i = k; i < n; i++) {
    //        // 滑动窗口依次右移，并入队当前元素，保证队首->队尾是依次递减的
    //        while (!deque.isEmpty() && deque.peekLast().num < nums[i]) {
    //            deque.pollLast();
    //        }
    //        deque.addLast(new Node(nums[i],i));
    //        // 若队首元素的索引超出滑动窗口范围，则队首元素出队
    //        while(deque.peekFirst().index <= i - k) {
    //            deque.pollFirst();
    //        }
    //        // 第i-k+1个滑动窗口的最大值为队首元素
    //        result[i - k + 1] = deque.peekFirst().num;
    //    }
    //    return result;
    //}

    /**
     * 分块+预处理：预处理每个分组的前缀最大值和后缀最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // 前缀
        int[] prefixMax = new int[n];
        // 后缀
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; ++i) {
            // 如果i是k的倍数，那么前缀最大为自己
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            } else {
                // 否则前缀最大为：当前值与上一个值中取最大
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            // 如果为最后一个数，或者其下一个数索引为k的倍数，后缀最大为自己
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                // 否则后缀最大为：当前值与下一个值中取最大
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }

        int[] ans = new int[n - k + 1];
        // 每个分段都取：分段开始的后缀最大值和分段结束的前缀最大值中取最大
        for (int i = 0; i <= n - k; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }

    //public static void main(String[] args) {
    //    maxSlidingWindow(new int[]{1,-1},1);
    //}

}
