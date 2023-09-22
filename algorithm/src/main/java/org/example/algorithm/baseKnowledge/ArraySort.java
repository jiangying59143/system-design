package org.example.algorithm.baseKnowledge;

import java.util.Arrays;

public class ArraySort {
    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void bubble(int[] arr){
        for (int r = arr.length-1; r >= 0; r--) {
            for (int i = 1; i <= r; i++) {
               if(arr[i-1] > arr[i]){
                   swap(arr, i-1, i);
               }
            }
        }
    }

    public static void selection(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
               if(arr[j] < arr[minIndex]){
                   minIndex = j;
               }
            }
            swap(arr, i, minIndex);
        }
    }

    public static void insertion(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j >= 1 && arr[j-1] > arr[j]; j--) {
                swap(arr, j, j-1);
            }
        }
    }

    public static void shell(int[] arr){
        for (int gap = arr.length>>1; gap > 0; gap >>= 1) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j >= gap && arr[j-gap] > arr[j]; j--) {
                    swap(arr, j, j-gap);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9,3,7,2,5,8,1,4};
        shell(arr);
        System.out.println(Arrays.toString(arr));
    }
}
