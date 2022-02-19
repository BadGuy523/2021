package com.algorithm.string;

/**
 * @description: leetcode696:计数二进制子串
 * @author: BadGuy
 * @date: 2021-12-05 15:43
 **/
public class ProblemNo696 {
    public static int countBinarySubstrings(String s) {
        if (s.length() < 2) {
            return 0;
        }
        int left = 0;
        int right = 1;
        boolean flag = false;
        int result = 0;
        int leftCount = 1;
        int rightCount = 0;
        while (right <= s.length() - 1) {
            if (s.charAt(right - 1) == s.charAt(right)) {
                right++;
                if (!flag) {
                    leftCount++;
                } else {
                    rightCount++;
                }
                continue;
            }
            if (s.charAt(right - 1) != s.charAt(right) && !flag) {
                right++;
                rightCount++;
                flag = true;
                continue;
            }
            if ((s.charAt(right - 1) != s.charAt(right)) && flag) {
                flag = false;
                result += Math.min(leftCount,rightCount);
                left = right - rightCount;
                right = left + 1;
                leftCount = 1;
                rightCount = 0;
            }
        }
        if (flag) {
            result += Math.min(leftCount,rightCount);
        }
        return result;
    }

    public static void main(String[] args) {
        int i = countBinarySubstrings("00110011");
        System.out.println(i);
    }
}
