package geeks_for_geeks.algorithms.hashing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Contest {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            kSmallestNumbers(reader);
            xorPair(reader);
            kThSmallestDifference(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> readParams(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static int[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .mapToInt(num -> num)
                .toArray();
    }

    private static void kSmallestNumbers(BufferedReader reader) throws IOException {
        int testCases = Integer.parseInt(reader.readLine());
        for (int i = 0; i < testCases; i++) {
            List<Integer> params = readParams(reader);
            int n = params.get(0);
            int k = params.get(1);
            int[] array = readArray(reader);
            findKthSmallestSum(array, k);
        }
    }

    private static void findKthSmallestSum(int[] array, int k) {
        Arrays.sort(array);
        long result = 0;
        for (int i = 0; i < k; i++) {
            result += array[i];
        }
        System.out.println(result);
    }



    private static void xorPair(BufferedReader reader) throws IOException {
        int testCases = Integer.parseInt(reader.readLine());
        for (int i = 0; i < testCases; i++) {
            List<Integer> params = readParams(reader);
            int n = params.get(0);
            int k = params.get(1);
            int[] array = readArray(reader);
            findXorPair(array, k);
        }
    }

    private static void findXorPair(int[] array, int target) {
        Set<Integer> xor = new HashSet<>();
        boolean found = false;
        for (int num : array) {
            if (xor.contains(num)) {
                found = true;
                break;
            } else {
                xor.add(target ^ num);
            }
        }
        if (found) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static void kThSmallestDifference(BufferedReader reader) throws IOException {
        int testCases = Integer.parseInt(reader.readLine());
        for (int i = 0; i < testCases; i++) {
            List<Integer> params = readParams(reader);
            int n = params.get(0);
            int k = params.get(1);
            int[] array = readArray(reader);
            findDiff(array, k);
        }
    }

    private static void findDiff(int[] array, int k) {
        int n = array.length;
        Arrays.sort(array);

        // Minimum absolute difference
        int low = array[1] - array[0];
        for (int i = 1; i < n - 1; i++)
            low = Math.min(low, array[i + 1] - array[i]);

        // Maximum absolute difference
        int high = array[n - 1] - array[0];

        // Do binary search for k-th absolute difference
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (countPairs(array, mid) < k)
                low = mid + 1;
            else
                high = mid;
        }
        System.out.println(low);
    }

    // number of pairs whose abs diff is less than or equal to mid
    private static int countPairs(int[] a, int mid) {
        int n = a.length, res = 0;
        for (int i = 0; i < n; ++i) {
            int j = i;
            while (j < n && a[j] - a[i] <= mid) j++;
            res += j - i - 1;
        }
        return res;
    }
}
