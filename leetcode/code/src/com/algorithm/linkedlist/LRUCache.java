package com.algorithm.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
  * @Description: leetcode-146，LRU缓存：最近最少使用，若数据最近被访问过，那么将来被访问的几率也更高
  * @Author: zhangjunqiang
  * @Date: 2021/9/3 22:29
  * @version v1.0
  */
public class LRUCache {

    // 双向链表结构
    static class Node {
        int key;
        int value;
        Node pre;
        Node next;

        public Node(int key,int value) {
            this.key = key;
            this.value = value;
        }
    }

    // 哈希表保存key与Node映射关系
    Map<Integer,Node> LRUCacheMap;

    // 双线链表哨兵节点
    Node dummy;

    Node tail;

    int capcity;

    /**
     * 初始化LRU缓存
     * @param capcity
     */
    public LRUCache(int capcity) {
        LRUCacheMap = new HashMap<>(capcity);
        this.capcity = capcity;
        dummy = new Node(-1,-1);
        tail = new Node(-1,-1);
        dummy.next = tail;
        tail.pre = dummy;
    }

    /**
     * 根据key值获取缓存数据
     * @param key
     * @return
     */
    public int get(int key) {
        if (LRUCacheMap.containsKey(key)) {
            moveToTail(LRUCacheMap.get(key));
            return LRUCacheMap.get(key).value;
        } else {
            return -1;
        }
    }

    /**
     * 将key-value存入缓存
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (LRUCacheMap.containsKey(key)) {
            // 已存在此key值
            Node existNode = LRUCacheMap.get(key);
            existNode.value = value;
            LRUCacheMap.put(key,existNode);
            moveToTail(existNode);
        } else {
            Node newNode = new Node(key,value);
            Node firstNode = dummy.next;
            firstNode.pre = newNode;
            newNode.next = firstNode;
            newNode.pre = dummy;
            dummy.next = newNode;
            moveToTail(newNode);
            if (LRUCacheMap.size() == capcity) {
                Node deleteNode = dummy.next;
                Node nextNode = deleteNode.next;
                nextNode.pre = dummy;
                dummy.next = nextNode;
                LRUCacheMap.remove(deleteNode.key);
                LRUCacheMap.put(key,newNode);
            } else {
                LRUCacheMap.put(key,newNode);
            }
        }
    }

    public void moveToTail(Node currentNode) {
        if (currentNode.next == tail) {
            return;
        }
        Node preNode = currentNode.pre;
        Node nextNode = currentNode.next;
        Node tailPre = tail.pre;
        tail.pre = currentNode;
        currentNode.next = tail;
        currentNode.pre = tailPre;
        tailPre.next = currentNode;
        nextNode.pre = preNode;
        preNode.next = nextNode;
    }

}
