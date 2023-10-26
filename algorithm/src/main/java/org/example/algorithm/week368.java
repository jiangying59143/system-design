package org.example.algorithm;

public class week368 {
    /**
     * 给你一个下标从0开始的整数数组 nums
     * 如果下标三元组(i，j，k) 满足下述全部条件，则认为它是一个山形三元组
     * i<j<k
     * nums[i] < nums[j] 目 nums[k] < nums[j]
     * 请你找出 nums 中元素和最小的山形三元组，并返回其 元素和如果不存在满足条件的三元组，返回 -1 。
     */
    public static int minimumSum(int[] nums){
        if(nums == null || nums.length < 3){
            return -1;
        }
        // 右边最小值数组
        int[] rightMin = new int[nums.length];
        rightMin[nums.length-1] = nums[nums.length-1];
        for (int i = nums.length-2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i+1], nums[i]);
        }
        int leftMin = nums[0], min = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length-1; i++) {
            if(nums[i] > leftMin && nums[i] > rightMin[i+1]){
                min = Math.min(min, leftMin + nums[i] + rightMin[i+1]);
            }
            leftMin = Math.min(leftMin, nums[i]);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) {
        int[] nums;
        nums = new int[]{8,6,1,5,3};
        System.out.println(minimumSum(nums));
    }
}
