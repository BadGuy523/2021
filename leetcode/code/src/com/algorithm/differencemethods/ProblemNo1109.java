package com.algorithm.differencemethods;

/**
  * @Description: leetcode-1109：航班预定统计
  * @Author: zhangjunqiang
  * @Date: 2021/10/19
  */
public class ProblemNo1109 {

    public int[] corpFlightBookings(int[][] bookings,int n) {
        int[] diff = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            // 将某一区间的增量修改，映射为差分数组的修改，区间左边界+对应增量，区间右边界右边一位-对应增量
            diff[bookings[i][0] - 1] += bookings[i][2];
            // 边界已溢出，不用-对应增量
            if (bookings[i][1] < n) {
                diff[bookings[i][1]] -= bookings[i][2];
            }
        }
        for (int i = 1; i < n; i++) {
            diff[i] = diff[i - 1] + diff[i];
        }
        return diff;
    }

}
