package com.algorithm.heappriorityqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
  * @Description: leetcode-506：相对名次
  * @Author: zhangjunqiang
  * @Date: 2021/10/5 10:54
  * @version v1.0
  */
public class ProblemNo506 {

    class Runner implements Comparable<Runner>{
        int score;
        int index;

        public Runner(int score, int index) {
            this.score = score;
            this.index = index;
        }

        @Override
        public int compareTo(Runner that) {
            return that.score - this.score;
        }
    }

    public String[] findRelativeRanks(int[] score) {
        if (score == null) {
            return new String[0];
        }
        // 选手个数
        int num = score.length;
        if (num == 0) {
            return new String[0];
        }
        // 记录排名数组
        String[] records = new String[num];
        // 奖牌数组初始化
        String[] medals = new String[]{"Gold Medal","Silver Medal","Bronze Medal"};
        int recordNum = 3;
        List<Runner> list = new ArrayList<>();
        // 保存索引和成绩
        for (int i = 0; i < score.length; i++) {
            list.add(new Runner(score[i],i));
        }
        // 根据成绩进行排序
        Collections.sort(list);
        for (int i = 0; i < num; i++) {
            if (i <= 2) {
                records[list.get(i).index] = medals[i];
            } else {
                records[list.get(i).index] = String.valueOf(++recordNum);
            }
        }
        return records;
    }

}
