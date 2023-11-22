package org.example.algorithm;

import java.util.HashMap;
import java.util.Map;

public class leetcode1590 {
    public int minSubarray(int[] nums, int p) {
        int x = 0;
        for (int num : nums) {
            x = (x+num)%p;
        }
        if(x == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE, y = 0;
        for (int i = 0; i < nums.length; i++) {
            map.put(y, i); // 记录的是前一个前缀和的index, 所以后面的ans 要 +1
            y = (y + nums[i]) % p;
            if(map.containsKey((y-x + p) % p)) ans = Math.min(ans, i - map.get((y-x + p) % p) + 1);
        }
        return ans == nums.length ? -1 : ans;
    }

    public static void main(String[] args) {
        System.out.println(10 % -6);
    }
}
