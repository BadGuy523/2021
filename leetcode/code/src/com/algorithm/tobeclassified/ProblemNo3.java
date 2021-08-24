package com.algorithm.tobeclassified;

import java.util.ArrayList;
import java.util.List;

/**
  * @Description: leetcodeNo3-无重复字符的最大子序列长度(滑动窗口，哈希表)
  * @Author: zhangjunqiang
  * @Date: 2021/8/23 23:16
  * @version v1.0
  */
public class ProblemNo3 {

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        // 记录最大值
        int maxCount = 0;
        // 作为滑动窗口进行比较
        List<Character> container = new ArrayList<>();
        for (char aChar : chars) {
            // 如果有重复，需要删除滑动窗口中，重复字符及之前的字符
            if (container.contains(aChar)) {
                maxCount = Math.max(container.size(), maxCount);
                int deleteIndex = container.indexOf(aChar);
                for (int i = 0; i <= deleteIndex; i++) {
                    container.remove(0);
                }
            }
            container.add(aChar);
        }
        return Math.max(container.size(), maxCount);
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

}
