package org.example.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode3 {
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        //todo 错的 nums.length-3
        for(int i = 0; i < nums.length-2; i++){
            int j = i+1, k = nums.length -1;
            if(nums[i] > 0 || nums[i] + nums[j] > 0) continue;
            while(j < k){
                if(nums[i] + nums[j] + nums[k] < 0){
                    j++;
                }else if(0 < nums[i] + nums[j] + nums[k]){
                    k--;
                }else{
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // todo 漏了
                    break;
                }
            }
        }
        return res;
    }

    //todo 非重复版本
    public static List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        //todo 错的 nums.length-3
        for(int i = 0; i < nums.length-2; i++){
            int j = i+1, k = nums.length -1;
            if(nums[i] > 0 || nums[i] + nums[j] > 0) continue;
            // TODO 去重
            if(i > 0 && nums[i] == nums[i-1])continue;
            while(j < k){
                if(nums[i] + nums[j] + nums[k] < 0){
                    j++;
                }else if(0 < nums[i] + nums[j] + nums[k]){
                    k--;
                }else{
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // todo 漏了
                    break;
                }
            }
        }
        return res;
    }

    //todo 非重复版本
    public static List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        //todo 错的 nums.length-3
        for(int i = 0; i < nums.length-2; i++){
            int j = i+1, k = nums.length -1;
            // todo 剪支
            if(nums[i] > 0 || nums[i] + nums[j] > 0) continue;
            // TODO 去重
            if(i > 0 && nums[i] == nums[i-1])continue;

            while(j < k){
                if(nums[i] + nums[j] + nums[k] < 0){
                    j++;
                }else if(0 < nums[i] + nums[j] + nums[k]){
                    k--;
                }else{
                    //todo 去重
                    if(j-1 > i && nums[j] == nums[j-1]){
                        j++;
                        continue;
                    }
                    //todo  nums[i++], nums[j--]
                    res.add(Arrays.asList(nums[i], nums[j++], nums[k]));
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSumOrigin(int[] nums) {
        if(nums==null || nums.length < 3) return new ArrayList<>();
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length-2; i++) {
            //todo 剪支 nums[i] > 0 || nums[i] + nums[i+1] > 0
            //nums[i] > 0 那么 nums[i] + nums[i+1] 肯定大于 0
            if(nums[i]+nums[i+1]+nums[i+2] > 0) return res;
            if(nums[i] + nums[nums.length-1] + nums[nums.length-2] < 0) continue;
            //todo 去重
            if(i>0 && nums[i] == nums[i-1]) continue;
            int L = i+1;
            int R = nums.length-1;
            while(L < R) {
                if (nums[i] + nums[L] + nums[R] == 0) {
                    res.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    //todo 去重
                    while (L+1 < R && nums[L] == nums[L + 1]) ++L;
                    while (R-1 > L && nums[R] == nums[R - 1]) --R;
                    ++L;
                    --R;
                } else if (nums[i] + nums[L] + nums[R] > 0) {
                    --R;
                }else{
                    ++L;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums; List<List<Integer>> res;

//        nums = new int[]{-1,0,1,2,-1,-4};
//        res = threeSum(nums);
//        for (List<Integer> re : res) {
//            System.out.println(Arrays.toString(re.toArray()));
//        }

        //todo 错的 nums.length-3
//        nums = new int[]{0,0,0};
//        res = threeSum(nums);
//        for (List<Integer> re : res) {
//            System.out.println(Arrays.toString(re.toArray()));
//        }

        nums = new int[]{0,0,0,0};
        res = threeSum2(nums);
        for (List<Integer> re : res) {
            System.out.println(Arrays.toString(re.toArray()));
        }

//        nums = new int[]{-4,1,2,2,3};
//        res = threeSum2(nums);
//        for (List<Integer> re : res) {
//            System.out.println(Arrays.toString(re.toArray()));
//        }

        System.out.println("最大值是: "+Integer.MAX_VALUE);
        System.out.println("10^9 是: "+(int)Math.pow(10, 9));
    }
}
