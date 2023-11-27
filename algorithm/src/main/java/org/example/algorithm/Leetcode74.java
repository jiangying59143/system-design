package org.example.algorithm;

public class Leetcode74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix[0].length;
        int i = 0, j = matrix.length * matrix[0].length - 1, mid;
        while(i <= j){
            mid = (i + j)>>>1;
            if(matrix[mid/n][mid%n] == target){
                return true;
            }else if(target < matrix[mid/n][mid%n]){
                j = mid - 1;
            }else{
                i = mid + 1;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[][] matrix; int target;
        matrix = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        target = 3;
        System.out.println(new Leetcode74().searchMatrix(matrix, target));

        target = 13;
        System.out.println(new Leetcode74().searchMatrix(matrix, target));
    }
}
