package org.example.algorithm.baseKnowledge;

import java.util.*;
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
        if(arr == null || arr.length <= 1){
            return;
        }
        // n*log(n)
//        for (int i = 0; i < arr.length; i++) {
//           heapUp(arr, i);
//        }

        // log(n)
        for (int r = arr.length-1; r >= 0; r--) {
            heapify(arr, r, arr.length);
        }
//        System.out.println(Arrays.toString(arr));

        int heapsize = arr.length;
        swap(arr, 0, --heapsize);
        while(heapsize > 0){
            heapify(arr, 0, heapsize);
            swap(arr, 0, --heapsize);
        }
    }

    private static void heapUp(int[] arr, int index){
        while(arr[index] > arr[(index-1)/2]){
            swap(arr, index, (index-1)/2);
            index = (index-1)/2;
        }
    }

    private static void heapify(int[] arr, int index, int heapSize){
        int left = index * 2 + 1, right = left+1;
        while(left < heapSize){
           int larger = right > heapSize-1 || arr[left] > arr[right] ? left : right;
           if(arr[index] >= arr[larger]){
               break;
           }
           swap(arr, index, larger);

           // TODO 漏掉了
           index = larger;

           left =  larger * 2 +1;
           right = left+1;

        }
    }

    public static void quickSort(int[] arr){
        quickSortLoop(arr, 0, arr.length-1);
    }

    public static void quickSortLoop(int[] arr, int left, int right){
        LinkedList<int[]> stack = new LinkedList<>();
        stack.push(new int[]{left, right});
        while(!stack.isEmpty()){
            int[] ele = stack.pop();
            left = ele[0];
            right = ele[1];
            if(left >= right){
                continue;
            }
            int pivot = left + (int)(Math.random() * (right-left+1));
            int[] equalArea = getEqualArea(arr, left, pivot, right);
            stack.push(new int[]{left, equalArea[0]-1});
            stack.push(new int[]{equalArea[1]+1, right});
        }
    }

    private static void quickSplit(int[] arr, int left, int right){
        if(left >= right){
            return;
        }
        int pivot = left + (int)(Math.random() * (right-left+1));
        int[] equalArea = getEqualArea(arr, left, pivot, right);
//        System.out.printf("left: %d, right: %d, pivot: %d%n",left, right, pivot);
//        System.out.println(Arrays.toString(arr));
        quickSplit(arr, left, equalArea[0]-1);
        quickSplit(arr, equalArea[1] + 1, right);
    }

    private static int[] getEqualArea(int[] arr, int left, int pivot, int right){
        swap(arr, pivot, right);
        int i = left;
        // 左边区域到不了的位置
        // todo
//        int leftIndex = -1;
        int leftIndex = left - 1;
        // 右边区域到不了的位置
        int rightIndex = right;
        while(i<rightIndex){
            if(arr[i] < arr[right]){
                swap(arr, ++leftIndex, i++);
            }else if(arr[i] > arr[right]){
                swap(arr, --rightIndex, i);
            }else{
                i++;
            }
        }
        // todo 如果 [7,1] arr[pivot] = 1 程序到这里 rightIndex = -1 下面一行代码会边界报错
        // swap(arr, --rightIndex, right);
        swap(arr, rightIndex, right);
        return new int[]{leftIndex+1, rightIndex};
    }

    public static void countSort(int[] arr){
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        int[] count = new int[max+1];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        int i = 0, j = 0;
        while(i<arr.length){
            while(count[j] > 0){
                arr[i++] = j;
                count[j]--;
            }
            j++;
        }
    }

    public static void bucketSort(int[] arr){
        if(arr == null || arr.length <= 1){
            return;
        }
        int max = arr[0],min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            min =  Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        int bucketLength = (max-min)/arr.length + 1;
        List<List<Integer>> bucketList = new ArrayList<>(bucketLength);
        for (int i = 0; i < bucketLength; i++) {
            bucketList.add(new ArrayList<>());
        }
        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = (arr[i]-min)/arr.length;
            bucketList.get(bucketIndex).add(arr[i]);
        }

        int index = 0;
        for (int i = 0; i < bucketList.size(); i++) {
            if(!bucketList.get(i).isEmpty()) {
                Collections.sort(bucketList.get(i));
                for (int j = 0; j < bucketList.get(i).size(); j++) {
                    arr[index++] = bucketList.get(i).get(j);
                }
            }
        }
    }

    public static void radixSort(int[] arr){
        if(arr == null || arr.length <= 1){
            return;
        }
        processRadixSort(arr, getMaxBit(arr));
    }

    private static int getMaxBit(int[] arr){
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int maxBit = 0;
        while(max > 0){
            max /= 10;
            maxBit++;
        }
        return maxBit;
    }

    private static void processRadixSort(int[] arr, int maxBit){
        int[] bucket = new int[10], help = new int[arr.length];
        for (int b = 1; b <= maxBit; b++) {
            for (int i = 0; i < arr.length; i++) {
               bucket[getDigit(arr[i], b)]++;
            }
            for (int i = 1; i < bucket.length; i++) {
               bucket[i] += bucket[i-1];
            }
//            System.out.println(Arrays.toString(bucket));
            for (int i = arr.length - 1; i >= 0; i--) {
               help[--bucket[getDigit(arr[i], b)]] = arr[i];
            }
            System.arraycopy(help, 0, arr, 0, help.length);
            initArr(bucket);
            //todo not need init help
        }
    }

    private static void initArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
    }

    private static int getDigit(int x, int bit){
        return x / (int)Math.pow(10, bit-1) % 10;
    }

    public static void main(String[] args) {
        System.out.println((0-1)/2);
        List<Consumer<int[]>> consumerList = Arrays.asList(
//                ints -> merge(ints),
                ints -> quickSort(ints)
        );

        for (Consumer<int[]> consumer : consumerList) {
            test(consumer, getTestData());
        }
    }


    private static void test(Consumer consumer, List<int[]> testDataList){
        for (int[] arr : testDataList) {
            System.out.println("orgin: " + Arrays.toString(arr));
            consumer.accept(arr);
            System.out.println("sorted: " + Arrays.toString(arr));
            System.out.println();
        }
    }

    public static List<int[]> getTestData(){
        return Arrays.asList(
                new int[]{},
                new int[]{9,3,7,2,5,8,1,4},
                new int[]{10,9,3,7,2,5,8,1,4}
        );
    }
}
