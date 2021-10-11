package com.algorithm.stack;

import java.util.Stack;

/**
  * @Description: leetcode-155:最小栈
  * @Author: zhangjunqiang
  * @Date: 2021/9/5 11:20
  * @version v1.0
  */
public class MinStack {

    Stack<Integer> regularStack;

    Stack<Integer> minStack;

    public MinStack() {
        regularStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        regularStack.push(val);
        if (minStack.isEmpty() || minStack.peek() >= val) {
            minStack.push(val);
        }
    }

    public void pop() {
        if (regularStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        regularStack.pop();
    }

    public int top() {
        return regularStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
