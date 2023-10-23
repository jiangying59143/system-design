package org.example.algorithm.dataStructure;

import java.util.Arrays;

public class Array {
    public static void print(int[] ... numArray){
        for (int[] ints : numArray) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static <T> void print(T[][] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }

    public static void print(boolean[][] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }
}
