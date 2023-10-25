package org.example.algorithm;

import org.example.algorithm.dataStructure.Array;

public class Leetcode188 {
    public static int maxProfit(int k, int[] prices) {
        int[][] dp = new int[k][2];
        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < k; j++) {
                dp[j][0] = Math.max(i==0 ? -prices[i] : dp[j][0], j==0 ? -prices[i] : dp[j-1][1]-prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j][0] + prices[i]);
            }
            Array.print(dp);
        }
        return dp[k-1][1];
    }

    public static void main(String[] args) {
        int[] prices;

        prices = new int[]{2,4,1};
        System.out.println(maxProfit(2, prices));
    }
}
