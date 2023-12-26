package org.example.algorithm;

public class Leetcode97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;
        Boolean[][] dp = new Boolean[s1.length()+1][s2.length()+1];
        return process(s1, s2, s3, 0, 0, dp);
    }

    private boolean process(String s1, String s2, String s3, int i, int j, Boolean[][] dp){
        if(dp[i][j] != null) return dp[i][j];
        if(i == s1.length() && j == s2.length()){
            dp[i][j] = true;
            return true;
        }
        boolean flag1 = false, flag2 = false;
        if(i < s1.length() && s1.charAt(i) == s3.charAt(i+j)){
            flag1 = process(s1, s2, s3, i+1, j, dp);
        }
        if(j < s2.length() && s2.charAt(j) == s3.charAt(i+j)){
            flag2 = process(s1, s2, s3, i, j+1, dp);
        }
        dp[i][j] = flag1 || flag2;
        return dp[i][j];
    }

    public boolean isInterleave2(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;
        int m = s1.length(), n = s2.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[m][n] = true;
        for (int j = dp[m].length - 2; j >= 0; j--) {
            dp[m][j] = s2.charAt(j) == s3.charAt(m+j) ? dp[m][j] = dp[m][j+1] : false;
        }
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = dp[i].length-1; j >= 0; j--) {
                if(j == dp[i].length-1){
                    dp[i][j] = s1.charAt(i) == s3.charAt(i+j) ? dp[i+1][j] : false;
                }else{
                    if(s1.charAt(i) == s3.charAt(i+j)) dp[i][j] = dp[i+1][j];
                    if(s2.charAt(j) == s3.charAt(i+j)) dp[i][j] = dp[i][j] || dp[i][j+1];
                }
            }
        }
        return dp[0][0];
    }

    public boolean isInterleave3(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;
        int m = s1.length(), n = s2.length();
        boolean[] dp = new boolean[n+1];
        dp[n] = true;
        for (int j = dp.length - 2; j >= 0; j--) {
            dp[j] = s2.charAt(j) == s3.charAt(m+j) ? dp[j] = dp[j+1] : false;
        }
        for (int i = m-1; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                boolean flag1 = false, flag2 = false;
                if(s1.charAt(i) == s3.charAt(i+j)){
                    flag1 = dp[j];
                }
                if(j < n && s2.charAt(j) == s3.charAt(i+j)){
                    flag2 = dp[j+1];
                }
                dp[j] = flag1 || flag2;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        System.out.println(new Leetcode97().isInterleave(s1, s2, s3));
        System.out.println(new Leetcode97().isInterleave2(s1, s2, s3));
        System.out.println(new Leetcode97().isInterleave3(s1, s2, s3));
    }
}
