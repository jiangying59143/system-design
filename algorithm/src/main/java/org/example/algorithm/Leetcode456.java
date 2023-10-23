package org.example.algorithm;

import java.util.*;

public class Leetcode456 {
    //1. 暴力方法  O(n^3)  O(1)
    public static boolean find132pattern(int[] nums) {
        if(nums == null || nums.length < 3){
            return false;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length-1; i++) {
            min = Math.min(min, nums[i]);
            for(int j = i+1; j < nums.length; j++){
                if(min < nums[j]){
                    for (int k = j+1; k < nums.length; k++) {
                       if(min < nums[k] && nums[k] < nums[j]){
                           return true;
                       }
                    }
                }
            }
        }
        return false;
    }

    //2. 暴力方法改进  O(n^2)  O(1)
    public static boolean find132pattern2(int[] nums) {
        if(nums == null || nums.length < 3){
            return false;
        }
        int min = nums[0];
        for(int j = 1; j < nums.length; j++){
            min = Math.min(min, nums[j]);
            if(min < nums[j]){
                for (int k = j+1; k < nums.length; k++) {
                    if(min < nums[k] && nums[k] < nums[j]){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //3. 方法2 -> 有序表红黑树  O(n*log(n)) O(n)
    public static boolean find132pattern3(int[] nums) {
        if(nums == null || nums.length < 3){
            return false;
        }
        int min = nums[0];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int k = 2; k < nums.length; k++) {
            treeMap.put(nums[k], treeMap.getOrDefault(nums[k], 0) + 1);
        }
        for(int j = 1; j < nums.length; j++){
            min = Math.min(min, nums[j]);
            if(min < nums[j]){
                Integer numsK = treeMap.ceilingKey(min+1);
                if(numsK < nums[j])return true;
            }
            treeMap.put(nums[j], treeMap.getOrDefault(nums[j], 1) - 1);
            if(treeMap.get(nums[j]) == 0){
                treeMap.remove(nums[j]);
            }
        }
        return false;
    }

    //4. 单调栈 + 数组 从前往后
    public static boolean find132pattern4(int[] nums) {
        if(nums == null || nums.length < 3){
            return false;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        List<Integer> minList = new ArrayList<>();
        stack.push(0);
        minList.add(nums[0]);
        for (int k = 1; k < nums.length; k++) {
            while(!stack.isEmpty() && nums[stack.peek()] <= nums[k]){
                 stack.pop();
            }
            if(!stack.isEmpty() && minList.get(stack.peek()) < nums[k]){
                return true;
            }
            stack.push(k);
            minList.add(Math.min(minList.get(k-1), nums[k]));
        }
        return false;
    }

    //5. 单调栈 从后往前
    public static boolean find132pattern5(int[] nums) {
        if(nums == null || nums.length < 3){
            return false;
        }
        int[] stack = new int[nums.length];
        int lastStackIndex = -1, maxK = Integer.MIN_VALUE;
        stack[++lastStackIndex] = nums.length-1;
        for (int i = 0; i < nums.length-1; i++) {
            if(nums[i] < maxK){
                return true;
            }
            while(lastStackIndex != -1 && nums[i] > nums[stack[lastStackIndex]]){
                maxK = stack[lastStackIndex--];
            }
            stack[++lastStackIndex] = i;
        }
        return false;
    }

    private static void printFind132pattern(int[] nums){
        System.out.println(
                "1." + find132pattern(nums)
                + " 2." + find132pattern2(nums)
                + " 3." + find132pattern3(nums)
                + " 4." + find132pattern4(nums)
                + " 5." + find132pattern5(nums)
        );
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums;
        nums = new int[]{1,2,3,4};
        System.out.println(Arrays.toString(nums));
        printFind132pattern(nums);

        nums = new int[]{3,1,4,2};
        System.out.println(Arrays.toString(nums));
        printFind132pattern(nums);
        // 重复值测单调栈
        nums = new int[]{3,1,4,4,2};
        System.out.println(Arrays.toString(nums));
        printFind132pattern(nums);

        nums = new int[]{3,1,4,0,2};
        System.out.println(Arrays.toString(nums));
        printFind132pattern(nums);

        nums = new int[]{-1,3,2,0};
        System.out.println(Arrays.toString(nums));
        printFind132pattern(nums);
    }
}
