package org.example.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode1575 {

    public int countRoutes1(int[] locations, int start, int finish, int fuel) {
        List<List<Integer>> list = new ArrayList<>();
        int ans = process1(locations, start, finish, fuel, new ArrayList<>(), list);
        for (List<Integer> subList : list) {
            System.out.println(Arrays.toString(subList.toArray()));
        }
        return ans;
    }

    public int process1(int[] locations, int start, int finish, int fuel, List<Integer> tmpList, List<List<Integer>> list) {
        if (Math.abs(locations[start] - locations[finish]) > fuel) {
            return 0;
        }
        int count = 0;
        if(start == finish) {
            count++;
            count %= (int)Math.pow(10,9) + 7;
            tmpList.add(finish);
            list.add(new ArrayList<>(tmpList));
            tmpList.remove(tmpList.size()-1);
        }

        for(int i=0; i<locations.length; i++){
            int cost = Math.abs(locations[i]-locations[start]);
            if(i == start ||  cost > fuel) continue;
            tmpList.add(start);
            count +=process1(locations, i, finish, fuel-cost, tmpList, list);
            tmpList.remove(tmpList.size()-1);
            count %= (int)Math.pow(10,9) + 7;
        }

        return count;
    }

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int[][] dp = new int[locations.length][fuel+1];
        for(int i=0; i<dp.length; i++) Arrays.fill(dp[i], -1);
        int ans = process(locations, start, finish, fuel, dp);

        return ans;
    }

    public int process(int[] locations, int start, int finish, int fuel, int[][] dp) {
        if(dp[start][fuel] != -1) return dp[start][fuel];
        if (Math.abs(locations[start] - locations[finish]) > fuel) {
            dp[start][fuel] = 0;
            return 0;
        }
        int count = 0;
        if(start == finish) {
            count++;
            count %= (int)Math.pow(10,9) + 7;
        }

        for(int i=0; i<locations.length; i++){
            int cost = Math.abs(locations[i]-locations[start]);
            if(i == start ||  cost > fuel) continue;
            count +=process(locations, i, finish, fuel-cost, dp);
            count %= (int)Math.pow(10,9) + 7;
        }
        dp[start][fuel] = count;
        return count;
    }

    public static void main(String[] args) {
        int[] locations; int start, finish, fuel;

        locations = new int[]{4,3,1};
        start = 1; finish = 0; fuel = 6;
        System.out.println(new Leetcode1575().countRoutes1(locations, start, finish, fuel));
        System.out.println();

    }
}
