package com.algorithm.slidingwindow;



/**
 * @Description: leetcode-1208：尽可能使字符串相等
 * @Author: zhangjunqiang
 * @Date: 2021/10/11
 */
public class ProblemNo1208 {

    /**
     * 时间复杂度过高
     *
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public int equalSubstring2(String s, String t, int maxCost) {
        int result = 0;
        int length = s.length();
        int tmpResult;
        // 遍历滑动窗口的长度
        for (int windowLen = 1; windowLen <= length; windowLen++) {
            // 遍历每个滑动窗口的起始位置
            for (int i = 0; i < length - windowLen + 1; i++) {
                tmpResult = 0;
                // 计算每个窗口内全部替换是否满足最大消费
                for (int j = i; j < windowLen + i; j++) {
                    tmpResult += Math.abs(s.charAt(j) - t.charAt(j));
                    if (tmpResult > maxCost) {
                        break;
                    }
                }
                // 满足则更新结果值
                if (tmpResult <= maxCost) {
                    result = windowLen;
                }
            }
        }
        return result;
    }

    public int equalSubstring(String s, String t, int maxCost) {
        int result = 0;
        int length = s.length();
        int leftPointer = 0;
        int rightPointer = 0;
        int totalCost = 0;
        // 声明两个指针 进行滑动窗口的移动
        while (rightPointer < length) {
            // 将右指针指向的字母差异累加
            totalCost += Math.abs(s.charAt(rightPointer) - t.charAt(rightPointer));
            // 当总的花费大于最大花费时，左指针右移，并减去左指针字母差异
            while (totalCost > maxCost) {
                totalCost -= Math.abs(s.charAt(leftPointer) - t.charAt(leftPointer));
                leftPointer++;
            }
            // 在原长度和现长度取最大值
            result = Math.max(result,rightPointer - leftPointer + 1);
            rightPointer++;
        }
        return result;
    }

    public static void main(String[] args) {

    }

}

