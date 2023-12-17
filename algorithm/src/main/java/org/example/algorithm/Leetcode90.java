package org.example.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for(int count=0; count<=nums.length; count++){
            boolean[] visited = new boolean[nums.length];
            process(nums, 0, count, new ArrayList<>(), list, visited);
        }
        return list;
    }

    private void process(int[] nums, int index, int count, List<Integer> tmpList, List<List<Integer>> list, boolean[] visited) {
        if (tmpList.size() == count) {
            list.add(new ArrayList<>(tmpList));
            return;
        }
        if (nums.length - index < count - tmpList.size()) return;
        for (int i = index; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) continue;
            visited[i] = true;
            tmpList.add(nums[i]);
            process(nums, i + 1, count, tmpList, list, visited);
            tmpList.remove(tmpList.size() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums;
        nums = new int[]{1,2,2};
        List<List<Integer>> list = new Leetcode90().subsetsWithDup(nums);
        for (List<Integer> subList : list) {
            System.out.print(Arrays.toString(subList.toArray()));
        }
        System.out.println();
    }
}
