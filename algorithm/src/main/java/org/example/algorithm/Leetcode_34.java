package org.example.algorithm;

import java.util.Arrays;

public class Leetcode_34 {
    public static int[] searchRange(int[] nums, int target) {
        //注意边界
        if(nums == null || nums.length == 0){
            return new int[]{-1,-1};
        }
        int leftMostIndex = getMost(nums, target, true);
        if(leftMostIndex >= nums.length || nums[leftMostIndex] != target){
            return new int[]{-1,-1};
        }
        return new int[]{leftMostIndex, getMost(nums, target, false)};
    }

    private static int getMost(int[] nums, int target, boolean isLeftMost){
        // 注意r 不能为nums.length
        int l=0, r=nums.length-1, m;
        while(l <= r){
            m = l + ((r-l)>>1);
            if(target < nums[m]){
                r = m-1;
            }else if(nums[m] < target){
                l = m+1;
            }else if(isLeftMost){
                r = m-1;
            }else{
                l = m+1;
            }
        }
        if(isLeftMost) return r+1;
        else return l-1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange(arr, 8)));
        System.out.println(Arrays.toString(searchRange(arr, 6)));
        System.out.println(Arrays.toString(searchRange(new int[0], 0)));
        System.out.println(Arrays.toString(searchRange(new int[]{1}, 1)));
    }
}
