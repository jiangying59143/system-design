package org.example.algorithm.baseKnowledge;

public class BinarySearch {
    //第一版
    public static int search1(int[] nums, int target){
        int L = 0, R = nums.length - 1, m;
        while(L <= R){
            /** m = (L+R)/2
             *  m = L+(R-L)/2
             *  m = L+((R-L)>>1)
             */
            m = (L+R)>>>1;
            if(target < nums[m]){
                R = m-1;
            }else if(nums[m] < target){
                L = m+1;
            }else{
                return m;
            }
        }
        return -1;
    }

    //第二版 出循环需要验证边界版本
    public static int search2(int[] nums, int target){
        int L = 0, R = nums.length - 1, m;
        //不同于第一版 L <= R
        while(L < R){
            m = (L+R)>>>1;
            if(target < nums[m]){
                R = m-1;
            }else if(nums[m] < target){
                L = m+1;
            }else{
                return m;
            }
        }
        //不同于第一版
        return nums[L] == target ? L : -1;
    }

    //第三版
    /**
     * R 是移动不到的位置
     */
    public static int search3(int[] nums, int target){
        int L = 0, R = nums.length, m;
        while(L < R){
            m = (L+R)>>>1;
            if(target < nums[m]){
                R = m;
            }else if(nums[m] < target){
                L = m+1;
            }else{
                return m;
            }
        }
        return -1;
    }

    //第四版
    /**
     * 获取最左位置或者最右位置
     * @param nums
     * @param target
     * @param isLeftMost
     * @return
     */
    public static int search4(int[] nums, int target, boolean isLeftMost){
        int L = 0, R = nums.length - 1, m, ans = -1;
        while(L <= R){
            /** m = (L+R)/2
             *  m = L+(R-L)/2
             *  m = L+((R-L)>>1)
             */
            m = (L+R)>>>1;
            if(target < nums[m]){
                R = m-1;
            }else if(nums[m] < target){
                L = m+1;
            }else{
                if(isLeftMost){
                    R = m-1;
                }else{
                    L = m+1;
                }
                ans = m;
            }
        }
        return ans;
    }

}
