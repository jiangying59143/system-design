package org.example.algorithm;

public class Leetcode5 {
    public static String longestPalindrome(String s) {
        if(s.length() == 0) return "";
        int maxLen = 0, expandMaxLen;
        int[] pos = new int[2];
        for (int i = 1; i < s.length(); i++) {
            expandMaxLen = Math.max(expandToMax(s, i, i), expandToMax(s, i, i+1));
            if(expandMaxLen > maxLen){
                maxLen = expandMaxLen;
                pos[0] = i - (maxLen-1)/2;
                pos[1] = i - maxLen/2;
            }
        }
        return s.substring(pos[0], pos[1]+1);
    }

    private static int expandToMax(String s, int i, int j){
        while(i >=0 && j < s.length() && s.charAt(i) == s.charAt(j)){
            i--; j++;
        }
        return j-i-1;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
    }
}
