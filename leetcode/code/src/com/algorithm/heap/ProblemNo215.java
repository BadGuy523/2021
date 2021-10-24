package com.algorithm.heap;

import java.util.PriorityQueue;

/**
 * @description: leetcode-215:数组中的第K个最大元素
 * @author: BadGuy
 * @date: 2021-10-24 10:50
 **/
public class ProblemNo215 {

    /**
     * heap，优先队列，缺点：需要对所有数据进行排序，效率并不高
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {
        // 声明优先队列，大数在队首
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        // 将数组中的数依次入队
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
        }
        // 取从队首开始第K个数即为第K大的数
        for (int i = 0; i < k - 1; i++) {
            queue.poll();
        }
        return queue.poll();
    }

    /**
     * 快速排序思想：找基准值对整个数组数据进行左右分组，第K大的数据在哪一组，就只需对那一组再进行分组排序，并不需要所有数据都排好序
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums,int k) {
        // 入参校验
        if (nums == null || nums.length == 0 || k > nums.length || k < 1) {
            return -1;
        }
        // 快速排序逻辑
        return quickSortK(nums,0,nums.length - 1,nums.length - k);
    }

    private int quickSortK(int[] nums, int left, int right, int targetIndex) {
        int start = left;
        int end = right;
        // 如果该分段已经排好序，那么直接返回目标索引对应的数
        if (left >= right) {
            return nums[targetIndex];
        }
        // 默认第一位数为基准值
        int baseValue = nums[left];
        while (left <= right) {
            // 从左边开始寻找比基准值大的第一个数
            while (nums[left] < baseValue && left <= right) {
                left++;
            }
            // 从右边开始寻找比基准值小的第一个数
            while (nums[right] > baseValue && left <= right) {
                right--;
            }
            // 如果left小于等于right,则两边进行交换,并且索引各自向前移位
            if (left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        // 如果目标索引在左分段
        if (targetIndex <= right) {
            return quickSortK(nums,start,right,targetIndex);
        }
        // 如果目标索引在右分段
        if (targetIndex >= left) {
            return quickSortK(nums,left,end,targetIndex);
        }
        return nums[targetIndex];
    }

}
