package org.example.algorithm;

import org.example.algorithm.dataStructure.TreeNode;

public class Leetcode572 {

    //todo 超出时间限制
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
        return root.val == subRoot.val && process(root.left, subRoot.left, origin) && process(root.right, subRoot.right, origin)
                || process(root.left, origin, origin) || process(root.right, origin, origin);
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

        subRoot = new TreeNode(3);subRoot.left(1);subRoot.right(2);
        root = new TreeNode(3);root.left(4).left(1);root.right(5).left(2);
        TreeNode.printTree(root);
        TreeNode.printTree(subRoot);
        System.out.println(isSubtree(root,subRoot));
    }
}
