package org.example.algorithm;

/**
 * 由1和0组成的平面N*N矩阵，
 * 1代表可通过，0代表不可通过，
 * 现在需要从左下角走到右上角，问最短需要多少步
 */
public class Interview1 {
    public static void main(String[] args) {
        int[][] arr;

        arr = new int[][]{
                {1,1},
                {1,0}
        };

        arr = new int[][]{
                {1,1},
                {1,1}
        };

        arr = new int[][]{
                {0,1},
                {1,1}
        };

        arr = new int[][]{
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };

        arr = new int[][]{
                {1,1,1},
                {1,1,0},
                {1,0,1}
        };

        arr = new int[][]{
                {1,1,1,1,1},
                {0,0,0,0,1},
                {1,1,1,0,1},
                {1,0,1,1,1},
                {1,0,0,0,0},
                {1,1,1,1,1}
        };

        int result = process(arr, 0, 0, 0);
        result = result == Integer.MAX_VALUE ? 0 : result;

        System.out.println(result);

    }

    private static int process(int[][] arr, int x, int y, int step){
        if(x == arr.length-1 && y == arr[x].length-1 && arr[x][y] == 1) return step+1;
        if( x < 0 || y < 0 || x == arr.length || y == arr[x].length || arr[x][y] == 0 || arr[x][y] == 2)return Integer.MAX_VALUE;
        arr[x][y] = 2;
        int left = process(arr, x-1, y, step+1);
        int right = process(arr, x+1, y, step+1);
        int up = process(arr, x, y-1, step +1);
        int down = process(arr, x, y+1, step+1);
        return Math.min(Math.min(left,right), Math.min(up, down));
    }
}
