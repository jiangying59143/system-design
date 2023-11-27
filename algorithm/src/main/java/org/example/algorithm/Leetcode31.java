package org.example.algorithm;

import java.util.Arrays;

public class Leetcode31 {
    public void nextPermutation(int[] nums) {
        int biggestPos = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if(nums[i-1] < nums[i]){
                biggestPos = i;
                // find the nearest big pos
                for (int j = nums.length - 1; j >= i; j--) {
                    if(nums[j] > nums[i-1]){
                        swap(nums, i-1, j);
                        break;
                    }
                }
                break;
            }
        }
        reverse(nums, biggestPos);
    }

    private void reverse(int[] nums, int L){
        int R = nums.length-1;
        while(L < R) swap(nums, L++, R--);
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums;

        nums = new int[]{1,2,3};
        new Leetcode31().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{3,2,1};
        new Leetcode31().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1,1,5};
        new Leetcode31().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
