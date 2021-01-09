package me.liiot.string_array;

/*
16) Spiral Matrix
m x n 요소의 행렬 (m행, n열)이 주어졌을 때, 행렬의 모든 요소를 나선형 순서로 반환하세요.
 */
public class SpiralMatrix {

    public static void main(String[] args) {

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}, {17, 18, 19, 20}};

        solve(matrix);
        solve(matrix2);
    }

    private static void solve(int[][] matrix) {

        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int colStart = 0;
        int colEnd = matrix[0].length -1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            // Right
            for (int i=colStart; i<=colEnd; i++) {
                System.out.print(matrix[rowStart][i] + " ");
            }
            rowStart++;

            // Down
            for (int i=rowStart; i<=rowEnd; i++) {
                System.out.print(matrix[i][colEnd] + " ");
            }
            colEnd--;

            // Left
            for (int i=colEnd; i>=colStart; i--) {
                System.out.print(matrix[rowEnd][i] + " ");
            }
            rowEnd--;

            // Up
            for (int i=rowEnd; i>=rowStart; i--) {
                System.out.print(matrix[i][colStart] + " ");
            }
            colStart++;
        }
        System.out.println();
    }
}
