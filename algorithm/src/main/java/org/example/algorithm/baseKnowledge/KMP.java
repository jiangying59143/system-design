package org.example.algorithm.baseKnowledge;

import java.util.Arrays;

public class KMP {
    public static int[] getNext(String matcher){
        char[] p = matcher.toCharArray();
        int[] next = new int[p.length];
        int j=0, i=1;
        while(i < next.length){
            if(p[i] == p[j]) next[i++] = (j++) + 1;
            else if(j == 0) next[i++] = 0;
            else j = next[j-1];
        }
        return next;
    }

    public static int match(String ss, String matcher){
        if(ss == null || matcher == null || ss.length() < matcher.length()){
            return -1;
        }
        char[] s = ss.toCharArray();
        char[] p = matcher.toCharArray();
        int[] next = getNext(matcher);
        int i=0, j=0;
        while(i < s.length){
            if(s[i] == p[j]){
                i++;
                j++;
            }else if(j == 0) i++;
            else j = next[j-1];

            if(j == p.length){
                return i-j;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String matcher;
        String ss;
        ss="aaabbab";
        matcher= "bab";
        System.out.println(Arrays.toString(getNext(matcher)));
        System.out.println(match(ss, matcher));
    }
}
