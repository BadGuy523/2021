package com.algorithm.topologysorting;

import java.util.LinkedList;
import java.util.Queue;

/**
  * @Description: leetcode-210：课程表II
  * @Author: zhangjunqiang zwx925931
  * @Date: 2021/10/21
  */
public class ProblemNo210 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 记录每个点的入度
        int[] toNodes = new int[numCourses];
        // 根据课程pre规则统计各点入度
        for (int i = 0; i < prerequisites.length; i++) {
            toNodes[prerequisites[i][0]]++;
        }
        // 记录下一次入度为0的点
        Queue<Integer> queue = new LinkedList<>();
        // 记录结果的集合
        int[] result = new int[numCourses];
        // 将入度为0的点加入队列
        for (int i = 0; i < toNodes.length; i++) {
            if (toNodes[i] == 0) {
                queue.add(i);
                toNodes[i] = -1;
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            result[index++] = poll;
            for (int i = 0; i < prerequisites.length; i++) {
                // 若入度为0的点等于pre规则的被依赖点，则依赖点的入度-1
                if (prerequisites[i][1] == poll) {
                    toNodes[prerequisites[i][0]]--;
                }
            }
            // 将入度为0的点加入队列
            for (int i = 0; i < toNodes.length; i++) {
                if (toNodes[i] == 0) {
                    queue.add(i);
                    toNodes[i] = -1;
                }
            }
        }
        // 如果还存在入度不为0的点，则表示存在循环依赖
        for (int i = 0; i < toNodes.length; i++) {
            if (toNodes[i] > 0) {
                return new int[]{};
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ProblemNo210 solution = new ProblemNo210();
        solution.findOrder(2,new int[][]{{1,0}});
    }

}
