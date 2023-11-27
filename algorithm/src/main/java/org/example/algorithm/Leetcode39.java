package org.example.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
          Arrays.sort(candidates);
          List<List<Integer>> res = new ArrayList<>();
          process(candidates, target, res, new ArrayList<>(), 0);
          return res;
    }

    private static void process(int[] candidates, int target,List<List<Integer>> res, List<Integer> list, int index){
        if(target <= 0){
            if(target == 0) res.add(new ArrayList<>(list));
            return;
        }
        for(int i = index; i < candidates.length; i++){
            if(target - candidates[i] < 0) break;
            list.add(candidates[i]);
            process(candidates, target-candidates[i], res, list, i);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] candidates; int target; List<List<Integer>> res;

        candidates = new int[]{2,3,6,7};
        target = 7;
        res = new Leetcode39().combinationSum(candidates, target);
        for (List<Integer> re : res) {
            System.out.println(Arrays.toString(re.toArray()));
        }

    }
}
