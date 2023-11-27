package org.example.algorithm;

import org.example.algorithm.dataStructure.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Leetcode337 {

    private Map<TreeNode, Integer> f = new HashMap<>(), g =  new HashMap<>();
    public int rob(TreeNode root) {
        if(root == null) return 0;
//        return process(root, false);
        process(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    private int process(TreeNode node, boolean flag){
        if(node == null) return 0;
        int ans = process(node.left, false) + process(node.right, false);
        if(!flag){
            ans = Math.max(ans,
                    node.val + process(node.left, true) + process(node.right, true));
        }
        return ans;
    }

    private void process(TreeNode node){
        if(node== null) return;
        process(node.left);
        process(node.right);
        g.put(node, node.val + f.getOrDefault(node.left, 0) + f.getOrDefault(node.right, 0));
        f.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }


    public static void main(String[] args) {
        TreeNode root;

        root = new TreeNode(3); root.left(2);
        System.out.println(new Leetcode337().rob(root));

        root = new TreeNode(3); root.left(2).right(3); root.right(3).right(1);
        System.out.println(new Leetcode337().rob(root));
    }
}
