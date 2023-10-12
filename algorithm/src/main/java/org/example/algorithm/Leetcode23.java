package org.example.algorithm;

import org.example.algorithm.dataStructure.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Leetcode23 {
    //O(k*k*n) O(1)
    public static ListNode mergeKLists0(ListNode[] lists) {
        if(lists == null) return null;
        ListNode dummyHead = new ListNode(), cur = dummyHead;
        int minIndex;
        while(true){
            minIndex = -1;
            for(int i=0; i<lists.length; i++){
                if(lists[i] == null){
                    continue;
                }
                if(minIndex == -1 || lists[i].val < lists[minIndex].val){
                    minIndex = i;
                }
            }
            if(minIndex == -1) break;
            cur.next = lists[minIndex];
            cur = cur.next;
            lists[minIndex] = lists[minIndex].next;
        }
        return dummyHead.next;
    }

    //O(k*n*log(k)) O(k)
    public static ListNode mergeKLists(ListNode[] lists) {
        // todo  need to add lists.length == 0, otherwise PriorityQueue init would encounter error
        if(lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, Comparator.comparingInt(o -> o.val));
        for(int i=0; i<lists.length; i++){
            if(lists[i] != null) heap.add(lists[i]);
        }
        ListNode dummyHead = new ListNode(), cur=dummyHead;
        while(!heap.isEmpty()){
            ListNode node = heap.poll();
            cur.next = node;
            cur = cur.next;
            if(node.next!=null) heap.add(node.next);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode[] lists;

        lists = new ListNode[3];
        lists[0] = new ListNode(1); lists[0].next(4).next(5);
        lists[1] = new ListNode(1); lists[1].next(3).next(4);
        lists[2] = new ListNode(2); lists[2].next(6);
        for (ListNode listNode : lists) {
            listNode.print();
        }
        ListNode res = mergeKLists0(lists);
        res.print();
//        ListNode res1 = mergeKLists(lists);
//        res1.print();
    }
}
