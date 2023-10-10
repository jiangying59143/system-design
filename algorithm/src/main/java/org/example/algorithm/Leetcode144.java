package org.example.algorithm;

import org.example.algorithm.dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode144 {
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list= new ArrayList<>();
        TreeNode cur = root, rightMost;
        while(cur != null){
            rightMost = cur.left;
            if(rightMost != null){
                while(rightMost.right != null && rightMost.right != cur){
                    rightMost = rightMost.right;
                }
                // todo 错误 rightMost == null
                if(rightMost.right == null){
                    list.add(cur.val);
                    rightMost.right = cur;
                    cur = cur.left;
                    continue;
                }else{
                    rightMost.right = null;
                }
            }else{
                list.add(cur.val);
            }
            cur = cur.right;
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root; List<Integer> list;
        root = new TreeNode(1); root.right(2).left(3);
        TreeNode.printTree(root);
        list = preorderTraversal(root);
        System.out.println(Arrays.toString(list.toArray()));
    }
}
