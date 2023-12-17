package org.example.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        process1(n, 1, k, new ArrayList<>(), list);
        return list;
    }

    private void process(int n, int index, int k, List<Integer> tmpList, List<List<Integer>> list){
        if(tmpList.size() == k){
            list.add(new ArrayList<>(tmpList));
            return;
        }
        if(n - (index - 1) < k - tmpList.size()) return;
        for(int i=index; i<=n; i++){
            tmpList.add(i);
            process(n, i+1, k, tmpList, list);
            tmpList.remove(tmpList.size()-1);
        }
    }

    private void process1(int n, int index, int k, List<Integer> tmpList, List<List<Integer>> list){
        if(tmpList.size() == k){
            list.add(new ArrayList<>(tmpList));
            return;
        }
        if(n - (index - 1) < k - tmpList.size()) return;

        tmpList.add(index);
        process1(n, index+1, k, tmpList, list);
        tmpList.remove(tmpList.size()-1);

        process1(n, index+1, k, tmpList, list);
    }

    public static void main(String[] args) {
        List<List<Integer>> list;

        list = new Leetcode77().combine(4,2);
        for (List<Integer> subList : list) {
            System.out.println(Arrays.toString(subList.toArray()));
        }
    }
}
