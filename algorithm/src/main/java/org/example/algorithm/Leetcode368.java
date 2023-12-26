package org.example.algorithm;

import java.util.*;

public class Leetcode368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        List<Integer> ans = process(nums, nums.length, new ArrayList<>());
        return ans;
    }

    private List<Integer> process(int[] nums, int i, List<Integer> list){
        if(i == 0){
            return new ArrayList<>(list);
        }
        List<Integer> list1 = process(nums, i-1, list);
        List<Integer> list2 = list;
        if(list.size() == 0 || list.get(list.size()-1) % nums[i-1] == 0){
            list.add(nums[i-1]);
            list2 = process(nums, i-1, list);
            list.remove(list.size()-1);
        }
        List<Integer> ans = new ArrayList<>(list1.size() > list2.size() ? list1 : list2);
        return ans;
    }

    public List<Integer> largestDivisibleSubset2(int[] nums) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        List<Integer> list = new ArrayList<>();
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        process2(nums, nums.length-1, dp);
        int maxLen = 0;
        for (int max : dp) {
            maxLen = Math.max(max, maxLen);
        }
        System.out.println(Arrays.toString(dp));
        if(maxLen == 1){
            list.add(nums[0]);
        }else {
            int end = 0, maxValue = nums[0];
            for (int i = 0; i < nums.length; i++) {
                if(dp[i] == maxLen){
                    end = i;
                    maxValue = nums[i];
                    break;
                }
            }
            for (int i = end; i >= 0 && maxLen > 0; i--) {
                if(maxValue % nums[i]== 0 && maxLen == dp[i]){
                    list.add(nums[i]);
                    maxValue = nums[i];
                    maxLen--;
                }
            }

        }
        return list;
    }

    // 函数 定义为    [i 开头必选 获得的最大个数]
    private int process2(int[] nums, int i, int[] dp){
        if(dp[i] != -1) return dp[i];
        if(i == 0){
            dp[i] = 1;
            return 1;
        }
        int ans = 1;
        for (int j = i-1; j >= 0; j--) {
            if(i > 0 && nums[i] % nums[j] == 0) {
                ans = Math.max(ans, 1 + process2(nums, j, dp));
            }
        }
        process2(nums, i-1, dp);
        dp[i] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int[] nums;
        List<Integer> list;

        nums = new int[]{1,2,3};
        list = new Leetcode368().largestDivisibleSubset2(nums);
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println();

        nums = new int[]{1,2,4,8};
        list = new Leetcode368().largestDivisibleSubset2(nums);
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println();

        nums = new int[]{3,4,16,8};
        list = new Leetcode368().largestDivisibleSubset2(nums);
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println();

        nums = new int[]{4,8,10,240};
        list = new Leetcode368().largestDivisibleSubset2(nums);
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println();

        nums = new int[]{2,3,4,9,8};
        list = new Leetcode368().largestDivisibleSubset2(nums);
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println();
    }
}
