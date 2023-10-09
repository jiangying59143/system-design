package org.example.algorithm;

import org.example.algorithm.dataStructure.ListNode;

public class Leetcode234 {
    public static boolean isPalindrome(ListNode head) {
        if(head.next == null){
            return true;
        }
        ListNode fast = head, slow = head, pre, dummyHead = new ListNode(), next;

        while(fast != null && fast.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;

            //revert
            next = dummyHead.next;
            dummyHead.next = pre;
            dummyHead.next.next = next;
        }

        ListNode nextPartHead = slow;

        //奇数的情况
        if(fast!=null){
            slow = slow.next;
        }

        boolean flag = true;
        ListNode cur = dummyHead.next;
        while(slow != null){
            if(slow.val != cur.val){
                flag = false;
                break;
            }
            slow = slow.next;
            cur = cur.next;
        }
        //revert
        cur = dummyHead.next;
        ListNode dummyHead2 = new ListNode(),tail = cur;
        while(cur != null){
            next = dummyHead2.next;
            dummyHead2.next = cur;
            cur = cur.next;
            dummyHead2.next.next = next;
        }
        tail.next = nextPartHead;

        return flag;
    }

    public static void main(String[] args) {
         ListNode head;

         head = new ListNode(1); head.next(2).next(2).next(1);
         System.out.println(isPalindrome(head));
         head.print();

         head = new ListNode(1); head.next(1);
         System.out.println(isPalindrome(head));
        head.print();

        head = new ListNode(1); head.next(2);
        System.out.println(isPalindrome(head));
        head.print();
    }
}
