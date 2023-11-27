package org.example.algorithm;

public class Leetcode213 {
    public int rob(int[] nums) {
        int[][] dp = new int[2][nums.length];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process(nums, nums.length-1, false, dp);
    }

    private int process(int[] nums, int index, boolean flag, int[][] dp){
        if(dp[flag ? 1: 0][index] != -1) return  dp[flag ? 1: 0][index];
        if(index == 0){
            if(flag) {
                dp[1][index] = 0;
                return dp[1][index];
            }
            dp[0][index] = nums[0];
            return nums[0];
        }
        if(index == 1) {
            if(flag){
                dp[1][index] = nums[1];
                return nums[1];
            }
            dp[0][index] = Math.max(nums[0], nums[1]);
            return dp[0][index];
        }
        //不偷提前房间就是 index -1 的子问题
        int x = process(nums, index - 1, index == nums.length-1 ? false : flag, dp);
        // 偷当前房间 就是 index - 2 的子问题
        int y = nums[index] + process(nums, index -2, index == nums.length-1 ? true : flag, dp);
        dp[flag ? 1:0][index] = Math.max(x, y);
        return Math.max(x, y);
    }

    public static void main(String[] args) {
        int[] nums;

        nums = new int[]{1,2,3,1};
        System.out.println(new Leetcode213().rob(nums));

        nums = new int[]{1,2,3};
        System.out.println(new Leetcode213().rob(nums));

        nums = new int[]{2,3,2};
        System.out.println(new Leetcode213().rob(nums));
    }
}
