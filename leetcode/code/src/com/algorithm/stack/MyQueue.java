package com.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
  * @Description: leetcode-232:用栈实现队列
  * @Author: zhangjunqiang
  * @Date: 2021/9/21 10:31
  * @version v1.0
  */
public class MyQueue {

    /**
     * 用于插入队列
     */
    Deque<Integer> insertDeque;

    /**
     * 用于查询队列
     */
    Deque<Integer> queryDeque;

    /**
     * 初始化
     */
    public MyQueue() {
        this.insertDeque = new ArrayDeque<>();
        this.queryDeque = new ArrayDeque<>();
    }

    /**
     * 入队尾方法：向插入栈中插入元素
     * @param x
     */
    public void push(int x) {
        insertDeque.push(x);
    }

    /**
     * 从队首弹出元素
     * @return
     */
    public int pop() {
        // 若查询栈中不为空，直接从查询栈中弹出栈顶元素
        if (!queryDeque.isEmpty()) {
            return queryDeque.pop();
        }
        // 查询栈为空，插入栈不为空，则先从插入栈弹出元素，并入查询栈
        while (!insertDeque.isEmpty()) {
            queryDeque.push(insertDeque.pop());
        }
        // 若查询栈为空，则队列为空，返回-1，否则返回查询栈栈顶元素即可
        if (!queryDeque.isEmpty()) {
            return queryDeque.pop();
        } else {
            return -1;
        }
    }

    /**
     * 查询队首元素
     * @return
     */
    public int peek() {
        // 若查询栈中不为空，直接从查询栈中查询栈顶元素
        if (!queryDeque.isEmpty()) {
            return queryDeque.peek();
        }
        // 查询栈为空，插入栈不为空，则先从插入栈中弹出元素，并入查询栈
        while (!insertDeque.isEmpty()) {
            queryDeque.push(insertDeque.pop());
        }
        // 若查询栈为空，则队列为空，返回-1，否则返回查询栈栈顶元素即可
        if (!queryDeque.isEmpty()) {
            return queryDeque.peek();
        } else {
            return -1;
        }
    }

    /**
     * 插入栈和查询栈都为空，队列才为空
     * @return
     */
    public boolean empty() {
        return insertDeque.isEmpty() && queryDeque.isEmpty();
    }

}
