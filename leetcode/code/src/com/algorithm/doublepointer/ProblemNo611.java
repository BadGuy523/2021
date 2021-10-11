package com.algorithm.doublepointer;

import java.util.Arrays;

/**
  * @Description: leetcode-611:有效三角形的个数
  * @Author: zhangjunqiang
  * @Date: 2021/10/6 13:30
  * @version v1.0
  */
public class ProblemNo611 {

    public int triangleNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 记录结果
        int result = 0;
        // 排序
        Arrays.sort(nums);
        // 初始化当前边，与另外两条边
        int currentPointer;
        int leftPointer;
        int rightPointer;
        // 遍历每个数作为其中一条边：从左向右遍历
        for (int i = 0; i < nums.length - 1; i++) {
            currentPointer = i;
            leftPointer = i + 1;
            rightPointer = nums.length - 1;
            while (leftPointer < rightPointer) {
                if (nums[currentPointer] + nums[leftPointer] > nums[rightPointer]) {
                    result += rightPointer - leftPointer;
                    leftPointer = i + 1;
                    rightPointer--;
                } else {
                    leftPointer++;
                    if (leftPointer == rightPointer && rightPointer > i + 1) {
                        leftPointer = i + 1;
                        rightPointer--;
                    }
                }
            }
        }
        return result;
    }

    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 记录结果
        int result = 0;
        // 排序
        Arrays.sort(nums);
        // 初始化当前边，与另外两条边
        int currentPointer;
        int leftPointer;
        int rightPointer;
        // 遍历每个数作为其中一条边：从右向左遍历
        for (int i = nums.length - 1; i > 0; i--) {
            currentPointer = i;
            leftPointer = 0;
            rightPointer = i - 1;
            while (leftPointer < rightPointer) {
                if (nums[rightPointer] + nums[leftPointer] > nums[currentPointer]) {
                    result += rightPointer - leftPointer;
                    rightPointer--;
                } else {
                    leftPointer++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ProblemNo611 object = new ProblemNo611();
        int i = object.triangleNumber(new int[]{24, 3, 82, 22, 35, 84, 19});
        System.out.println(i);
    }

}
