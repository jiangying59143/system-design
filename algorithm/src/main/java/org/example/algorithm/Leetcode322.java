package org.example.algorithm;

public class Leetcode322 {
    public int coinChange1(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount+1];
        for(int i=0; i<dp.length; i++){
            for(int j=0; j<dp[i].length; j++){
                dp[i][j] = -1;
            }
        }
        return process(coins, coins.length-1, amount, dp);
    }

    private int process(int[] coins,int i, int amount, int[][] dp){
        if(i < 0 || amount < 0) return -1;

        if(dp[i][amount] != -1) return dp[i][amount];

        if(amount == 0){
            dp[i][amount] = 0;
            return 0;
        }
        int unselected = process(coins, i-1, amount, dp);
        int selectOne = process(coins, i-1, amount - coins[i], dp);
        int selectMultiple = process(coins, i, amount - coins[i], dp);

        if(unselected == -1 && selectOne == -1 && selectMultiple == -1){
            return -1;
        }

        int ans = Math.min(Math.min(unselected == -1 ? Integer.MAX_VALUE : unselected,
                selectOne == -1 ? Integer.MAX_VALUE : 1 + selectOne),
                selectMultiple == -1 ? Integer.MAX_VALUE : 1 + selectMultiple);
        dp[i][amount] = ans;
        return ans;
    }

    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount+1];
        for(int i=0; i<dp.length; i++){
            for(int j=1; j<dp[i].length; j++){
                if( !match(dp, i-1, j) && !match(dp, i-1, j - coins[i]) && !match(dp, i, j - coins[i]) )
                    dp[i][j] = -1;
                else {
                    dp[i][j] = Math.min(Math.min(!match(dp, i-1, j) ? Integer.MAX_VALUE : dp[i-1][j],
                                    !match(dp, i-1, j - coins[i]) ? Integer.MAX_VALUE : 1 + dp[i-1][j-coins[i]]),
                            !match(dp, i, j - coins[i]) ? Integer.MAX_VALUE : 1 + dp[i][j-coins[i]]);
                }

            }
        }
        return dp[coins.length-1][amount];
    }

    private boolean match(int[][] dp, int i, int j){
        if(i < 0 || j < 0 || dp[i][j] == -1) return false;
        return true;
    }


    public static void main(String[] args) {
        int[] coins; int amount;

        coins = new int[]{1,2,5};
        amount = 1;
        System.out.println(new Leetcode322().coinChange(coins, amount));

        amount = 11;
        System.out.println(new Leetcode322().coinChange(coins, amount));

        amount = 4;
        System.out.println(new Leetcode322().coinChange(coins, amount));

        amount = 0;
        System.out.println(new Leetcode322().coinChange(coins, amount));

        coins = new int[]{3};
        amount = 2;
        System.out.println(new Leetcode322().coinChange(coins, amount));
    }
}
