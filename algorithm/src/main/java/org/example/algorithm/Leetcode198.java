package org.example.algorithm;

public class Leetcode198 {
    public int rob(int[] nums) {
//        return process(nums, nums.length-1);
        if(nums == null || nums.length == 0){
            return 0;
        }
        int x = nums[0];
        if(nums.length == 1) return x;
        int y = Math.max(nums[0], nums[1]), z = 0;
        if(nums.length == 2) return y;
        for (int i = 2; i < nums.length; i++) {
            z = Math.max(y, nums[i] + x);
            x = y;
            y = z;
        }

        return z;
    }

    private int process(int[] nums, int index){
        if(index == 0) return nums[0];
        if(index == 1) return Math.max(nums[0], nums[1]);
        //不偷提前房间就是 index -1 的子问题
        int x = process(nums, index - 1);
        // 偷当前房间 就是 index - 2 的子问题
        int y = nums[index] + process(nums, index -2);

        return Math.max(x, y);
    }

    public static void main(String[] args) {
         int[] nums;
         nums = new int[]{1,2,3,1};

        System.out.println(new Leetcode198().rob(nums));
    }
}
