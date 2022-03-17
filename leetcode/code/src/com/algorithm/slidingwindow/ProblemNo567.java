package com.algorithm.slidingwindow;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
  * @Description: leetcode-567: 字符串的排列
  * @Author: zhangjunqiang
  * @Date: 2021/10/31 12:09
  * @version v1.0
  */
public class ProblemNo567 {
    public static boolean checkInclusion(String s1, String s2) {
        Map<Character,Integer> countMap = new HashMap<>();
        initCountMap(countMap,s1);
        int left = 0;
        int right = 0;
        int len = s2.length();
        for (int i = 0; i <= s2.length() - s1.length();i++) {
            initCountMap(countMap,s1);
            for(int j = i; j < i + s1.length(); j ++) {
                if (countMap.get(s2.charAt(j)) != null && countMap.get(s2.charAt(j)) > 0) {
                    countMap.put(s2.charAt(j),countMap.get(s2.charAt(j)) - 1);
                }
            }
            boolean flag = true;
            for(int j = i; j < i + s1.length(); j ++) {
                if (countMap.get(s1.charAt(j)) == null || countMap.get(s1.charAt(j)) != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public static void initCountMap(Map<Character,Integer> countMap,String s1) {
        countMap.clear();
        for(int i = 0; i < s1.length(); i ++) {
            if (countMap.containsKey(s1.charAt(i))) {
                countMap.put(s1.charAt(i),countMap.get(s1.charAt(i)) + 1);
            } else {
                countMap.put(s1.charAt(i),1);
            }
        }
    }
    public static void main(String[] args) {
        boolean b = checkInclusion("hello", "ooolleoooleh");
        System.out.println(b);

        Map<Integer,Integer> map = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        AtomicInteger index = new AtomicInteger(0);
        index.getAndIncrement();



    }

}
