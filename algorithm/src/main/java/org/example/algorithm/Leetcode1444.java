package org.example.algorithm;

import java.util.Arrays;

public class Leetcode1444 {
    private final int MOD = (int)Math.pow(10, 9) + 7;
    public int ways(String[] pizza, int k) {
        int[][] nums = new int[pizza.length][pizza[0].length()];
        int[][][] dp = new int[nums.length][nums[0].length][k+1];
        for(int i=0; i<pizza.length; i++){
            for(int j=0; j<pizza[i].length(); j++){
                if(pizza[i].charAt(j) == 'A') nums[i][j] = 1;
                Arrays.fill(dp[i][j], -1);
            }
        }

        // 后缀和
        for(int i = nums.length-1; i >= 0; i--){
            for(int j = nums[i].length-1; j>= 0; j--){
                nums[i][j] +=
                        (i < nums.length-1 ? nums[i+1][j] : 0 ) +
                                (j < nums[i].length-1 ? nums[i][j+1] : 0) -
                                (i < nums.length-1 && j < nums[i].length-1 ? nums[i+1][j+1] : 0);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.println(Arrays.toString(nums[i]));
        }
        System.out.println();

        process(nums, 0, 0, k, dp);
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.println(Arrays.toString(dp[i][j]));
            }
            System.out.println();
        }
        return dp[0][0][k];
    }

    private int process(int[][] nums, int i, int j, int k, int[][][] dp){
        if(dp[i][j][k] != -1) return dp[i][j][k];
        if(k == 1){
            dp[i][j][k] = nums[i][j] > 0 ? 1 : 0;
            return dp[i][j][k];
        }
        int x = 0;
        for(int r = i+1; r < nums.length; r++){
            if(getCutAppleCount(nums, i, r, j, -1) == 0) continue;
            x += process(nums, r, j, k-1, dp);
            x %= MOD;
        }
        for(int c = j+1; c < nums[i].length; c++){
            if(getCutAppleCount(nums, i, -1, j, c) == 0){
                continue;
            }
            x += process(nums, i, c, k-1, dp);
            x %= MOD;
        }
        dp[i][j][k] = x % MOD;
        return dp[i][j][k];
    }

    private int getCutAppleCount(int[][] nums, int i, int r, int j, int c){
        if(c == -1) return nums[i][j] - nums[r][j];
        else return nums[i][j] - nums[i][c];
    }

    private void test(){
        int[][] nums = new int[][]{{4,2,1},{3,2,1},{0,0,0}};
        System.out.println(getCutAppleCount(nums, 1,-1, 0, 2));
    }

    public static void main(String[] args) {
        String[] pizza; int k;

        pizza = new String[]{"A..","AAA","..."};
        k = 3;
        System.out.println(new Leetcode1444().ways(pizza, k));
        new Leetcode1444().test();
    }
}
