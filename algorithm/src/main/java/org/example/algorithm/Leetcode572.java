package org.example.algorithm;

import org.example.algorithm.dataStructure.TreeNode;

public class Leetcode572 {
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null && subRoot == null){
            return true;
        }
        if(root == null && subRoot != null || root!= null && subRoot == null){
            return false;
        }
        if(root.val == subRoot.val){
            //一定要是并且关系
            return isSubtree(root.left, subRoot.left) && isSubtree(root.right, subRoot.right);
        }else{
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }
    }

    public static void main(String[] args) {
        TreeNode root, subRoot;

        subRoot = new TreeNode(4);subRoot.left(1); subRoot.right(2);
        root = new TreeNode(3);root.left = subRoot; root.right(5);
        TreeNode.printTree(root);
        TreeNode.printTree(subRoot);
        System.out.println(isSubtree(root,subRoot));

        subRoot = new TreeNode(4);subRoot.left(1); subRoot.right(2);
        root = new TreeNode(3);TreeNode left = root.left(4);left.left(1); left.right(2).left(0); root.right(5);
        TreeNode.printTree(root);
        TreeNode.printTree(subRoot);
        System.out.println(isSubtree(root,subRoot));
    }
}
