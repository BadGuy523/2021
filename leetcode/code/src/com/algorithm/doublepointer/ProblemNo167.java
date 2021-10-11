package com.algorithm.doublepointer;

/**
  * @Description: leetcode-167:两数之和II-输入有序数组
  * @Author: zhangjunqiang
  * @Date: 2021/10/5 15:25
  * @version v1.0
  */
public class ProblemNo167 {

    public int[] twoSum(int[] numbers, int target) {
        // 声明左指针与右指针
        int leftPointer = 0;
        int rightPointer = numbers.length - 1;
        // 声明保存结果的数据
        int[] result = new int[2];
        // 分别从两头开始向内移动指针,若和大于目标值，则右指针左移，反之，左指针右移
        while (leftPointer < rightPointer) {
            if (numbers[leftPointer] + numbers[rightPointer] == target) {
                result[0] = leftPointer + 1;
                result[1] = rightPointer + 1;
                break;
            } else if (numbers[leftPointer] + numbers[rightPointer] > target) {
                rightPointer--;
            } else {
                leftPointer++;
            }
        }
        return result;
    }

}
