package com.algorithm.dynamicprogramming;

import java.util.*;

/**
 * @description: leetcode898:子数组按位或操作
 * @author: BadGuy
 * @date: 2021-12-05 16:11
 **/
public class ProblemNo898 {
    public static int subarrayBitwiseORs(int[] arr) {
        // 暴力求之
        // 遍历子数组长度
        int len = arr.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= len; i ++) {
            // 遍历起始索引
            for (int j = 0; j <= len - i; j++) {
                int result = 0;
                for (int k = j; k < j + i; k++) {
                    result |= arr[k];
                }
                set.add(result);
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        int i = subarrayBitwiseORs(new int[]{1, 1, 2});
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,1);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1 << 30);
        System.out.println((1 << 31) - 1);
        Map map2 = new TreeMap();
        Set set = new TreeSet();
    }
}
