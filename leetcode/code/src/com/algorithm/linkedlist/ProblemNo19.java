package com.algorithm.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @Description: leetcode19-删除链表的倒数第N个结点
 * @Author: zhangjunqiang
 * @Date: 2021/10/31 11:05
 */
public class ProblemNo19 {


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


    public static ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> nodeList = new ArrayList<>();
        ListNode dummy = new ListNode(-1, head);
        ListNode current = dummy;
        nodeList.add(current);
        while ((current = current.next) != null) {
            nodeList.add(current);
        }
        int lastIndex = nodeList.size() - 1;
        if (n == 1) {
            nodeList.get(lastIndex - n).next = null;
        } else {
            nodeList.get(lastIndex - n).next = nodeList.get(lastIndex - n + 1);
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4,n5);
        ListNode n3 = new ListNode(3,n4);
        ListNode n2 = new ListNode(2,n3);
        ListNode n1 = new ListNode(1,n2);
        removeNthFromEnd(n1,2);
    }
}
