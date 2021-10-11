package com.algorithm.doublepointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
  * @Description: leetcode-15：三数之和
  * @Author: zhangjunqiang
  * @Date: 2021/10/5 15:42
  * @version v1.0
  */
public class ProblemNo15 {

    public List<List<Integer>> threeSum(int[] nums) {
        // 声明保存结果的集合
        List<List<Integer>> result = new ArrayList<>();
        // 对给定数组进行排序
        Arrays.sort(nums);
        // 给定数组的长度
        int length = nums.length;
        // 遍历每个数，然后进行双指针移动
        int currentPointer;
        int leftPointer;
        int rightPointer;
        for (int i = 0; i < length - 1; i++) {
            // 当前数与上一个数相同，跳过，避免重复
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            currentPointer = i;
            // 避免重复
            leftPointer = i + 1;
            rightPointer = length - 1;
            while (leftPointer < rightPointer) {
                if (nums[leftPointer] + nums[rightPointer] + nums[currentPointer] == 0) {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[leftPointer]);
                    item.add(nums[rightPointer]);
                    item.add(nums[currentPointer]);
                    result.add(item);
                    leftPointer++;
                    rightPointer--;
                    // 避免重复
                    while (leftPointer < rightPointer && nums[leftPointer] == nums[leftPointer - 1]) {
                        leftPointer++;
                    }
                    // 避免重复
                    while (leftPointer < rightPointer && nums[rightPointer] == nums[rightPointer + 1]) {
                        rightPointer--;
                    }
                } else if (nums[leftPointer] + nums[rightPointer] + nums[currentPointer] > 0) {
                    rightPointer--;
                } else {
                    leftPointer++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ProblemNo15 object = new ProblemNo15();
        List<List<Integer>> lists = object.threeSum(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0});
        System.out.println();
    }

}
