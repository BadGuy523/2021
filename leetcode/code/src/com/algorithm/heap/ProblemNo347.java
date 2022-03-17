package com.algorithm.heap;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ProblemNo347 {

    public static int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0) + 1);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        for (Map.Entry<Integer, Integer> entry : entries) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll().getKey();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(topKFrequent(new int[]{4,1,-1,2,-1,2,3},2));
    }

}
