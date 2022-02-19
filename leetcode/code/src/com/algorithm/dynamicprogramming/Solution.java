package com.algorithm.dynamicprogramming;

/**
 * @description: test
 * @author: BadGuy
 * @date: 2021-12-12 19:16
 **/
public class Solution {
    /**
     动态规划思想：若一个子串为回文串，则左右两边的字符相等时，新的区间也为回文串
     */
    public static String longestPalindrome(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        // 声明存储各区间是否是回文串dp数组
        boolean[][] dp = new boolean[len][len];
        // 初始化最长回文子串的左右索引和长度
        int leftIndex = 0;
        int rightIndex = 0;
        int maxLen = 0;
        dp[0][0] = true;
        maxLen = 1;
        // 初始化长度为1和为2的子串回文串长度
        for (int i = 1; i < len;i ++) {
            dp[i][i] = true;
            leftIndex = i;
            rightIndex = i;
            if (chars[i - 1] == chars[i]) {
                dp[i - 1][i] = true;
                maxLen = 2;
                leftIndex = i - 1;
                rightIndex = i;
            }
        }
        // 遍历其余长度区间
        for (int left = 0; left < len; left++) {
            for (int right = left + 2; right < len; right++) {
                // 去除左右两边的字符，是一个回文串
                if (dp[left + 1][right - 1] && chars[left] == chars[right]) {
                    dp[left][right] = true;
                    if (right - left + 1 > maxLen) {
                        maxLen = right - left + 1;
                        leftIndex = left;
                        rightIndex = right;
                    }
                }
            }
        }
        return s.substring(leftIndex,rightIndex + 1);
    }

    public static void main(String[] args) {
        longestPalindrome("cbbd");
    }
}
