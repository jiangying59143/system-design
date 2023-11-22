package org.example.algorithm;

import java.util.Arrays;

public class Leetcode179 {
    public static String largestNumber(int[] nums) {
        String[] numStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numStrs, (x, y)-> (y+x).compareTo(x+y));
        StringBuilder sb = new StringBuilder();
        if(numStrs[0].equals("0")){
            return "0";
        }
        for (String integer : numStrs) {
           sb.append(integer);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] nums;
        nums = new int[]{3,30,34,5,9};
        System.out.println(largestNumber(nums));

        nums = new int[]{0,0,0};
        System.out.println(largestNumber(nums));
    }
}
