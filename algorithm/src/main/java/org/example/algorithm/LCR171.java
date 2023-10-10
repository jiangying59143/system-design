package org.example.algorithm;

import org.example.algorithm.dataStructure.ListNode;

public class LCR171 {
    static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headB == null || headA== null){
            return null;
        }

        //todo connect B and A
        ListNode cur = headB;
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = headA;

        ListNode fast = headB, slow = headB;
        //  slow != fast 不应该放循环条件里, 因为初始时候就相等了
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            //
            if(slow == fast){
                break;
            }
        }

        // no circle
        if(fast == null || fast.next == null){
            //todo break connection of A and B
            cur.next = null;
            return null;
        }

        fast = headB;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        // break connection of A and B
        cur.next = null;
        return fast;
    }
    //todo 错误
    static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        while(headA != headB){
            headA = headA != null ? headA.next : headB;
            headB = headB != null ? headB.next : headA;
        }
        return headA;
    }

    static ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while(A != B){
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }

    public static void main(String[] args) {
        ListNode headA, headB, intersectedNode;

        intersectedNode = new ListNode(8);intersectedNode.next(4).next(5);
        headA = new ListNode(4);headA.next(1).next = intersectedNode;
        headB = new ListNode(5);headB.next(0).next(1).next = intersectedNode;
        headA.print();
        headB.print();
//        System.out.println(getIntersectionNode(headA, headB));
        System.out.println(getIntersectionNode3(headA, headB));
    }
}
