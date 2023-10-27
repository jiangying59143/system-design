package org.example.algorithm;

import java.util.HashMap;
import java.util.Map;

public class Leetcode560 {
    public static int subarraySum(int[] nums, int k) {
        for(int i=1; i<nums.length; i++){
            nums[i]+= nums[i-1];
        }

        Map<Integer, Integer> sumCount = new HashMap<>();
        // todo 默认-1位置为0；
        sumCount.put(k,1);
        int count = 0;
        for(int i=0; i<nums.length; i++){
            if(sumCount.containsKey(nums[i])){
                count += sumCount.get(nums[i]);
            }
            sumCount.put(k+nums[i], sumCount.getOrDefault(k+nums[i], 0)+1);
        }
        return count;

    }

    public static void main(String[] args) {
        int nums[], k;
        nums = new int[]{1,1,1};
        k = 2;
        System.out.println(subarraySum(nums, k));
    }
}
