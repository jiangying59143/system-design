package org.example.algorithm;

public class LCR121 {
    public static boolean findTargetIn2DPlants(int[][] plants, int target) {
        if(plants == null || plants.length ==0 ){
            return false;
        }
        int i = 0, j = plants[0].length-1;
        while(i < plants.length && j >= 0){
            if(target < plants[i][j]){
                j--;
            }else if(plants[i][j] < target){
                i++;
            }else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] plants = new int[][]{{-1, 3}};
        System.out.println(findTargetIn2DPlants(plants, -1));
    }
}
