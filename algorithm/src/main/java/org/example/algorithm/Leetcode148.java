package org.example.algorithm;

import org.example.algorithm.dataStructure.ListNode;

public class Leetcode148 {
    public static ListNode sortList(ListNode head) {
        ListNode dummyHead = new ListNode(), cur = dummyHead;
        int length = getLength(head);
        for(int step=1; step <= length; step <<= 1){
            while(head != null) {
                ListNode a = new ListNode(), ca = a, b = new ListNode(), cb = b;
                for (int i = 0; i < step && head != null; i++) {
                    ca.next = head;
                    head = head.next;
                    ca.next.next = null;
                    ca = ca.next;
                }
                for (int i = 0; i < step && head != null; i++) {
                    cb.next = head;
                    head = head.next;
                    cb.next.next = null;
                    cb = cb.next;
                }
                //todo a.next, b.next
                ListNode[] headTail = merge(a.next, b.next);
                cur.next = headTail[0];
                cur = headTail[1];
            }
            head = dummyHead.next;
            cur = dummyHead;
        }
        return dummyHead.next;
    }

    private static int getLength(ListNode head){
        int ans = 0;
        while(head != null){
            head = head.next;
            ans++;
        }
        return ans;
    }

    private static ListNode[] merge(ListNode a, ListNode b){
        ListNode dummyHead = new ListNode(),
                //todo 漏了
        cur = dummyHead;
        while(a != null && b != null){
            if(a.val < b.val){
                cur.next = a;
                a = a.next;
                //todo 漏了
                cur.next.next = null;
                //todo 漏了
                cur = cur.next;
            }else{
                cur.next = b;
                b = b.next;
                //todo 漏了
                cur.next.next = null;
                //todo 漏了
                cur = cur.next;
            }
        }

        cur = insertListNode(cur, a);

        cur = insertListNode(cur, b);

        return new ListNode[]{dummyHead.next, cur};
    }

    private static ListNode insertListNode(ListNode cur, ListNode a){
        while( a!= null) {
            cur.next = a;
            a = a.next;
            cur.next.next = null;
            cur = cur.next;
        }
        return cur;
    }

    public static void main(String[] args) {
        ListNode head;

//        head = new ListNode(4); head.next(2).next(1).next(3);
//        head.print();
//        sortList(head).print();

        head = new ListNode(1);
        head.print();
        sortList(head).print();
    }
}
