package org.example.algorithm;

import java.util.Arrays;

public class Leetcode32 {
    public int longestValidParentheses1(String s) {
        if(s == null || s.length()==0) return 0;
        int[] dp = new int[s.length()];
        dp[0] = 0;
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if(')' == s.charAt(i)){
                if('(' == s.charAt(i-1)) dp[i] = 2 + (i-2< 0 ? 0 : dp[i-2]);
                else if(i-dp[i-1]-1 >= 0 && '('  == s.charAt(i-dp[i-1]-1)) dp[i] = dp[i-dp[i-1]-1] + 2 + dp[i-1];
                max = Math.max(max, dp[i]);
//                else dp[i] = dp[i-1];
            }
//            else{
//                dp[i] = dp[i-1];
//            }
        }
//        System.out.println(Arrays.toString(dp));
//        return dp[dp.length-1];
        return max;
    }


    public int longestValidParentheses2(String s) {
        if(s == null || s.length()==0) return 0;
        int[] stack = new int[s.length() + 1];
        int si = -1, ret = 0;
        stack[++si] = -1;
        for (int i = 0; i < s.length(); i++) {
            if('(' == s.charAt(i)) stack[++si] = i;
            else{
                si--;
                if(si == -1) stack[++si] = i;
                else ret = Math.max(ret, i - stack[si]);
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        String s;

//        s = "(()";
//        System.out.println(new Leetcode32().longestValidParentheses1(s));
//        System.out.println(new Leetcode32().longestValidParentheses2(s));
//        System.out.println();
//        s = ")()())";
//        System.out.println(new Leetcode32().longestValidParentheses1(s));
//        System.out.println(new Leetcode32().longestValidParentheses2(s));
//        System.out.println();
//        s = "())";
//        System.out.println(new Leetcode32().longestValidParentheses1(s));
//        System.out.println(new Leetcode32().longestValidParentheses2(s));
//        System.out.println();

        s = "()(()";
        System.out.println(new Leetcode32().longestValidParentheses1(s));
//        System.out.println(new Leetcode32().longestValidParentheses2(s));
        System.out.println();

    }


}
