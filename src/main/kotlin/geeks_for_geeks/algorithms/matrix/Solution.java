package geeks_for_geeks.algorithms.matrix;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

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
    static int[][] multiplyMatrix(int A[][], int B[][]) {
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

    static ArrayList<Integer> snakePattern(int matrix[][]) {
        // code here
        ArrayList<Integer> result = new ArrayList<>(matrix.length * matrix[0].length);
        for (int i = 0; i < matrix.length; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    result.add(matrix[i][j]);
                }
            } else {
                for (int j = matrix.length - 1; j >= 0; j--) {
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
    static void transpose(int matrix[][], int n) {
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
    static void rotateby90(int matrix[][], int n) {
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
     * <p>
     * Example 1:
     * Input:
     * N = 4
     * matrix[][] = {{1, 0, 2, -1},
     * {3, 0, 0, 5},
     * {2, 1, 4, -3},
     * {1, 0, 5, 0}}
     * Output: 30
     * Explanation:
     * Determinant of the given matrix is 30.
     * <p>
     * Example 2:
     * Input:
     * N = 3
     * matrix[][] = {{1, 2, 3},
     * {4, 5, 6},
     * {7, 10, 9}}
     * Output: 12
     * Explanation:
     * Determinant of the given matrix is 12.
     */
    static int determinantOfMatrix(int matrix[][], int n) {
        if (n == 1) {
            return matrix[0][0];
        } else if (n == 2) {
            return calculateDeterminantForTwoByTwoMatrix(matrix);
        } else if (n == 3) {
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

    /**
     * You are given a matrix of dimensions n x m.
     * The task is to perform boundary traversal on the matrix in a clockwise manner.
     * <p>
     * Input:
     * n = 4, m = 4
     * matrix[][] = {{1, 2, 3, 4},
     * {5, 6, 7, 8},
     * {9, 10, 11, 12},
     * {13, 14, 15,16}}
     * Output: 1 2 3 4 8 12 16 15 14 13 9 5
     * Explanation:
     * The matrix is:
     * 1 2 3 4
     * 5 6 7 8
     * 9 10 11 12
     * 13 14 15 16
     * The boundary traversal is:
     * 1 2 3 4 8 12 16 15 14 13 9 5
     */
    static ArrayList<Integer> boundaryTraversal(int matrix[][], int n, int m) {
        // code here
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            result.add(matrix[0][i]);
        }
        if (isSingleColumnMatrix(matrix)) {
            for (int i = 1; i < n; i++) {
                result.add(matrix[i][0]);
            }
        } else if (n > 1) {
            for (int i = 1; i < n; i++) {
                result.add(matrix[i][m - 1]);
            }

            for (int i = m - 2; i >= 0; i--) {
                result.add(matrix[n - 1][i]);
            }

            for (int i = n - 2; i >= 1; i--) {
                result.add(matrix[i][0]);
            }
        }
        return result;
    }

    private static boolean isSingleColumnMatrix(int[][] matrix) {
        boolean singleColumn = true;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length > 1) {
                singleColumn = false;
                break;
            }
        }
        return singleColumn;
    }

    /**
     * You are given a matrix of dimensions (n1 x m1).
     * You have to exchange its first column with the last column.
     * <p>
     * Input:
     * n1 = 4, m1 = 4
     * matrix[][] = {{1, 2, 3, 4},
     * {5, 6, 7, 8},
     * {9, 10, 11, 12},
     * {13, 14, 15,16}}
     * Output:
     * 4 2 3 1 8 6 7 5 12 10 11 9 16 14 15 13
     * Explanation:
     * Matrix is as follow:
     * 1 2 3 4
     * 5 6 7 8
     * 9 10 11 12
     * 13 14 15 16
     * After exchanging first column with
     * last column, we have matrix as follows:
     * 4 2 3 1
     * 8 6 7 5
     * 12 10 11 9
     * 16 14 15 13
     */
    static void exchangeColumns(int matrix[][]) {
        if (matrix[0].length <= 1) return;
        // code here
        for (int[] row : matrix) {
            int tmp = row[0];
            row[0] = row[row.length - 1];
            row[row.length - 1] = tmp;
        }
    }

    /**
     * Given a matrix of size r*c. Traverse the matrix in spiral form.
     * <p>
     * Input:
     * r = 4, c = 4
     * matrix[][] = {{1, 2, 3, 4},
     * {5, 6, 7, 8},
     * {9, 10, 11, 12},
     * {13, 14, 15,16}}
     * Output:
     * 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
     */
    static ArrayList<Integer> spirallyTraverse(int matrix[][], int r, int c) {
        // code here
        ArrayList<Integer> result = new ArrayList<>();
        int top = 0;
        int bottom = r - 1;
        int left = 0;
        int right = c - 1;
        while (top <= bottom && left <= right) {
            // topmost row
            for (int i = left; i <= right; ++i) {
                result.add(matrix[top][i]);
            }
            ++top;
            // rightmost col
            for (int i = top; i <= bottom; ++i) {
                result.add(matrix[i][right]);
            }
            --right;
            // bottommost row
            if (top <= bottom) {
                for (int i = right; i >= left; --i) {
                    result.add(matrix[bottom][i]);
                }
                --bottom;
            }
            // leftmost col
            if (left <= right) {
                for (int i = bottom; i >= top; --i) {
                    result.add(matrix[i][left]);
                }
                ++left;
            }
        }
        return result;
    }

    /**
     * Given a matrix of dimensions n1 x m1. Reverse its columns in-place such
     * that the last column will become the first column and so on.
     * <p>
     * Input:
     * n1 = 4, m1 = 3
     * matrix[][] = {{ 1,  2,  3},
     * { 5,  6,  7},
     * {11, 10,  9},
     * {13, 14, 15}}
     * Output: 3 2 1 7 6 5 9 10 11 15 14 13
     * Explanation:
     * Array after exchanging columns:
     * 3 2 1
     * 7 6 5
     * 9 10 11
     * 15 14 13
     */
    static void reverseCol(int matrix[][]) {
        // code here
        for (int[] row : matrix) {
            reverseRow(row);
        }
    }

    private static void reverseRow(int[] row) {
        for (int i = 0; i < row.length / 2; i++) {
            int tmp = row[i];
            row[i] = row[row.length - i - 1];
            row[row.length - i - 1] = tmp;
        }
    }

    /**
     * Given a matrix of dimensions n1 x m1. Interchange its rows in-place
     * such that the first row will become the last row and so on.
     * <p>
     * Example 1:
     * <p>
     * Input:
     * n1 = 4, m1 = 4
     * matrix[][] = {{1, 2, 3, 4},
     * {5, 6, 7, 8},
     * {9, 10, 11, 12},
     * {13, 14, 15,16}}
     * Output:
     * 13 14 15 16 9 10 11 12 5 6 7 8 1 2 3 4
     * Explanation:
     * Matrix after exchanging rows:
     * 13 14 15 16
     * 9 10 11 12
     * 5  6  7  8
     * 1  2  3  4
     * Note: Output is printed row-wise linearly.
     */
    static void interchangeRows(int matrix[][]) {
        // code here
        for (int i = 0; i < matrix.length / 2; i++) {
            int[] tmp = matrix[i];
            matrix[i] = matrix[matrix.length - i - 1];
            matrix[matrix.length - i - 1] = tmp;
        }
    }

    /**
     * Given a matrix of size n x m, where every row and column is sorted in
     * increasing order, and a number x. Find whether element x is present in
     * the matrix or not.
     * <p>
     * Input:
     * n = 3, m = 3, x = 62
     * matrix[][] = {{ 3, 30, 38},
     * {36, 43, 60},
     * {40, 51, 69}}
     * Output: 0
     * Explanation:
     * 62 is not present in the matrix,
     * so output is 0.
     * <p>
     * Expected Time Complexity: O(N + M)
     * Expected Auxiliary Space: O(1)
     */
    static boolean search(int matrix[][], int n, int m, int x) {
        // code here
        int row = 0;
        int col = m - 1;
        if (x < matrix[0][0]) return false;
        if (x > matrix[n - 1][m - 1]) return false;

        while (row < n && col >= 0) {
            if (matrix[row][col] == x) return true;
            else if (matrix[row][col] > x) --col;
            else ++row;
        }
        return false;
    }

    /**
     * Given a boolean matrix of size RxC where each cell contains either 0 or 1,
     * modify it such that if a matrix cell matrix[i][j] is 1 then all the cells
     * in its ith row and jth column will become 1.
     * <p>
     * Input:
     * R = 2, C = 2
     * matrix[][] = {{1, 0},
     * {0, 0}}
     * Output:
     * 1 1
     * 1 0
     * Explanation:
     * Only cell that has 1 is at (0,0) so all
     * cells in row 0 are modified to 1 and all
     * cells in column 0 are modified to 1.
     */
    void booleanMatrix(int matrix[][]) {
        // code here
        Stack<Integer> s = new Stack<>();
        int n = matrix.length;
        int m = matrix[0].length;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (matrix[row][col] == 1) {
                    s.add(row);
                    s.add(col);
                }
            }
        }
        while (!s.empty()) {
            int col = s.pop();
            int row = s.pop();
            for (int i = 0; i < n; i++) {
                matrix[i][col] = 1;
            }
            for (int j = 0; j < m; j++) {
                matrix[row][j] = 1;
            }
        }
    }

    /**
     * A beautiful matrix is a matrix in which the sum of elements in each row and column
     * is equal.
     * Given a square matrix of size NxN. Find the minimum number of operation(s)
     * that are required to make the matrix beautiful. In one operation you can increment
     * the value of any one cell by 1.
     *
     *
     *
     *    Input:
     *    N = 2
     *    matrix[][] = {{1, 2},
     *                  {3, 4}}
     *    Output: 4
     *    Explanation:
     *    Updated matrix:
     *    4 3
     *    3 4
     *    1. Increment value of cell(0, 0) by 3
     *    2. Increment value of cell(0, 1) by 1
     *    Hence total 4 operation are required.
     *
     */
    static int findMinOperation(int matrix[][], int n)
    {
        // Initialize the sumRow[]
        // and sumCol[] array to 0
        int[] sumRow = new int[n];
        int[] sumCol = new int[n];

        // Calculate sumRow[] and
        // sumCol[] array
        for (int i = 0; i < n; ++i)

            for (int j = 0; j < n; ++j)
            {
                sumRow[i] += matrix[i][j];
                sumCol[j] += matrix[i][j];
            }

        // Find maximum sum value
        // in either row or in column
        int maxSum = 0;
        for (int i = 0; i < n; ++i)
        {
            maxSum = Math.max(maxSum, sumRow[i]);
            maxSum = Math.max(maxSum, sumCol[i]);
        }

        int count = 0;
        for (int i = 0, j = 0; i < n && j < n;)
        {
            // Find minimum increment
            // required in either row
            // or column
            int diff = Math.min(maxSum - sumRow[i],
                    maxSum - sumCol[j]);

            // Add difference in
            // corresponding cell,
            // sumRow[] and sumCol[]
            // array
            matrix[i][j] += diff;
            sumRow[i] += diff;
            sumCol[j] += diff;

            // Update the count
            // variable
            count += diff;

            // If ith row satisfied,
            // increment ith value
            // for next iteration
            if (sumRow[i] == maxSum)
                ++i;

            // If jth column satisfied,
            // increment jth value for
            // next iteration
            if (sumCol[j] == maxSum)
                ++j;
        }
        return count;
    }
}
