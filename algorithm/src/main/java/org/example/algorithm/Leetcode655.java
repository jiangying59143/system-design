package org.example.algorithm;

import org.example.algorithm.dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode655 {

    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList<>();
        int height = getHeight(root), m = getRows(height), n = getColumns(height);
        for(int i=0; i<m; i++){
            List<String> subList = new ArrayList<>();
            for(int j=0; j<n; j++){
                subList.add("");
            }
            res.add(subList);
        }
        process(root, height, 0,  (n-1)/2, res);
        return res;
    }

    private void process(TreeNode node, int height, int r, int c, List<List<String>> res){
        if(node == null){
            return;
        }
        res.get(r).set(c, String.valueOf(node.val));
        process(node.left, height,  r+1, getColumnIndex(height, r, c, true), res);
        process(node.right, height, r+1,getColumnIndex(height, r, c, false), res);
    }

    private int getHeight(TreeNode node){
        if(node == null){
            return -1;
        }
        return Math.max(getHeight(node.left)+1, getHeight(node.right)+1);
    }

    private int getColumnIndex(int height, int r, int c, boolean isLeft){
        if(isLeft) return c - (int)(Math.pow(2, height-r-1));
        return c + (int)(Math.pow(2, height-r-1));
    }

    private int getRows(int height){
        return height+1;
    }

    private int getColumns(int height){
        return (int)(Math.pow(2, height+1))-1;
    }

    public static void main(String[] args) {
//        System.out.println(new Leetcode655().getColumnIndex(2, 0, 3, true));
        TreeNode root;
        List<List<String>> res;

        root = new TreeNode(1);
        root.left(2);
        res = new Leetcode655().printTree(root);
        print(res);

        root = new TreeNode(1);
        root.left(2).right(4);root.right(3);
        res = new Leetcode655().printTree(root);
        print(res);

    }

    private static void print(List<List<String>> res ){
        for (List<String> re : res) {
            System.out.println(Arrays.toString(re.toArray()));
        }
    }
}
