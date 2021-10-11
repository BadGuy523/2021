package com.algorithm.stack;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: leetcode-901:股票价格跨度，当前天价格往前找，小于或等于当前天的价格连续天数，包括当前天
 * @Author: zhangjunqiang
 * @Date: 2021/9/14
 */
//public class StockSpanner {
//
//    // 利用数组来存储索引，只用一个栈
//
//    int[] nums;
//
//    Deque<Integer> deque;
//
//    int count;
//
//    public StockSpanner () {
//        nums = new int[10005];
//        deque = new ArrayDeque<>();
//        count = 0;
//    }
//
//    public int next(int price) {
//        while (!deque.isEmpty() && nums[deque.peek()] <= price) {
//            deque.pop();
//        }
//        if (deque.isEmpty()) {
//            deque.push(count);
//            nums[count] = price;
//            return count++ + 1;
//        }
//        int top = deque.peek();
//        deque.push(count);
//        nums[count] = price;
//        return count++ - top;
//    }
//
//}
public class StockSpanner {

    // 用两个栈，一个栈存价格，一个栈存它之前有几天小于等于它

    Deque<Integer> pricesQueue;

    Deque<Integer> daysQueue;

    public StockSpanner () {
        pricesQueue = new ArrayDeque<>();
        daysQueue = new ArrayDeque<>();
    }

    public int next(int price) {
        int day = 1;
        while (!pricesQueue.isEmpty() && pricesQueue.peek() <= price) {
            pricesQueue.pop();
            day += daysQueue.pop();

        }
        pricesQueue.push(price);
        daysQueue.push(day);
        return day;
    }

}

