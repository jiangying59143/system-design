package org.example.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Leetcode17 {
    char[][] chars = new char[][]{
            {'a','b','c'},{'d','e','f'},
            {'g','h','i'},{'j','k','l'},{'m','n','o'},
            {'q','p','r','s'},{'t','u','v'},{'w','x','y','z'}
    };
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        process(digits, 0, new StringBuilder(), list);
        return list;
    }
    private void process(String digits, int index, StringBuilder sb, List<String> list){
        if(index == digits.length()){
            if(sb.length() > 0) list.add(sb.toString());
            return;
        }
        char[] charArr = chars[digits.charAt(index)-'2'];
        for(int i=0; i<charArr.length; i++){
            sb.append(charArr[i]);
            process(digits, index+1, sb, list);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public static void main(String[] args) {
        Leetcode17 object = new Leetcode17();
        System.out.println(object.letterCombinations("23"));
    }
}
