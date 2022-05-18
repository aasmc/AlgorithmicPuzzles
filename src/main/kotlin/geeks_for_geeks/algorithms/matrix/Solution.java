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
}
