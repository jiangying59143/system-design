package org.example.algorithm;

import org.example.algorithm.dataStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Leetcode655 {

    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> list = new ArrayList<>();
        int height = getDepth(root)-1, r = getRows(height), c = getColumns(height);
        for(int i=0; i<r; i++){
            List<String> subList = new ArrayList<>();
            for(int j=0; j<c; j++){
                subList.add("");
            }
        }
        process(root, 0, list);
        return list;
    }

    private int getDepth(TreeNode node){
        if(node == null){
            return 0;
        }
        return Math.max(getDepth(node.left)+1, getDepth(node.right)+1);
    }

    private void process(TreeNode node, int height, List<List<String>> list){
        if(node == null){
            return;
        }
        process(node.left, height+1, list);
        process(node.right, height+1, list);


    }

    private int getRowIndex(int height){
        int r  = getRows(height)-1;
        return r+1;
    }

    private int getColumnIndex(int height){
        int c = getColumns(height)-1,r  = getRows(height)-1;;
        return c - (int)(Math.pow(2, height-r-1));
    }

    private int getRows(int height){
        return height+1;
    }

    private int getColumns(int height){
        return (int)(Math.pow(2, height+1))-1;
    }
}
