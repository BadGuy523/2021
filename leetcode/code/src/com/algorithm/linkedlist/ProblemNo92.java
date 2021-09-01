package com.algorithm.linkedlist;

import java.util.List;

/**
  * @Description: leetcode-92:翻转列表2，翻转指定两个节点之间的链表
  * @Author: zhangjunqiang
  * @Date: 2021/8/29 18:45
  * @version v1.0
  */
public class ProblemNo92 {

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left > right) {
            return null;
        }
        if (left == right) {
            return head;
        }
        ListNode dummy = new ListNode(-1,head);
        head = dummy;
        // 获取到分段的起始节点
        for (int i = 1; i < left; i++) {
            head = head.next;
        }
        ListNode preLeftNode = head;
        ListNode leftNode = head.next;
        ListNode rightNode = leftNode;
        ListNode leftNextNode = leftNode.next;

        // 链表翻转1的思路
        for (int i = left; i < right; i++) {
            ListNode next = leftNextNode.next;
            leftNextNode.next = rightNode;
            rightNode = leftNextNode;
            leftNextNode = next;
        }
        // 特殊处理分段外的节点
        preLeftNode.next = rightNode;
        leftNode.next = leftNextNode;
        return dummy.next;
    }

    public static void main(String[] args) {
        ProblemNo92.ListNode five = new ProblemNo92.ListNode(5,null);
        ProblemNo92.ListNode four = new ProblemNo92.ListNode(4,five);
        ProblemNo92.ListNode three = new ProblemNo92.ListNode(3,four);
        ProblemNo92.ListNode two = new ProblemNo92.ListNode(2,three);
        ProblemNo92.ListNode one = new ProblemNo92.ListNode(1,two);
        ProblemNo92.ListNode listNode = reverseBetween(one,2,4);
        System.out.println();
    }

    static class ListNode {
        int val;
        ProblemNo92.ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ProblemNo92.ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
