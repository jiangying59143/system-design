package org.example.algorithm.baseKnowledge;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

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

    public static void merge(int[] arr){
        int left = 0, right = arr.length-1;
        int[] help = new int[arr.length];
        split(arr, left, right, help);
    }

    private static void split(int[] arr, int left, int right, int[] help){
        if(left >= right){
            return;
        }
        int mid = left + ((right-left)>>1);
        split(arr, left, mid, help);
        split(arr, mid+1, right, help);
        doMerge(arr, left, mid, right, help);
    }

    private static void doMerge(int[] arr, int left, int mid, int right, int[] help){
        int i = left, li = left, ri = mid+1;
        while(li <= mid && ri <=right){
            help[i++] = arr[li] < arr[ri] ? arr[li++] : arr[ri++];
        }
        while(li <= mid){
            help[i++] = arr[li++];
        }
        while(ri <=right){
            help[i++] = arr[ri++];
        }

        for(int j = left; j <= right; j++){
            arr[j] = help[j];
        }
    }

    public static void mergeDownUp(int[] arr){
        int right,mid;
        int[] help = new int[arr.length];
        for(int width = 1; width < arr.length; width= width*2){
            for(int left = 0; left < arr.length; left += 2*width){
                right = Math.min(left + 2*width-1, arr.length-1);
//                System.out.printf("split arr :[%d, %d]%n", left, right);
                mid = Math.min(left + width - 1, right);
//                System.out.printf("left: %d, right: %d, width: %d, mid : %d  %d %n",left, right, width,  mid, (left + ((right-left)>>1)));
                doMerge(arr, left, mid, right, help);
            }
        }
    }

    public static void heap(int[] arr){
    }

    public static void main(String[] args) {
        System.out.println((0-1)/2);
        List<Consumer<int[]>> consumerList = Arrays.asList(
//                ints -> merge(ints),
                ints -> mergeDownUp(ints)
        );

        for (Consumer<int[]> consumer : consumerList) {
            test(consumer);
        }
    }


    public static void test(Consumer consumer){
        System.out.printf("sort %s start\n", consumer.getClass().getSimpleName());
        int[] arr = null;
//        consumer.accept(arr);
//        System.out.println(Arrays.toString(arr));
        arr = new int[]{};
        consumer.accept(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{9,3,7,2,5,8,1,4};
        consumer.accept(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{10,9,3,7,2,5,8,1,4};
        consumer.accept(arr);
        System.out.println(Arrays.toString(arr));
        System.out.printf("sort %s end\n", consumer.getClass().getSimpleName());
    }
}
