package org.example.algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * 假设你有一个长度为 n 的数组，初始情况下所有的数字均为 0，你将会被给出 k 个更新的操作。
 *
 * 其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，你需要将子数组 A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加 inc。
 *
 * 请你返回 k 次操作后的数组。
 *
 * 输入: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * 输出: [-2,0,3,5,3]
 *
 * 初始状态:
 * [0,0,0,0,0]
 *
 * 解释
 * 进行了操作 [1,3,2] 后的状态:
 * [0,2,2,2,0]
 *
 * 进行了操作 [2,4,3] 后的状态:
 * [0,2,5,5,3]
 *
 * 进行了操作 [0,2,-2] 后的状态:
 * [-2,0,3,5,3]
 */
public class Leetcode307 {
    public static int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];
        for (int k = 0; k < updates.length; k++) {
            for (int j = updates[k][0]; j <= updates[k][1]; j++) {
                ans[j] += updates[k][2];
            }
        }
        return ans;
    }

    public static int[] getModifiedArray1(int length, int[][] updates) {
        int[] ans = new int[length];
        for (int k = 0; k < updates.length; k++) {
            ans[updates[k][0]] += updates[k][2];
            if(updates[k][1] < length-1){
                ans[updates[k][1] + 1] -= updates[k][2];
            }
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += ans[i];
            ans[i] = sum;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] updates; int length;

        length = 5;
        updates = new int[][]{{1,3,2},{2,4,3},{0,2,-2}};
        System.out.println(Arrays.toString(getModifiedArray(length, updates)));
        System.out.println(Arrays.toString(getModifiedArray1(length, updates)));
    }
}
