package com.algorithm.sort;

/**
  * @Description: 快速排序
  * @Author: zhangjunqiang
  * @Date: 2021/10/7 17:04
  * @version v1.0
  */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = new int[1000000];
        //quick sort
        long start4 = System.currentTimeMillis();
        for (int k = 0; k < 10; k++) {
            for (int j = 0; j < array.length; j++) {
                array[j] =(int)(Math.random() * 10000);
            }
            quickSort(array);
        }
        long end4 = System.currentTimeMillis();
        System.out.println("quick Sort->");
        System.out.println(end4 - start4);
    }

    public static void quickSort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    public static void sort(int[] array, int start, int end) {
        // 已经递归到一个数字的数列，直接返回
        if (start >= end) {
            return;
        }
        // 默认基准值为第一个数
        int pivot = array[start];
        int left = start;
        int right = end;
        // 当左指针小于等于右指针
        while (left <= right) {
            // 左指针指向的数小于基准值，那么左指针右移
            while (left <= right && array[left] < pivot) {
                left++;
            }
            // 右指针指向的数大于基准值，那么右指针左移
            while (left <= right && array[right] > pivot) {
                right--;
            }
            // 确认左指针是否还小于等于右指针，满足条件，则交换两个数，且左指针右移，右指针左移
            if (left <= right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        // 递归进行子分段排序
        sort(array, start, right);
        sort(array, left, end);
    }
}
