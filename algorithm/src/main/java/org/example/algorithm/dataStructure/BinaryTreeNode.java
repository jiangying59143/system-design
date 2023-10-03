package org.example.algorithm.dataStructure;

import org.example.algorithm.view.PrettyPrintTree;

import java.util.Arrays;

public class BinaryTreeNode {
    public BinaryTreeNode left;
    public BinaryTreeNode right;
    public int val;

    public BinaryTreeNode(int val){
        this.val = val;
    }

    public BinaryTreeNode left(int val){
        BinaryTreeNode node = new BinaryTreeNode(val);
        this.left = node;
        return node;
    }

    public BinaryTreeNode right(int val){
        BinaryTreeNode node = new BinaryTreeNode(val);
        this.right = node;
        return node;
    }

    @Override
    public String toString() {
        return val + ":"
                + "left:" + (this.left == null ?  "" : this.left.toString())
                + "right:" + (this.right == null ?  "" : this.right.toString());
    }

    public static void printTree(BinaryTreeNode tree){
        var pt = new PrettyPrintTree<BinaryTreeNode>(
                (x) -> Arrays.asList(x.left, x.right),
                (x) -> String.valueOf(x.val)
        );
        pt.display(tree);
        System.out.println("--------------------------------------------------");
    }
}
