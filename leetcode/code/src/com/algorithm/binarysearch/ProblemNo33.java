package com.algorithm.binarysearch;

/**
 * @Description: leetcode-33:搜索旋转排序数组（二分查找）
 * @Author: zhangjunqiang
 * @Date: 2021/9/7
 */
public class ProblemNo33 {

    public  int search(int[] nums,int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right) / 2;
        int result = -1;
        while (left <= right) {
            // 中间值位于左上升分段
            if (nums[mid] >= nums[left]) {
                if (target == nums[mid]) {
                    result = mid;
                    break;
                }
                // 结果位于右上升分段
                if (target < nums[left]) {
                    left = mid + 1;
                    mid = (left + right) / 2;
                } else {
                    // 结果位于左上升分段，判断中间值与目标值大小
                    if (target > nums[mid]) {
                        left = mid + 1;
                        mid = (left + right) / 2;
                    } else {
                        right = mid - 1;
                        mid = (left + right) / 2;
                    }
                }
            } else {// 中间值位于右上升分段
                if (target == nums[mid]) {
                    result = mid;
                    break;
                }
                // 结果位于左上升分段
                if (target >= nums[left]) {
                    right = mid - 1;
                    mid = (left + right) / 2;
                } else {
                    // 结果位于右上升分段，判断中间值与目标值大小
                    if (target > nums[mid]) {
                        left = mid + 1;
                        mid = (left + right) / 2;
                    } else {
                        right = mid - 1;
                        mid = (left + right) / 2;
                    }
                }
            }
        }
        return result;
    }

    //public static void main(String[] args) {
    //    System.out.println(search(new int[]{4,5,6,7,0,1,2},0));
    //}
}
