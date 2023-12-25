package org.example.algorithm;

import java.util.Arrays;
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
            System.out.println(nums[i] + " y=" + y + " x=" + x + " " + ((y-x)%p) + " " + ((y-x+p)%p));
            if(map.containsKey((y-x + p) % p)) ans = Math.min(ans, i - map.get((y-x + p) % p) + 1);
        }
        return ans == nums.length ? -1 : ans;
    }

    public int minSubarray1(int[] nums, int p){
        int x = 0;
        for (int num : nums) {
            x = (x+num)%p;
        }
        if(x == 0) return 0;
        long[] myNums = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            myNums[i] = nums[i];
        }
        long sum = 0;
        int ans = Integer.MAX_VALUE;
        Map<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(sum, i-1);
            sum = sum + myNums[i];
            if(map.containsKey((sum % p - x)%p)) {
                ans = Math.min(ans, i-map.get((sum % p - x)%p));
                System.out.println(i+ " " + ans);
            }
        }
        return ans == nums.length ? -1 : ans;
    }

    public static void main(String[] args) {
        int[] nums;
        nums = new int[]{3,1,4,2};
        System.out.println(Arrays.toString(nums));
        System.out.println(new leetcode1590().minSubarray1(nums, 6));
//        System.out.println(-1 % 6);
    }
}
