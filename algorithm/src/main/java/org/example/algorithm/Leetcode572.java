package org.example.algorithm;

import org.example.algorithm.dataStructure.TreeNode;

public class Leetcode572 {
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return process(root, subRoot, subRoot);
    }

    private static boolean process(TreeNode root, TreeNode subRoot, TreeNode origin){
        if(root == null && subRoot == null){
            return true;
        }
        if(root == null || subRoot == null){
            return false;
        }
        return root.val == subRoot.val && isSubtree(root.left, subRoot.left) && isSubtree(root.right, subRoot.right)
                || isSubtree(root.left, origin) || isSubtree(root.right, origin);
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
