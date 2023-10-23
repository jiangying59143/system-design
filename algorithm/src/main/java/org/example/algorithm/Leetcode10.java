package org.example.algorithm;

import org.example.algorithm.dataStructure.Array;

public class Leetcode10 {
    public static boolean isMatchOfficial(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if(p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i][j-2];
                    if(charMatch(s, p, i-1, j-2)){
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }else{
                    dp[i][j] = charMatch(s,p, i-1, j-1) && dp[i-1][j-1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static boolean isMatch2(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        // first row is [true, false, false,...]
        dp[0][0] = true;
        // todo 不能写 i==1  sample: "":a*
        for(int i=0; i<dp.length; i++){
            for(int j=1; j<dp[i].length;j++){
                if(p.charAt(j-1) != '*'){
                    dp[i][j] = charMatch2(s, p, i-1, j-1) && dp[i-1][j-1];
                }else{
                    if(!charMatch2(s, p, i-1, j-2)){
                        dp[i][j] = dp[i][j-2];
                    }else{
                        dp[i][j] = dp[i-1][j] || dp[i][j-2];
                    }

                }
            }
        }
        return dp[s.length()][p.length()];
    }

    private static boolean charMatch2(String s, String p, int i, int j){
        if(i<0 || j<0){
            return false;
        }
        if(p.charAt(j) == '.'){
            return true;
        }
        if(s.charAt(i) == p.charAt(j)){
            return true;
        }
        return false;
    }

    private static boolean charMatch(String s, String p, int i, int j){
        if(i < 0 || j < 0){
            return false;
        }
        return p.charAt(j) == '.' || s.charAt(i) == p.charAt(j);
    }

    public static void main(String[] args) {
        String s, p;
        s = "aa";
        p = "a*";
        System.out.println(isMatchOfficial(s, p));
        System.out.println(isMatch(s, p));
        System.out.println(isMatch2(s, p));
    }
}
