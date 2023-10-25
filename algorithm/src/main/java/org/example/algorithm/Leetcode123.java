package org.example.algorithm;

public class Leetcode123 {
    public static int maxProfit(int[] prices) {
        int ans = 0;
        for(int i=0; i<prices.length; i++){
            ans = Math.max(ans, getMaxProfix(prices, 0, i)+getMaxProfix(prices, i, prices.length-1));
        }
        return ans;
    }

    private static int getMaxProfix(int[] prices, int s, int e){
        int min = prices[s], ans = 0;
        for (int i = s; i <= e; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }

    public static int maxProfitOfficial(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
            System.out.println(i + " |" + buy1 + " " + sell1 + " "+ buy2 + " "+ sell2 + " ");
        }
        return sell2;
    }

    public static int maxProfitOfficialComments(int[] prices) {
        int n = prices.length;
        int[] dp1 = new int[n],dp2 = new int[n];
        // 从前往后计算前一段的最大利益
        int minVal = prices[0];
        for(int i=1;i<n;i++){
            dp1[i] = Math.max(dp1[i-1],prices[i]-minVal);
            minVal = Math.min(minVal,prices[i]);
        }
        // 从后往前计算后一段的最大利益
        int maxVal = prices[n-1];
        for(int i=n-2;i>=0;i--){
            dp2[i] = Math.max(dp2[i+1],maxVal-prices[i]);
            maxVal = Math.max(maxVal,prices[i]);
        }

        //两段相加
        int res = 0;
        for(int i=0;i<n;i++){
            res = Math.max(res,dp1[i]+dp2[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] prices;

        prices = new int[]{3,3,5,0,0,3,1,4};
        System.out.println(maxProfitOfficial(prices));
    }
}
