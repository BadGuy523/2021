package com.algorithm.differencemethods;

import java.util.HashMap;
import java.util.Map;

/**
  * @Description: leetcode-1094:拼车
  * @Author: zhangjunqiang
  * @Date: 2021/10/19
  */
public class ProblemNo1094 {

    /**
     * 记录每个上车点和下车点的人数，遍历路程求解
     *
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling2(int[][] trips,int capacity) {
        Map<Integer,Integer> getonMap = new HashMap<>();
        Map<Integer,Integer> getoffMap = new HashMap<>();
        // 记录起点和终点
        int start = 1001;
        int end = -1;
        for (int i = 0; i < trips.length; i++) {
            // 记录上车的每个点及上车人数
            if (getonMap.containsKey(trips[i][1])) {
                getonMap.put(trips[i][1],getonMap.get(trips[i][1]) + trips[i][0]);
            } else {
                getonMap.put(trips[i][1],trips[i][0]);
            }
            // 记录下车的每个点及下车人数
            if (getoffMap.containsKey(trips[i][2])) {
                getoffMap.put(trips[i][2],getoffMap.get(trips[i][2]) + trips[i][0]);
            } else {
                getoffMap.put(trips[i][2],trips[i][0]);
            }
            start = Math.min(trips[i][1],start);
            end = Math.max(trips[i][2],end);
        }
        int count = 0;
        for (int i = start; i <= end ; i++) {
            // 先下后上
            if (getoffMap.get(i) != null) {
                count -= getoffMap.get(i);
            }
            if (getonMap.get(i) != null) {
                count += getonMap.get(i);
            }
            if (count > capacity) {
                return false;
            }
        }
        return true;
    }

    /**
     * 差分：前缀和的逆逻辑
     *
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips,int capacity) {
        // 记录起点和终点
        int start = 1001;
        int end = -1;
        int[] diff = new int[start];
        for (int i = 0; i < trips.length; i++) {
            start = Math.min(trips[i][1],start);
            end = Math.max(trips[i][2],end);
            // 记录每个点与上一个点的差值
            diff[trips[i][1]] += trips[i][0];
            diff[trips[i][2]] -= trips[i][0];
        }
        if (diff[start] > capacity) {
            return false;
        }
        int[] cap = new int[end + 1];
        cap[start] = diff[start];
        for (int i = start + 1; i <= end; i++) {
            // 前缀和逆逻辑，根据差值加当前值，判断有没有超载
            cap[i] = cap[i - 1] + diff[i];
            if (cap[i] > capacity) {
                return false;
            }
        }
        return true;
    }
    
}
