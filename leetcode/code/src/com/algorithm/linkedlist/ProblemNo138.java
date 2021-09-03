package com.algorithm.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
  * @Description: leetcode-138:复制带随机指针的链表
  * @Author: zhangjunqiang
  * @Date: 2021/9/1 23:36
  * @version v1.0
  */
public class ProblemNo138 {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 两次循环，哈希表存copynode
     * @param head
     * @return
     */
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node dummy = new Node(-1);
        dummy.next = head;
        Map<Node,Node> map = new HashMap<>();
        map.put(head,new Node(head.val));
        while (head.next != null) {
            map.put(head.next,new Node(head.next.val));
            head = head.next;
        }
        head = dummy.next;
        while(head != null) {
            map.get(head).next = map.get(head.next);
            map.get(head).random = map.get(head.random);
            head = head.next;
        }
        return map.get(dummy.next);
    }

    Map<Node,Node> map = new HashMap<>();

    /**
     * 递归，哈希表存copynode
     * @param head
     * @return
     */
    public Node copyRandomList3(Node head) {
        if (head == null) {
            return null;
        }
        if (!map.containsKey(head)) {
            Node node = new Node(head.val);
            map.put(head,node);
            node.next = copyRandomList3(head.next);
            node.random = copyRandomList3(head.random);
        }
        return map.get(head);
    }

    // 不用哈希表，用本身链表进行拷贝
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node currentNode = head;
        while (currentNode != null) {
            // 将拷贝节点放在该节点之后，修改节点指针指向
            Node node = new Node(currentNode.val);
            node.next = currentNode.next;
            currentNode.next = node;
            currentNode = node.next;
        }
        currentNode = head;
        while (currentNode != null) {
            currentNode.next.random = currentNode.random != null ? currentNode.random.next : null;
            currentNode = currentNode.next.next;
        }
        // 自己逻辑,注意点：需要将原节点与拷贝节点的指向完全断开
        Node headNew = head.next;
        currentNode = head;
        Node next = currentNode.next.next;
        while (currentNode != null) {
            currentNode.next.next = next == null ? null : next.next;
            currentNode.next = next;
            currentNode = next;
            next = next == null ? null : currentNode.next.next;
        }
        // 官方逻辑
//        Node headNew = head.next;
//        currentNode = head;
//        while (currentNode != null) {
//            Node next = currentNode.next;
//            currentNode.next = currentNode.next.next;
//            next.next = next.next != null ? next.next.next : null;
//            currentNode = currentNode.next;
//        }
        return headNew;
    }

}
