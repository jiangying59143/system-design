package org.example.algorithm.dataStructure;

import org.example.algorithm.view.PrettyPrintTree;

import java.util.Arrays;

public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int val;

    public TreeNode(int val){
        this.val = val;
    }

    public TreeNode left(int val){
        TreeNode node = new TreeNode(val);
        this.left = node;
        return node;
    }

    public TreeNode right(int val){
        TreeNode node = new TreeNode(val);
        this.right = node;
        return node;
    }

    public static void printTree(TreeNode tree){
        var pt = new PrettyPrintTree<TreeNode>(
                (x) -> Arrays.asList(x.left, x.right),
                (x) -> String.valueOf(x.val)
        );
        pt.display(tree);
        System.out.println("--------------------------------------------------");
    }
}
