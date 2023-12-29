package org.example.algorithm;

import org.example.algorithm.dataStructure.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Leetcode105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return process(preorder, inMap, 0, preorder.length-1, 0, inorder.length-1);
    }

    private TreeNode process(int[] preorder,Map<Integer, Integer> inMap, int preSt, int preEd, int inSt, int inEd){
        TreeNode node = new TreeNode(preorder[preSt]);
        int pivot = inMap.get(preorder[preSt]), leftLen = pivot-inSt, rightLen = inEd - pivot;
        if(leftLen > 0)
        node.left = process(preorder, inMap, preSt + 1, preSt + leftLen, inSt, pivot-1);
        if(rightLen > 0)
        node.right = process(preorder, inMap,  preEd-rightLen+1, preEd, pivot+1, inEd);
        return node;
    }

    private static void pre(TreeNode root){
        if(root == null) return;
        TreeNode cur = root;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(cur);
        while(!stack.isEmpty()){
            cur = stack.pop();
            System.out.print(cur.val + " ");
            if(cur.right != null) stack.push(cur.right);
            if(cur.left != null) stack.push(cur.left);
        }
        System.out.println();
    }

    private static void in(TreeNode root){
        if(root == null) return;
        TreeNode cur = root;
        Deque<TreeNode> stack = new LinkedList<>();
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
                continue;
            }
            cur = stack.pop();
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    private static void post(TreeNode root){
        if(root == null) return;
        TreeNode cur = root;
        Deque<TreeNode> stack = new LinkedList<>(), postStack = new LinkedList<>();
        stack.push(cur);
        while(!stack.isEmpty()){
            cur = stack.pop();
            postStack.push(cur);
            if(cur.left != null) stack.push(cur.left);
            if(cur.right != null) stack.push(cur.right);
        }
        while(!postStack.isEmpty()) System.out.print(postStack.pop().val + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] preorder, inorder;

        preorder = new int[]{3,9,20,15,7};
        inorder = new int[]{9,3,15,20,7};
        TreeNode root = new Leetcode105().buildTree(preorder, inorder);
        TreeNode.printTree(root);
        pre(root);
        in(root);
        post(root);
        preorder = new int[]{1,2,3};
        inorder = new int[]{2,3, 1};
        TreeNode.printTree(new Leetcode105().buildTree(preorder, inorder));
    }
}
