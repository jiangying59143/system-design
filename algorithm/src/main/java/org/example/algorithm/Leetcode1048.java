package org.example.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Leetcode1048 {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int[] dp = new int[words.length];
        dp[0] = 1;
        int maxLen = 1;
        for(int i = 1; i < words.length; i++){
            dp[i] = 1;
            for(int j = i-1; j >=0; j--){
                if(matched(words, j, i)){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        System.out.println(Arrays.toString(dp));
        return maxLen;
    }

    public int longestStrChain2(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        Map<String, Integer> map = new HashMap<>();
        int maxLen = 1;
        map.put(words[0], 1);
        for(int i = 1; i < words.length; i++){
            map.put(words[i], 1);
            for (int k = 0; k < words[i].length(); k++) {
                String removeKStr = words[i].substring(0, k) + words[i].substring(k+1);
                if(map.containsKey(removeKStr)){
                    map.put(words[i], Math.max(map.get(words[i]), map.get(removeKStr)+1));
                }
            }
            maxLen = Math.max(maxLen, map.get(words[i]));
        }
        return maxLen;
    }

    private boolean matched(String[] words, int i, int j){
        if(words[j].length() - words[i].length() != 1) return false;
        for (int k = 0; k < words[j].length(); k++) {
            String removeKStr = words[j].substring(0, k) + words[j].substring(k+1);
            if(removeKStr.equals(words[i])){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] words;

        words = new String[]{"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh",
                "zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"};
        System.out.println(new Leetcode1048().longestStrChain(words));
        System.out.println(new Leetcode1048().longestStrChain2(words));
    }
}
