package com.algorithm.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: leetcode-2032:至少在两个数组中出现的值
 * @author: BadGuy
 * @date: 2021-10-16 16:47
 **/
public class ProblemNo2032 {

    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        // 记录结果集合
        List<Integer> result = new ArrayList<>();
        // 记录每个数字在不同数组出现的次数
        Map<Integer,Integer> map = new HashMap<>();
        // 记录每个数字在相同数组出现的次数
        Map<Integer,Integer> tmpMap = new HashMap<>();
        int[] nums = null;
        // 循环遍历三个数组
        for (int n = 0; n < 3; n++) {
            // 根据遍历次数确定需要遍历的数组
            if (n == 0) {
                nums = nums1;
            } else if (n == 1) {
                nums = nums2;
            } else {
                nums = nums3;
            }
            // 临时map清零
            tmpMap.clear();
            for (int i = 0; i < nums.length; i++) {
                // 如果在相同数组的记录map中已经存在，则计数+1
                if (tmpMap.containsKey(nums[i])) {
                    tmpMap.put(nums[i],tmpMap.get(nums[i]) + 1);
                } else {
                    // 不存在，则计数置1
                    tmpMap.put(nums[i],1);
                }
                // 如果在不同数组的记录map中已经存在，则判断在相同数组中的记录是否为1，为1则计数+1
                if (map.containsKey(nums[i]) && tmpMap.get(nums[i]) == 1) {
                    map.put(nums[i],map.get(nums[i]) + 1);
                } else if (!map.containsKey(nums[i])){
                    // 不存在，则计数置1
                    map.put(nums[i],1);
                }
            }
        }
        map.forEach((key, value) -> {
            if (value > 1) {
                result.add(key);
            }
        });
        return result;
    }

    public static void main(String[] args) {
        ProblemNo2032 solution = new ProblemNo2032();
        solution.twoOutOfThree(new int[]{1,1,3,2},new int[]{2,3},new int[]{3});
    }

}
