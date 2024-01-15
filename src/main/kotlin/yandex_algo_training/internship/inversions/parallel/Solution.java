package yandex_algo_training.internship.inversions.parallel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] numbers = new int[n];
        String[] split = reader.readLine().split("\\s+");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(split[i]);
        }
        long[] res = countAverageInversions(numbers);
        System.out.printf("%d/%d", res[0], res[1]);
    }

    private static long[] countAverageInversions(int[] numbers) {
        LongAdder totalInv = new LongAdder();
        long totalRepl = 0;
        ForkJoinPool pool = new ForkJoinPool();
        List<Future<Long>> futures = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                swap(numbers, i, j);
                int[] copy = new int[numbers.length];
                int[] temp = new int[numbers.length];
                System.arraycopy(numbers, 0, copy, 0, numbers.length);
                var f = pool.submit(() -> countInversions(copy, temp, numbers.length));
                futures.add(f);
                swap(numbers, i, j);
                ++totalRepl;
            }
        }
        for(Future<Long> f: futures) {
            try {
                totalInv.add(f.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        pool.shutdownNow();
        long invs = totalInv.sum();
        long gcd = gcd(invs, totalRepl);
        long[] res = new long[2];
        res[0] = invs / gcd;
        res[1] = totalRepl / gcd;
        return res;
    }

    private static void swap(int[] numbers, int left, int right) {
        int tmp = numbers[left];
        numbers[left] = numbers[right];
        numbers[right] = tmp;
    }

    private static long gcd(long x, long y) {
        while (y != 0) {
            long tmp = y;
            y = x % y;
            x = tmp;
        }
        return x;
    }

    private static long mergeAndCount(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        long invCount = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                invCount += (mid - i + 1);
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }

        return invCount;
    }

    private static long mergeSortAndCount(int[] arr, int[] temp, int l, int r) {
        long invCount = 0;
        if (l < r) {
            int m = l + (r - l) / 2;
            invCount += mergeSortAndCount(arr, temp, l, m);
            invCount += mergeSortAndCount(arr, temp, m + 1, r);
            invCount += mergeAndCount(arr, temp, l, m, r);
        }
        return invCount;
    }

    private static long countInversions(int[] numbers, int[] temp, int size) {
        return mergeSortAndCount(numbers, temp, 0, size - 1);
    }
}
