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
                pos[1] = i + maxLen/2;
            }
        }
        return s.substring(pos[0], pos[1]+1);
    }

    public static String longestPalindrome1(String s){
        if(s == null || s.length() <= 1){
            return s;
        }
        s = getManacherStr(s);

        int C = 0, R = -1, start = 0, end = 0,len, maxLen = 1;
        int[] parr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if(R >= i){
                int initArmLen = Math.min(parr[2*C-i], R - i);
                len = expandToMax(s, i-initArmLen, i+initArmLen);
            }else{
                len = expandToMax(s, i, i);
            }

            parr[i] = len/2;

            if(i + len/2 > R){
                C = i;
                R = i + len/2;
            }

            if(len > maxLen){
                maxLen = len;
                start = i - len/2;
                end = i + len/2;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if(s.charAt(i) != '#'){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    private static String getManacherStr(String s){
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append("#");
        }
        return sb.toString();
    }

    private static int expandToMax(String s, int i, int j){
        while(i >=0 && j < s.length() && s.charAt(i) == s.charAt(j)){
            i--; j++;
        }
        return j-i-1;
    }

    public static void main(String[] args) {
        String s;

        s = "babad";
        System.out.println("原 string : " + s);
        System.out.println("中心扩展方法 ： " + longestPalindrome(s));
        System.out.println("manacher方法 ： " + longestPalindrome1(s));
        System.out.println("-----------------------------------------");

        s = "cbbd";
        System.out.println("原 string : " + s);
        System.out.println("中心扩展方法 ： " + longestPalindrome(s));
        System.out.println("manacher方法 ： " + longestPalindrome1(s));
        System.out.println("-----------------------------------------");
    }
}
