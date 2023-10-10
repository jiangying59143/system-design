package org.example.algorithm;

import java.util.Arrays;

public class Leetcode88 {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m-1, q=n-1, rp = m+n-1, x, y, z;
        while(p >=0 || q >= 0){
            x = p >= 0 ? nums1[p] : Integer.MIN_VALUE;
            y = q >= 0 ? nums2[q] : Integer.MIN_VALUE;
            nums1[rp--] = Math.max(x, y);
            if(x > y){
                p--;
            }else{
                q--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1, nums2;
        nums1 = new int[]{1,2,3,0,0,0};
        nums2 = new int[]{2,5,6};
        merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
