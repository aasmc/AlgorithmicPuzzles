package yandex_algo_training.contest01.diego03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) throws IOException {
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
        List<Integer> res = new ArrayList<>();
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
