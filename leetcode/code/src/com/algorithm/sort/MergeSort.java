package com.algorithm.sort;

/**
 * @description: 归并排序
 * @author: BadGuy
 * @date: 2021-10-17 14:02
 **/

public class MergeSort {

    public static void main(String[] args) {
        int[] array = new int[100000];

        //merge sort
        long start5 = System.currentTimeMillis();
        for (int k = 0; k < 10; k++) {
            for (int j = 0; j < array.length; j++) {
                array[j] =(int)(Math.random() * 10000);
            }
            mergeSort(array);
        }
        long end5 = System.currentTimeMillis();
        System.out.println("merge Sort->");
        System.out.println(end5 - start5);

    }

    //merge Sort
    public static void mergeSort(int[] array) {
        // 声明一个临时数组
        int[] temp = new int[array.length];
        // 归并排序之分治
        mergeSortImpl(array, 0, array.length - 1, temp);
    }
    public static void mergeSortImpl(int[] array, int start, int end, int[] temp) {
        // 为最小单元直接返回
        if (start >= end) {
            return;
        }
        // 将原数组平均分为两部分
        int mid = (start + end) / 2;
        // 对两部分进行递归
        mergeSortImpl(array, start, mid, temp);
        mergeSortImpl(array, mid + 1, end, temp);
        // 分治后的两段数组合并
        merge(array, start, mid, end, temp);
    }

    public static void merge(int[] array, int start, int mid, int end, int[] temp) {
        // 左半段起始索引
        int left = start;
        // 右半段起始索引
        int right = mid + 1;
        // 开始遍历的索引
        int index = start;
        // 当两段数组都还未完成遍历
        while (left <= mid && right <= end) {
            // 按数字大小选择左半段还是右半段的数按顺序加入临时数组
            if (array[left] < array[right]) {
                temp[index++] = array[left++];
            } else {
                temp[index++] = array[right++];
            }
        }
        // 当左半段数组还未遍历完成，直接将左半段数字依次加入临时数组
        while (left <= mid) {
            temp[index++] = array[left++];
        }
        // 当右半段数组还未遍历完成，直接将右半段数字依次加入临时数组
        while (right <= end) {
            temp[index++] = array[right++];
        }
        // 将这两段的数字，根据临时数组中的顺序修改目标数组中的顺序
        for (index = start; index <= end; index++) {
            array[index]= temp[index];
        }
    }
}

