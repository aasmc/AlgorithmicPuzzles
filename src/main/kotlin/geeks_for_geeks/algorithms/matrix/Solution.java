package geeks_for_geeks.algorithms.matrix;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    static int[][] sumMatrix(int A[][], int B[][]) {
        // code here
        if (A.length != B.length) {
            return new int[0][0];
        }
        int[][] result = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i].length != B[i].length) {
                    return new int[0][0];
                }
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    /**
     * Given a square matrix of size N*N, print the sum of upper and lower triangular elements.
     * Upper Triangle consists of elements on the diagonal and above it. The lower triangle
     * consists of elements on the diagonal and below it.
     * <p>
     * Example:
     * The given matrix is
     * 6 5 4
     * 1 2 5
     * 7 9 7
     * The elements of upper triangle are
     * 6 5 4
     * 2 5
     * 7
     * Sum of these elements is 6+5+4+2+5+7=29
     * The elements of lower triangle are
     * 6
     * 1 2
     * 7 9 7
     * Sum of these elements is 6+1+2+7+9+7= 32.
     */
    static ArrayList<Integer> sumTriangles(int matrix[][], int n) {
        // code here
        int upperSum = 0;
        int lowerSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > i) {
                    upperSum += matrix[i][j];
                } else if (j < i) {
                    lowerSum += matrix[i][j];
                } else {
                    upperSum += matrix[i][j];
                    lowerSum += matrix[i][j];
                }
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        res.add(upperSum);
        res.add(lowerSum);
        return res;
    }

    //Function to multiply two matrices.
    static int[][] multiplyMatrix(int A[][], int B[][])
    {
        int[][] res = new int[A.length][B[0].length];
        if (A[0].length != B.length) {
            return new int[0][0];
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    res[i][j] += (A[i][k] * B[k][j]);
                }
            }
        }
        return res;
    }

    static ArrayList<Integer> snakePattern(int matrix[][])
    {
        // code here
        ArrayList<Integer> result = new ArrayList<>(matrix.length * matrix[0].length);
        for (int i = 0; i < matrix.length; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    result.add(matrix[i][j]);
                }
            } else {
                for (int j = matrix.length - 1; j >= 0 ; j--) {
                    result.add(matrix[i][j]);
                }
            }
        }
        return result;
    }

    /**
     * Write a program to find the transpose of a square matrix of size N*N.
     * Transpose of a matrix is obtained by changing rows to columns and columns to rows.
     */
    static void transpose(int matrix[][], int n)
    {
        // code here
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[0].length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    //Function to rotate matrix anticlockwise by 90 degrees.
    static void rotateby90(int matrix[][], int n)
    {
        // code here
        transpose(matrix, n);
        for (int i = 0; i < n / 2; i++) {
            int[] tmp = matrix[n - i - 1];
            matrix[n - i - 1] = matrix[i];
            matrix[i] = tmp;
        }
    }

    /**
     * Given a square matrix of size N x N. The task is to find the determinant of this matrix.
     *
     * Example 1:
     *      Input:
     *      N = 4
     *      matrix[][] = {{1, 0, 2, -1},
     *                    {3, 0, 0, 5},
     *                    {2, 1, 4, -3},
     *                    {1, 0, 5, 0}}
     *      Output: 30
     *      Explanation:
     *      Determinant of the given matrix is 30.
     *
     * Example 2:
     *      Input:
     *      N = 3
     *      matrix[][] = {{1, 2, 3},
     *                    {4, 5, 6},
     *                    {7, 10, 9}}
     *      Output: 12
     *      Explanation:
     *      Determinant of the given matrix is 12.
     */
    static int determinantOfMatrix(int matrix[][], int n)
    {
        if (n == 1) {
            return matrix[0][0];
        }
        else if (n == 2) {
            return calculateDeterminantForTwoByTwoMatrix(matrix);
        } else if (n == 3){
            return calculateDeterminantForThreeByThreeMatrix(matrix);
        } else {
            int res = 0;
            for (int i = 0; i < n; i++) {
                int[][] tmpMatrix = createTmpMatrix(matrix, i);
                int num = matrix[0][i];
                if (i % 2 == 0) {
                    res += (num * determinantOfMatrix(tmpMatrix, n - 1));
                } else {
                    res -= (num * determinantOfMatrix(tmpMatrix, n - 1));
                }
            }
            return res;
        }
    }

    private static int calculateDeterminantForTwoByTwoMatrix(int[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    private static int calculateDeterminantForThreeByThreeMatrix(int[][] matrix) {
        int[][] tmpMatrix = new int[2][2];
        tmpMatrix[0][0] = matrix[1][1];
        tmpMatrix[0][1] = matrix[1][2];
        tmpMatrix[1][0] = matrix[2][1];
        tmpMatrix[1][1] = matrix[2][2];
        int aDeterminant = calculateDeterminantForTwoByTwoMatrix(tmpMatrix);
        aDeterminant *= matrix[0][0];

        tmpMatrix[0][0] = matrix[1][0];
        tmpMatrix[1][0] = matrix[2][0];

        int bDeterminant = calculateDeterminantForTwoByTwoMatrix(tmpMatrix);
        bDeterminant *= matrix[0][1];

        tmpMatrix[0][1] = matrix[1][1];
        tmpMatrix[1][1] = matrix[2][1];

        int cDeterminant = calculateDeterminantForTwoByTwoMatrix(tmpMatrix);
        cDeterminant *= matrix[0][2];
        return aDeterminant - bDeterminant + cDeterminant;
    }

    private static int[][] createTmpMatrix(int[][] from, int excludeCol) {
        int[][] result = new int[from.length - 1][from.length - 1];
        for (int i = 1; i < from.length; i++) {
            int colIdx = 0;
            for (int j = 0; j < from.length; j++) {
                if (j != excludeCol) {
                    result[i - 1][colIdx++] = from[i][j];
                }
            }
        }
        return result;
    }

}
