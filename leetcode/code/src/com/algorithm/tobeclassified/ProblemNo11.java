package com.algorithm.tobeclassified;

/**
  * @Description: leetcode-11:盛最多水的容器
  * @Author: zhangjunqiang
  * @Date: 2021/8/29 17:35
  * @version v1.0
  */
public class ProblemNo11 {

    /**
     * 双指针思路：
     * 起始状态：左指针在最左，右指针在最右，此时底最大
     * 移动指针：向内移动指针指向值较小的指针，取最大值
     * min(left,right) * t
     * 向内移动时，如果移动指向值较大的指针，min(left1,right1)必小于等于min(left,right),t1必小于t，面积必然更小
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int leftIndex = 0;
        int rightIndex = height.length - 1;
        int result = Math.min(height[leftIndex],height[rightIndex]) * (rightIndex - leftIndex);
        while (leftIndex < rightIndex) {
            if (height[leftIndex] > height[rightIndex]) {
                rightIndex -= 1;
            } else {
                leftIndex += 1;
            }
            result = Math.max(Math.min(height[leftIndex],height[rightIndex]) * (rightIndex - leftIndex),result);
        }
        return result;
    }

//    /**
//     * 贪心暴力枚举各区间，时间复杂度过高
//     *
//     * @param height
//     * @return
//     */
//    public static int maxArea(int[] height) {
//        int result = 0;
//        for (int i = 0; i < height.length; i++) {
//            for (int j = 1; j < height.length; j++) {
//                result = Math.max(Math.min(height[i],height[j]) * (j - i),result);
//            }
//        }
//        return result;
//    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

}
