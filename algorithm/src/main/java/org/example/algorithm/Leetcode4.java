package org.example.algorithm;

import org.example.algorithm.dataStructure.Array;

public class Leetcode4 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int M = nums1.length, N = nums2.length;
        if(N < M) return findMedianSortedArrays(nums2, nums1);
        //todo R 左侧最大的个数，到得了,
        //leftSumLen 是nums1 num2左边总个数, (M+N+1)>>1 左边比右边多一个 或者 相等
        int L = 0, R = M, m, leftSumLen = (M+N+1)>>1, leftVal1, rightVal1, leftVal2, rightVal2;
        while(L <= R){
            //m 是 nums1 左边的个数
            m = L + ((R-L) >> 1);
            leftVal1 = m == 0 ? Integer.MIN_VALUE : nums1[m-1];
            rightVal1 = m == M ? Integer.MAX_VALUE : nums1[m];
            leftVal2 = leftSumLen-m == 0 ? Integer.MIN_VALUE : nums2[leftSumLen-m-1];
            rightVal2 = leftSumLen-m == N ? Integer.MAX_VALUE : nums2[leftSumLen-m];
            //todo 一定要加等号
            if(leftVal1 <= rightVal2 && rightVal1 >= leftVal2){
                int leftMin = Math.max(leftVal1, leftVal2), rightMax = Math.min(rightVal1, rightVal2);
                // todo (M + N) & 1 == 0    =>   ((M + N) & 1) == 0
                return ((M + N) & 1) == 0 ? (leftMin + rightMax) / 2.0 : leftMin / 1.0;
            }else if(leftVal1 > rightVal2){
                R = m - 1;
            }else{
                L = m + 1;
            }
        }
        // todo 代码不会走到这里;
        return 0.0;
    }

    public static void main(String[] args) {
        int[] nums1, nums2;

        /*nums1 = new int[]{1,3};
        nums2 = new int[]{2};
        Array.print(nums1, nums2);
        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println();

        nums1 = new int[]{1,2};
        nums2 = new int[]{3,4};
        Array.print(nums1, nums2);
        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println();

        nums1 = new int[]{};
        nums2 = new int[]{2};
        Array.print(nums1, nums2);
        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println();*/

        nums1 = new int[]{1};
        nums2 = new int[]{1};
        Array.print(nums1, nums2);
        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println();
    }
}
