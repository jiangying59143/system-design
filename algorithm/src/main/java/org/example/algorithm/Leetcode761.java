package org.example.algorithm;

public class Leetcode761 {
    static class  MyHashMap {
        private int[] arr;

        public MyHashMap() {
            arr = new int[1000001];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = -1;
            }
        }

        public void put(int key, int value) {
            arr[key] = value;
        }

        public int get(int key) {
            return arr[key];
        }

        public void remove(int key) {
            arr[key] = -1;
        }
    }
}
