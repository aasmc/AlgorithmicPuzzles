package geeks_for_geeks.algorithms.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Contest {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            toggleMiddleBit(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void toggleMiddleBit(BufferedReader reader) throws IOException {
        int testCases = Integer.parseInt(reader.readLine());
        for (int i = 0; i < testCases; i++) {
            int number = Integer.parseInt(reader.readLine());
            String binaryStr = Integer.toBinaryString(number);
            int pos = binaryStr.length() / 2;
            number = toggleBit(number, pos);
            if (binaryStr.length() % 2 == 0) {
                number = toggleBit(number, pos - 1);
            }
            System.out.println(number);
        }
    }

    private static int toggleBit(int set, int pos) {
        return set ^ (1 << (pos));
    }

    private static void noAdjacentElements(BufferedReader scanner) throws IOException {
        int testCases = Integer.parseInt(scanner.readLine());
        for (int i = 0; i < testCases; i++) {
            int size = Integer.parseInt(scanner.readLine());
            String[] arrayStr = scanner.readLine().split(" ");
            int[] array = new int[size];
            for (int j = 0; j < size; j++) {
                array[j] = Integer.parseInt(arrayStr[j]);
            }
            System.out.println(findMaxSum(array));
        }
    }

    private static int findMaxSum(int[] nums) {
        int inclusive = nums[0];
        int exclusive = 0;
        int exclusiveNew;

        for (int i=1; i<nums.length; i++){
            exclusiveNew = Math.max(inclusive, exclusive);
            inclusive = exclusive + nums[i];
            exclusive = exclusiveNew;
        }
        return Math.max(inclusive, exclusive);
    }

    private static void booleanMatrixContest(BufferedReader scanner) throws IOException {
        int testCases = Integer.parseInt(scanner.readLine());
        for (int i = 0; i < testCases; i++) {
            String[] rowCol = scanner.readLine().split(" ");
            int rows = Integer.parseInt(rowCol[0]);
            int cols = Integer.parseInt(rowCol[1]);
            int[][] matrix = new int[rows][cols];
            fillMatrix(matrix, scanner, rows);
            printMatrix(matrix);
        }
    }

    private static void fillMatrix(int[][] matrix, BufferedReader scanner, int rows) throws IOException {
        for (int row = 0; row < rows; row++) {
            boolean found = false;
            String s = scanner.readLine();
            if (s.contains("1")) {
                found = true;
            }
            if (found) {
                Arrays.fill(matrix[row], 1);
            } else {
                Arrays.fill(matrix[row], 0);
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < ints.length; j++) {
                System.out.print(ints[j]);
                if (j < ints.length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }
}
