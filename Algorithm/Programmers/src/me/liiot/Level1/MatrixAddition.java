package me.liiot.Level1;

/*
행렬의 덧셈
 */
public class MatrixAddition {

    public static void main(String[] args) {

        int[][] arr1 = {{1, 2}, {2, 3}};
        int[][] arr2 = {{3, 4}, {5, 6}};
        int[][] result = solution(arr1, arr2);
        for (int[] i : result) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static int[][] solution(int[][] arr1, int[][] arr2) {

        int row = arr1.length;
        int col = arr1[0].length;
        int[][] result = new int[row][col];

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                result[i][j] = arr1[i][j] + arr2[i][j];
            }
        }

        return result;
    }
}
