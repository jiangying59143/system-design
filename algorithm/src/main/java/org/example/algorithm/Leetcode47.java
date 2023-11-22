package org.example.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        List<List<Integer>> res = new ArrayList<>();
        process(nums, res, new ArrayList<>(), 0, visited);
        return res;
    }

    private void process(int[] nums, List<List<Integer>> res,List<Integer> list, int pos, boolean[] visited){
        if(pos == nums.length){
            res.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; i++) {
            if(visited[i]) continue;
            if(i > 0 && nums[i-1] == nums[i] && !visited[i-1]) continue;
            list.add(nums[i]);
            visited[i] = true;
            process(nums, res, list, pos+1, visited);
            visited[i] = false;
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums;List<List<Integer>> res;

        nums = new int[]{1,1,2};
        res = new Leetcode47().permuteUnique(nums);
        for (List<Integer> re : res) {
            System.out.print(Arrays.toString(re.toArray()));
        }
        System.out.println();
    }
}
