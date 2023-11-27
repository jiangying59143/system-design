package org.example.algorithm;

import java.util.Arrays;

public class Leetcode62 {
    public int uniquePaths(int m, int n) {
        if(m < n) uniquePaths(n, m);
        int[] path = new int[n];
        for (int j = 0; j < n; j++) {
            path[j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                path[j] = (j == 0 ? 0 : path[j-1]) + path[j];
            }
        }

        return path[path.length-1];
    }

    public int uniquePaths1(int m, int n) {
//        return process(m-1, n-1, 0, 0);
        int[][] path = new int[m][n];
        int res = process1(m-1, n-1, path);
//        for (int i = 0; i < path.length; i++) {
//            System.out.println(Arrays.toString(path[i]));
//        }
        return res;
    }

    private int process(int m, int n, int i, int j){
        if(i > m || j > n) return 0;
        if(i == m && j == n) return 1;
        int x = process(m, n, i+1, j);
        int y = process(m, n, i, j+1);
        return x+y;
    }

    private int process1(int i, int j, int[][] path){
        if(i < 0 || j < 0) {
            return 0;
        }
        if(i == 0 && j == 0) {
            path[i][j] = 1;
            return 1;
        }
        int x = process1(i-1, j, path);
        int y = process1(i, j-1, path);
        path[i][j] = x + y;
        return path[i][j];
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode62().uniquePaths1(3, 7) + " " + new Leetcode62().uniquePaths(3, 7));

        System.out.println(new Leetcode62().uniquePaths1(3, 2) + " " + new Leetcode62().uniquePaths(3, 2));

        System.out.println(new Leetcode62().uniquePaths1(3, 3) + " " + new Leetcode62().uniquePaths(3, 3));
    }
}
