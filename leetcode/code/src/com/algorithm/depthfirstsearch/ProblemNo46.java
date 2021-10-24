package com.algorithm.depthfirstsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: leetcode-46:全排列
 * @author: BadGuy
 * @date: 2021-10-24 16:33
 **/
public class ProblemNo46 {

    /**
     * 有意思的递归：回溯条件&递归逻辑（关键）
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        // 声明记录结果集合
        List<List<Integer>> result = new ArrayList<>();
        // 判空
        if (nums == null || nums.length == 0) {
            return result;
        }
        // 临时集合
        List<Integer> tmpList = new ArrayList<>();
        // 递归
        dfs(result,nums,tmpList);
        return result;
    }

    /**
     * 递归逻辑：外层循环遍历每个数作为排列开头，内层循环通过递归，回溯，进行不同的排列组合
     * @param result
     * @param nums
     * @param tmpList
     */
    private void dfs(List<List<Integer>> result, int[] nums, List<Integer> tmpList) {
        // 递归回溯条件：tmpList作为排列组合之一，其容量和nums相同时，即为回溯条件
        if (tmpList.size() == nums.length) {
            result.add(new ArrayList<>(tmpList));
            return;
        }
        // 遍历nums中的每个数作为排列组合的开头数字
        for (int i = 0; i < nums.length; i++) {
            // 因为每个数字都不相同，所以不包含时才加入，以及进行递归操作
            if (!tmpList.contains(nums[i])) {
                tmpList.add(nums[i]);
                // 递归加入下一位组合数
                dfs(result,nums,tmpList);
                // 递归加入后，需要移除最后一位加入的数，继续回溯
                tmpList.remove(tmpList.size() - 1);
            }
        }
    }

}
