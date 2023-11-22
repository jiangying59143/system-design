package org.example.algorithm;

import org.example.algorithm.dataStructure.ListNode;

public class Leetcode19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(), cur = dummyHead, next = head;
        dummyHead.next = head;
        for (int i = 1; i <= n-1; i++) {
            next = next.next;
        }
        while(next.next != null){
            cur = cur.next;
            next = next.next;
        }
        cur.next = cur.next.next;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode head, res;
        head = ListNode.generate(new int[]{1,2,3,4,5});
        res = new Leetcode19().removeNthFromEnd(head, 2);
        ListNode.print(res);

        head = ListNode.generate(new int[]{1});
        res = new Leetcode19().removeNthFromEnd(head, 1);
        ListNode.print(res);

        head = ListNode.generate(new int[]{1,2});
        res = new Leetcode19().removeNthFromEnd(head, 1);
        ListNode.print(res);

    }
}
