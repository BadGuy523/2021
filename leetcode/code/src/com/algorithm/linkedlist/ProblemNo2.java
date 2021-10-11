package com.algorithm.linkedlist;

/**
  * @Description: 两数相加
  * @Author: zhangjunqiang
  * @Date: 2021/9/21 10:16
  * @version v1.0
  */
public class ProblemNo2 {

    /**
     * 节点结构
     */
    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 记录结果的节点
        ListNode result = new ListNode(0);
        ListNode resultNode = result;
        int overInt = 0;
        // 因为链表中是倒叙的方式存储数字，所以首节点与首节点相加即是个位数开始相加，计算进位以及当前位数
        while (l1 != null || l2 != null) {
            result.next = new ListNode(((l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + overInt) % 10);
            overInt = ((l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + overInt) / 10;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            result = result.next;
        }
        // 若最后有进位，加入下一节点
        if (overInt > 0) {
            result.next = new ListNode(overInt);
        }
        return resultNode.next;
    }

}
