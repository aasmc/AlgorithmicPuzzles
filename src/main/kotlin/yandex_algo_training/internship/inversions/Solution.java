package yandex_algo_training.internship.inversions;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] numbers = new int[n];
        String[] split = reader.readLine().split("\\s+");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(split[i]);
        }
        int[] res = countAverageInversions(numbers);
        System.out.printf("%d/%d", res[0], res[1]);
    }

    private static int[] countAverageInversions(int[] numbers) {
        int totalInv = 0;
        int totalRepl = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int[] copy = new int[numbers.length];
                System.arraycopy(numbers, 0, copy, 0, numbers.length);
                int tmp = copy[i];
                copy[i] = copy[j];
                copy[j] = tmp;
                totalInv += countInversions(copy);
                ++totalRepl;
            }
        }
        int gcd = gcd(totalInv, totalRepl);
        int[] res = new int[2];
        res[0] = totalInv / gcd;
        res[1] = totalRepl / gcd;
        return res;
    }

    private static int gcd(int x, int y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }

    private static int countInversions(int[] numbers) {
        int inv = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    ++inv;
                }
            }
        }
        return inv;
    }
}
