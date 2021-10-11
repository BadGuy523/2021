package com.algorithm.sort;

import java.util.Arrays;

/**
  * @Description: 经典排序之：冒泡排序、插入排序、选择排序
  * 排序算法的是否稳定：保证排序前2个相等的数在序列的前后位置和排序后相同
  * @Author: zhangjunqiang
  * @Date: 2021/10/7 17:04
  * @version v1.0
  */
public class SortEx {
    public static void main(String[] args) {
        int[] array = new int[1000];
        // bubble Sort
        long start1 = System.currentTimeMillis();
        for (int k = 0; k < 10; k++) {
            for (int j = 0; j < array.length; j++) {
                array[j] =(int)(Math.random() * 10000);
            }
            bubbleSort(array);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Bubble Sort->");
        System.out.println(end1 - start1);
//        System.out.println(Arrays.toString(array));
        //insert Sort
        long start2 = System.currentTimeMillis();
        for (int k = 0; k < 10; k++) {
            for (int j = 0; j < array.length; j++) {
                array[j] =(int)(Math.random() * 10000);
            }
            insertSort(array);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Insert Sort->");
        System.out.println(end2 - start2);
//        System.out.println(Arrays.toString(array));
        //select sort
        long start3 = System.currentTimeMillis();
        for (int k = 0; k < 10; k++) {
            for (int j = 0; j < array.length; j++) {
                array[j] =(int)(Math.random() * 10000);
            }
            selectSort(array);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("Select Sort->");
        System.out.println(end3 - start3);
//        System.out.println(Arrays.toString(array));
    }

    public static void bubbleSort(int[] array) {
        int length = array.length;
        // 外层循环控制循环多少次，每次会将最大的数放到最后
        for (int i = 0; i < array.length; i++) {
            // 内层循环控制每一次循环的比较交换次数：length - i
            for (int j = 1; j < length - i; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void insertSort(int[] array) {
        int insertNode;
        int j;
        // 假设前i个数字为已排序好的数组，从下一位开始向前比较，寻找自己合适的位置插入
        for (int i = 1; i < array.length; i++) {
            // 记录即将插入的数字
            insertNode = array[i];
            j = i - 1;
            // 向前寻找自己的位置，若是比自己大的数字，交换位置
            while(j >= 0 && insertNode < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            // 将数字插入自己的位置
            array[j + 1] = insertNode;
        }
    }

    public static void selectSort(int[] array) {
        // 外层循环控制循环多少次
        for (int i = 0; i < array.length; i++) {
            // 记录当前位置，作为最小数的索引
            int pos = i;
            // 从下一位开始寻找，若下一位小于当前数字，修改最小位置索引
            for (int j = i + 1; j < array.length; j++) {
                if (array[pos] > array[j]) {
                    pos = j;
                }
            }
            // 如果最小位置索引不是当前位置，交换位置
            if (pos != i) {
                int temp = array[i];
                array[i] = array[pos];
                array[pos] = temp;
            }
        }
    }
}
