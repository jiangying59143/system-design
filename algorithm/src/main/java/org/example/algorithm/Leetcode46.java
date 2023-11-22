package org.example.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode46 {
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        List<List<Integer>> res = new ArrayList<>();
        process(list, res, 0);
        return res;
    }

    private void process(List<Integer> list, List<List<Integer>> res, int pos){
        if(pos == list.size()){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i=pos; i < list.size(); i++){
            swap(list, pos, i);
            process(list, res, pos+1);
            swap(list, pos, i);
        }
    }

    private void swap(List<Integer> list, int i, int j){
        if(i == j) return;
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static void main(String[] args) {
        int[] nums; List<List<Integer>> res;

        nums = new int[]{1,2,3,4,5};
        res = new Leetcode46().permute(nums);
        for (List<Integer> re : res) {
            System.out.println(Arrays.toString(re.toArray()));
        }
        System.out.println();
    }
}
