package org.example.algorithm;

import java.util.Arrays;

public class Leetcode135 {
    public int candy(int[] ratings) {
        int ans = Integer.MAX_VALUE;
        int[][] dp = new int[ratings.length][2*ratings.length+1];
        for(int j=1;j<=ratings.length; j++){
            ans=Math.min(ans,  process(ratings, 0, j, dp));
        }
        return ans;
    }

    private int process(int[] ratings, int index, int candies, int[][] dp){
        if(dp[index][candies] != 0) return dp[index][candies];
        if(index  ==  ratings.length-1){
            dp[index][candies] = candies == 0 ? Integer.MAX_VALUE : candies;
            return dp[index][candies];
        }else if(candies == 0){
            dp[index][candies] = Integer.MAX_VALUE;
            return Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        if(ratings[index]>ratings[index+1]) {
            ans = process(ratings, index+1, candies-1, dp);
        }else if(ratings[index]<ratings[index+1]){
            ans = process(ratings, index+1, candies+1, dp);
        }else{
            ans = process(ratings, index+1, candies, dp);
            for (int i = candies-1; i > 0; i--) {
                ans =  Math.min(ans, process(ratings,  index+1, i,  dp));
            }
        }
        ans  = ans==Integer.MAX_VALUE ? Integer.MAX_VALUE : ans+candies;
        dp[index][candies]=ans;
        return ans;

    }

    public static void main(String[] args) {
        int[] ratings;
        ratings = new int[]{1,0,2};
        System.out.println(new Leetcode135().candy(ratings));

        ratings = new int[]{1,2,2};
        System.out.println(new Leetcode135().candy(ratings));

        ratings = new int[]{1,2,87,87,87,2,1};
        System.out.println(new Leetcode135().candy(ratings));
    }
}
