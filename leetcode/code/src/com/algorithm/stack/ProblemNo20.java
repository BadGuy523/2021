package com.algorithm.stack;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
  * @Description: leetcode-20：有效的括号(简单栈)
  * @Author: zhangjunqiang
  * @Date: 2021/9/5 10:56
  * @version v1.0
  */
public class ProblemNo20 {

    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char aChar : chars) {
            if (stack.isEmpty()) {
                stack.push(aChar);
                continue;
            } else if (aChar == '}' && stack.peek() == '{') {
                stack.pop();
            } else if (aChar == ']' && stack.peek() == '[') {
                stack.pop();
            } else if (aChar == ')' && stack.peek() == '(') {
                stack.pop();
            } else {
                stack.push(aChar);
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}
