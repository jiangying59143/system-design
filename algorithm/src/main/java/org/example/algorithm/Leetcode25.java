package org.example.algorithm;

public class Leetcode25 {

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        public ListNode next(int val){
            ListNode listNode = new ListNode(val);
            next = listNode;
            return listNode;
        }

        @Override
        public String toString() {
            return val + "," + (next == null ? "null" : next.toString()) ;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        // prev 是fake head 和 反转后的tail节点
        ListNode dummyHead = new ListNode(),prev = dummyHead;
        dummyHead.next = head;
        ListNode[] headTail;
        while(prev.next != null){
            headTail = reverse(prev.next, k);
            prev.next = headTail[0];
            prev = headTail[1];
        }

        return dummyHead.next;
    }

    private static ListNode[] reverse(ListNode head, int k) {
        // next 是dummyHead 的下一个节点, tail 反转后的尾部节点
        ListNode dummyHead = new ListNode(), next, tail = head;
        for(int i=0; i<k; i++){
            // at last, if amount of remaining nodes are less than k, need to revert it back.
            if(head == null){
                return reverse(dummyHead.next, i);
            }
            // 头插法反转链表
            next = dummyHead.next;
            dummyHead.next = head;
            head = head.next;
            dummyHead.next.next = next;
        }

        tail.next = head;

        return new ListNode[]{dummyHead.next, tail};
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next(2).next(3).next(4).next(5);
        System.out.println(listNode);
        ListNode result = reverseKGroup(listNode, 2);
        System.out.println(result);
    }
}
