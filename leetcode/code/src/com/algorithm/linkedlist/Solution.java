package com.algorithm.linkedlist;

import java.util.*;


public class Solution {

    // 记录使用频繁数据的数据结构，链表
    class Node {
        int val;
        int key;
        Node prev;
        Node next;
        public Node(int key,int val){
            this.val = val;
            this.key = key;
        }
    }

    // 缓存
    Map<Integer,Node> cache = new HashMap<>();

    // 缓存容量
    int size;

    // 哨兵节点（头）
    Node dummy = new Node(-1,-1);

    // 哨兵节点（尾）
    Node tail = new Node(-1,-1);

    void init(int size) {
        this.dummy.next = tail;
        this.tail.prev = dummy;
        this.size = size;
    }

    public void set(int key,int value) {
        processNode(new Node(key,value));
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        } else {
            processNode(node);
            return node.val;
        }
    }

    // 将某一节点放入链表中，并更新缓存
    public void processNode(Node cur) {
        // 分情况：1、链表中不存在，且超容量 2、链表中不存在，未超容量 3、链表中存在
        if (!cache.containsKey(cur.key) && cache.size() >= size) {
            // 删除尾节点及缓存
            Node deleteNode = tail.prev;
            cache.remove(deleteNode.key);
            Node lastPreNode = tail.prev.prev;
            lastPreNode.next = tail;
            tail.prev = lastPreNode;

            // 在头结点加当前节点
            Node firstNode = dummy.next;
            cur.prev = dummy;
            cur.next = firstNode;
            firstNode.prev = cur;
            dummy.next = cur;
            cache.put(cur.key,cur);
        } else if (!cache.containsKey(cur.key) && cache.size() < size) {
            // 在头节点加当前节点
            Node firstNode = dummy.next;
            cur.prev = dummy;
            cur.next = firstNode;
            firstNode.prev = cur;
            dummy.next = cur;
            cache.put(cur.key,cur);
        } else {
            Node newCur = cur;
            Node head = dummy.next;
            cur = cache.get(cur.key);
            cur.val = newCur.val;
            if (cur.prev == dummy) {
                return;
            }

            Node curPrev = cur.prev;
            Node curNext = cur.next;
            dummy.next = cur;
            cur.prev = dummy;
            cur.next = head;
            head.prev = cur;
            curPrev.next = curNext;
            curNext.prev = curPrev;

            cache.put(cur.key,cur);
        }
    }

    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        // 初始化结构
        init(k);
        // 记录get结果
        List<Integer> result = new ArrayList<>();
        // 遍历操作数据
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 1) {
                // 调用set方法
                set(operators[i][1],operators[i][2]);
            } else {
                // 调用get方法
                int tmp = get(operators[i][1]);
                // 保存get结果
                result.add(tmp);
            }
        }
        // 将结果存入数组
        int[] results = new int[result.size()];
        int index = 0;
        for (Integer value : result) {
            results[index++] = value;
        }
        return results;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.LRU(new int[][]{{1,1,1},{1,2,2},{1,1,3},{2,1},{2,1}},3);
    }


}