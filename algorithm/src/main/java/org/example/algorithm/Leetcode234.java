package org.example.algorithm;

import org.example.algorithm.dataStructure.ListNode;

public class Leetcode234 {
    public static boolean isPalindrome(ListNode head) {
        if(head.next == null){
            return true;
        }
        ListNode fast = head, slow = head, pre=slow, dummyHead = new ListNode(), next;

        while(fast != null && fast.next != null){
            //revert
            next = dummyHead.next;
            dummyHead.next = pre;
            dummyHead.next.next = next;

            slow = slow.next;
            pre = slow;
            fast = fast.next.next;
        }

        if(fast!=null){
            slow = slow.next;
        }

        ListNode cur = dummyHead.next;
        while(slow != null){
            if(slow.val != cur.val){
                return false;
            }
            slow = slow.next;
            cur = cur.next;
        }

        return true;
    }

    public static void main(String[] args) {
         ListNode head;

         head = new ListNode(1); head.next(2).next(2).next(1);
        System.out.println(isPalindrome(head));
    }
}
