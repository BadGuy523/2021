package com.algorithm.linkedlist;

/**
 * @version v1.0
 * @Description: leetcode-206:翻转链表
 * @Author: zhangjunqiang
 * @Date: 2021/8/27 22:55
 */
public class ProblemNo206 {


     static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseList(ListNode head) {
         if (head == null) {
             return null;
         }
         ListNode pre = head;
         ListNode current = head.next;
         head.next = null;
         while(current != null) {
             ListNode nextNode = current.next;
             current.next = pre;
             pre = current;
             current = nextNode;
         }
         return pre;
    }

    public static void main(String[] args) {
        ListNode five = new ListNode(5,null);
        ListNode four = new ListNode(4,five);
        ListNode three = new ListNode(3,four);
        ListNode two = new ListNode(2,three);
        ListNode one = new ListNode(1,two);
        ListNode listNode = reverseList(one);
        System.out.println();
    }
}
