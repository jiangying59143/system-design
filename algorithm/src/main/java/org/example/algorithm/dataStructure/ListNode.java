package org.example.algorithm.dataStructure;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode next(int val){
        ListNode a = new ListNode(val);
        this.next = a;
        return a;
    }

    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        return "val=" + val;
    }

    public void print(){
        ListNode head = this;
        while(head != null){
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void print(ListNode listNode){
        if(listNode == null){
            System.out.println("null");
            return;
        }
        listNode.print();
    }

    public static ListNode generate(int[] nums){
        ListNode head = new ListNode(nums[0]), cur = head;
        for (int i = 1; i < nums.length; i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
        return head;
    }
}
