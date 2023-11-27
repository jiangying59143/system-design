package org.example.algorithm;

import org.example.algorithm.dataStructure.TreeNode;
import org.example.algorithm.view.PrettyPrintTree;

import java.util.Arrays;
import java.util.Map;

public class Leetcode337 {
    static class MyTreeNode extends TreeNode{
        public MyTreeNode left;
        public MyTreeNode right;
        public int val;
        public int ans = -1;

        public MyTreeNode(int val) {
            super(val);
            this.val = val;
        }

        public static void printTree(MyTreeNode tree){
            var pt = new PrettyPrintTree<MyTreeNode>(
                    (x) -> Arrays.asList(x.left, x.right),
                    (x) -> String.valueOf(x.val)
            );
            pt.display(tree);
            System.out.println("--------------------------------------------------");
        }
    }
    public int rob(TreeNode root) {
        if(root == null) return 0;
        MyTreeNode mRoot = new MyTreeNode(root.val);
        copyTree(root, mRoot);
        MyTreeNode.printTree(mRoot);
        return process(mRoot, false);
    }
    private void copyTree(TreeNode node, MyTreeNode mNode){
        if(node == null) return;
        if(node.left != null) {
            mNode.left = new MyTreeNode(node.left.val);
        }
        if(node.right != null) {
            mNode.right = new MyTreeNode(node.right.val);
        }
        copyTree(node.left, mNode.left);
        copyTree(node.right, mNode.right);
    }

    private int process(MyTreeNode node, boolean flag){
        if(node == null) return 0;
//        if(node.ans != -1) return node.ans;
        int ans = process(node.left, false) + process(node.right, false);
        if(!flag){
            ans = Math.max(ans,
                    node.val + process(node.left, true) + process(node.right, true));
        }
        node.ans = ans;
        return ans;
    }


    public static void main(String[] args) {
        TreeNode root;

        root = new TreeNode(3); root.left(2);
        System.out.println(new Leetcode337().rob(root));

        root = new TreeNode(3); root.left(2).right(3); root.right(3).right(1);
        System.out.println(new Leetcode337().rob(root));
    }
}
