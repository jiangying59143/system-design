package org.example.algorithm;

public class Leetcode7 {
    public static int reverse(int x) {
        int ans = 0;
        while(x !=0 ){
            if(ans > Integer.MAX_VALUE / 10 || ans < Integer.MIN_VALUE / 10){
                return 0;
            }
            int mod = x % 10;
            ans = ans*10 + mod;
            x = x / 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(reverse(Integer.MIN_VALUE));
        System.out.println(reverse(Integer.MAX_VALUE));
    }
}
