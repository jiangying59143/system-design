package org.example.algorithm;

import java.util.HashMap;
import java.util.Map;

public class Leetcode13 {
    static Map<Character, Integer> map = new HashMap<>(){{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};
    public static int romanToInt(String s) {
        if(s == null || s.length() == 0){
             return 0;
        }
        int ans = map.get(s.charAt(0));
        for(int i=1; i<s.length(); i++){
            if(map.get(s.charAt(i-1)) < map.get(s.charAt(i))){
                ans -= 2*map.get(s.charAt(i-1));
            }
            ans += map.get(s.charAt(i));
        }
        return ans;
    }

    public static int romanToInt1(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int ans = 0;
        for(int i=0; i<s.length(); i++){
            if(i < s.length()-1 && map.get(s.charAt(i)) < map.get(s.charAt(i+1)))
                ans -= map.get(s.charAt(i));
            else ans += map.get(s.charAt(i));
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt1("III"));

        System.out.println(romanToInt1("IV"));

        System.out.println(romanToInt1("IX"));

        System.out.println(romanToInt1("LVIII"));

        System.out.println(romanToInt1("MCMXCIV"));
    }
}
