package org.example.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode22 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(0, 0, n, new StringBuilder(), res);
        return res;
    }

    private void dfs(int left, int right, int limit, StringBuilder sb, List<String> res){
        if(left > limit || right > limit || left < right){
            return;
        }
        if(left == limit && right == limit){
            res.add(sb.toString());
            return;
        }
        sb.append("(");
        dfs(left+1, right, limit, sb, res);
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        dfs(left, right+1, limit, sb, res);
        sb.deleteCharAt(sb.length()-1);
    }

    public static void main(String[] args) {
        List<String> res;

        res = new Leetcode22().generateParenthesis(3);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
