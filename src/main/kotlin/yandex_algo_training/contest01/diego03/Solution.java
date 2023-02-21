package yandex_algo_training.contest01.diego03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        solve();
    }


    private static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int diegoNum = Integer.parseInt(br.readLine());
        String diegoStr = br.readLine().trim();

        int[] diego = readStickers(diegoStr, diegoNum);
        int numCollections = Integer.parseInt(br.readLine());
        String collStr = br.readLine();
        int[] colls = readStickers(collStr, numCollections);

        int[] sortedDiego = removeDuplicatesAndSort(diego);
        int[] res = countStickers(colls, sortedDiego);
        Arrays.stream(res).forEach(System.out::println);
        br.close();
    }

    private static int[] removeDuplicatesAndSort(int[] arr) {
        if (arr.length == 1) return arr;
        Arrays.sort(arr);
        int positionAfterUnique = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[positionAfterUnique - 1]) {
                arr[positionAfterUnique++] = arr[i];
            }
        }
        int[] res = new int[positionAfterUnique];
        System.arraycopy(arr, 0, res, 0, positionAfterUnique);
        return res;
    }
    private static int[] readStickers(String str, int num) {
        if (num > 0) {
            int[] res = new int[num];
            int idx = 0;
            for (String n: str.split(" ")) {
                res[idx++] = Integer.parseInt(n);
            }
            return res;
        }
        return new int[0];
    }

    private static int[] countStickers(int[] colls, int[] diego) {
        if (colls.length == 0) {
            return new int[0];
        }
        int[] res = new int[colls.length];
        if (diego.length == 0) {
            return res;
        }
        for (int i = 0; i < colls.length; i++) {
            int target = colls[i];
            int idx = Arrays.binarySearch(diego, target);
            if (idx < 0) {
                // (-(insertion point) - 1)
                res[i] = Math.abs(idx + 1);
            } else {
                res[i] = idx;
            }
        }
        return res;
    }


    private static void solveV1() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int diegoNum = Integer.parseInt(br.readLine());
        String diegoStr = br.readLine().trim();
        TreeSet<Integer> diegoStickers = new TreeSet<>();
        if (diegoNum > 0) {
            String[] nums = diegoStr.split(" ");
            for (String num : nums) {
                diegoStickers.add(Integer.parseInt(num));
            }
        }
        int numCollections = Integer.parseInt(br.readLine());
        String collStr = br.readLine();
        List<Integer> colls = new ArrayList<>();
        if (numCollections > 0) {
            String[] nums = collStr.split(" ");
            for (String num : nums) {
                colls.add(Integer.parseInt(num));
            }
        }
        List<Integer> res = countStickers(colls, diegoStickers);
        res.forEach(System.out::println);
        br.close();
    }

    private static List<Integer> countStickers(List<Integer> colls, TreeSet<Integer> diego) {
        if (colls.isEmpty()) return Collections.emptyList();
        List<Integer> res = new ArrayList<>(colls.size());
        if (diego.isEmpty()) {
            for (int i = 0; i < colls.size(); ++i) {
                res.add(0);
            }
            return res;
        }
        for (Integer num : colls) {
            res.add(diego.headSet(num).size());
        }
        return res;
    }
}
