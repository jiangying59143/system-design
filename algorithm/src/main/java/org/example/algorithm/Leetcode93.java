package org.example.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        process(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void process(String s, int i, List<String> temp, List<String> res){
        if(i == s.length()){
            if(temp.size() == 4) res.add(String.join(".", temp));
            return;
        }

        if(temp.size() == 3 && (s.length() - i > 3 || s.length() - i == 3 && getThreeSum(s, i) > 255)){
            return;
        }
        temp.add(String.valueOf(s.charAt(i)));
        process(s, i+1, temp, res);
        temp.remove(temp.size()-1);

        if(s.charAt(i) == '0') return;

        if(i + 1 < s.length()) {
            temp.add(s.substring(i, i+2));
            process(s, i + 2, temp, res);
            temp.remove(temp.size()-1);
        }
        if(i + 2 < s.length() && getThreeSum(s, i) < 256) {
            temp.add(s.substring(i, i+3));
            process(s, i + 3, temp, res);
            temp.remove(temp.size()-1);
        }
    }

    private int getThreeSum(String s, int i){
        return (s.charAt(i) - '0') * 100 + (s.charAt(i+1) - '0') * 10 + (s.charAt(i+2) - '0');
    }

    public static void main(String[] args) {
//        List<String> list;
//        list = new Leetcode93().restoreIpAddresses("25525511135");
//        System.out.println(Arrays.toString(list.toArray()));

        System.out.println(Integer.bitCount(Integer.MAX_VALUE));
    }
}
