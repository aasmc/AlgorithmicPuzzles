package geeks_for_geeks.algorithms.matrix;

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
}
