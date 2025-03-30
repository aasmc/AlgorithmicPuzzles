package geeks_for_geeks.algorithms.sorting;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Executors;

public class Solutions {


    //Function to sort the array using bubble sort algorithm.
    public static void bubbleSort(int arr[], int n) {

        for (int i = 0; i < n; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    //Function to sort the array using insertion sort algorithm.
    public void insertionSort(int arr[], int n) {
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int sortedIdx = i - 1;
            while (sortedIdx >= 0 && arr[sortedIdx] > key) {
                arr[sortedIdx + 1] = arr[sortedIdx];
                --sortedIdx;
            }
            arr[sortedIdx + 1] = key;
        }
    }

    //Function to sort an array using quick sort algorithm.
    static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int currentPivot = selectPivot(low, high);
            int newPivot = partition(arr, low, high, currentPivot);
            quickSort(arr, low, newPivot);
            quickSort(arr, newPivot + 1, high);
        }
    }

    static int partition(int arr[], int low, int high, int pivotIdx) {
        swap(arr, low, pivotIdx);
        int pivot = arr[low];
        int i = low - 1;
        int j = high + 1;
        while (true) {
            do {
                ++i;
            } while (arr[i] < pivot);
            do {
                --j;
            } while (arr[j] > pivot);
            if (i >= j) {
                return j;
            }
            swap(arr, i, j);
        }
    }

    //Function to sort the binary array.
    static void binSort(int A[], int N) {
        int toSwapIdx = 0;
        for (int i = 0; i < N; i++) {
            if (A[i] == 0) {
                int tmp = A[i];
                A[i] = A[toSwapIdx];
                A[toSwapIdx++] = tmp;
            }
        }
    }

    // arr[]: Input Array
    // N : Size of the Array arr[]
    //Function to count inversions in the array.
    static long inversionCount(long arr[], long N) {
        // Your Code Here
        return inversionCountRec(arr, 0, (int) N - 1);
    }

    private static long inversionCountRec(long[] arr, int left, int right) {
        long res = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;
            res += inversionCountRec(arr, left, mid);
            res += inversionCountRec(arr, mid + 1, right);
            res += countAndMerge(arr, left, mid, right);
        }
        return res;
    }

    private static long countAndMerge(long[] arr, int left, int mid, int right) {
        long[] leftArr = new long[mid - left + 1];
        if (mid - left + 1 >= 0) System.arraycopy(arr, left, leftArr, 0, mid - left + 1);
        long[] rightArr = new long[right - mid];
        for (int i = 0; i < right - mid; i++) {
            rightArr[i] = arr[mid + i + 1];
        }
        int lIdx = 0;
        int rIdx = 0;
        int curIdx = left;
        long res = 0;
        while (lIdx < leftArr.length && rIdx < rightArr.length) {
            if (leftArr[lIdx] <= rightArr[rIdx]) {
                arr[curIdx++] = leftArr[lIdx++];
            } else {
                arr[curIdx++] = rightArr[rIdx++];
                res += leftArr.length - lIdx;
            }
        }
        while (lIdx < leftArr.length) {
            arr[curIdx++] = leftArr[lIdx++];
        }
        while (rIdx < rightArr.length) {
            arr[curIdx++] = rightArr[rIdx++];
        }
        return res;
    }

    //Function to return a list containing the union of the two arrays.
    public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m) {
        Set<Integer> noDuplicates = new HashSet<>();
        for (int i = 0; i < n; i++) {
            noDuplicates.add(arr1[i]);
        }
        for (int i = 0; i < m; i++) {
            noDuplicates.add(arr2[i]);
        }
        ArrayList<Integer> result = new ArrayList<>(noDuplicates.size());
        result.addAll(noDuplicates);
        Collections.sort(result);
        return result;
    }

    //Function to return a list containing the intersection of two arrays.
    static ArrayList<Integer> printIntersection(int arr1[], int arr2[], int n, int m) {
        ArrayList<Integer> result = new ArrayList<>();
        int firstIdx = 0;
        int secondIdx = 0;
        while (firstIdx < n && secondIdx < m) {
            if (firstIdx > 0 && arr1[firstIdx] == arr1[firstIdx - 1]) {
                ++firstIdx;
                continue;
            }
            if (arr1[firstIdx] == arr2[secondIdx]) {
                result.add(arr1[firstIdx]);
                firstIdx++;
                secondIdx++;
            } else if (arr1[firstIdx] < arr2[secondIdx]) {
                firstIdx++;
            } else if (arr1[firstIdx] > arr2[secondIdx]) {
                secondIdx++;
            }
        }
        return result;
    }

    //Function to count the number of possible triangles.
    static int findNumberOfTriangles(int arr[], int n) {
        Arrays.sort(arr);
        int result = 0;
        for (int i = 0; i < n; i++) {
            int k = i + 2;
            for (int j = i + 1; j < n; j++) {
                while (k < n && arr[k] < arr[i] + arr[j]) {
                    k++;
                }
                result += (k - (j + 1));
            }
        }
        return result;
    }

    /**
     * Given an array arr[] of n integers. Check whether it contains a
     * triplet that sums up to zero.
     * Expected Time Complexity: O(n2)
     * Expected Auxiliary Space: O(1)
     */
    public boolean findTriplets(int arr[], int n) {
        if (n < 3) return false;
        for (int i = 0; i < n; i++) {
            int elem = arr[i];
            Pair p = findPairOfSum(-elem, arr);
            if (p != null) return true;
        }
        return false;
    }

    private static Pair findPairOfSum(int sum, int[] arr) {
        Map<Integer, Integer> complements = new HashMap<>();
        for (int j : arr) {
            if (j == -sum) continue; // skip identity values
            complements.put(sum - j, j);
        }
        for (int elem :
                arr) {
            if (elem == -sum) continue; // skip identity values
            if (complements.containsKey(elem) && complements.get(elem) != elem) {
                return new Pair(elem, complements.get(elem));
            }
        }
        return null;
    }

    static class Pair {
        private int first;
        private int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }
    }

    /**
     * Given an array of size n and a range [a, b]. The task is to partition the
     * array around the range such that array is divided into three parts.
     * 1) All elements smaller than a come first.
     * 2) All elements in range a to b come next.
     * 3) All elements greater than b appear in the end.
     * The individual elements of three sets can appear in any order.
     */
    public void threeWayPartition(int array[], int a, int b) {
        int low = 0;
        int mid = 0;
        int high = array.length - 1;
        while (mid <= high) {
            if (array[mid] < a) {
                swap(array, low, mid);
                ++low;
                ++mid;
            } else if (array[mid] > b) {
                swap(array, mid, high);
                --high;
            } else {
                ++mid;
            }
        }
    }


    //Function to arrange all letters of a string in lexicographical
    //order using Counting Sort.
    public static String countSort(String arr) {
        int[] letters = new int[26];
        for (int i = 0; i < arr.length(); ++i) {
            letters[arr.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < letters[i]; ++j) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }

    // arr[]: Input Array
    // N: Size of the Array arr[]
    //Function to segregate 0s, 1s and 2s in sorted increasing order.
    public static void segragate012(int arr[], int N) {
        int low = 0;
        int mid = 0;
        int high = N - 1;
        while (mid <= high) {
            if (arr[mid] < 1) {
                swap(arr, low, mid);
                ++mid;
                ++low;
            } else if (arr[mid] > 1) {
                swap(arr, mid, high);
                --high;
            } else {
                ++mid;
            }
        }
    }

    /**
     * Given an array of N elements and a number K. The task is to arrange array
     * elements according to the absolute difference with K, i. e., element having
     * minimum difference comes first and so on.
     * Note : If two or more elements are at equal distance arrange them in same
     * sequence as in the given array.
     * <p>
     * Example
     * Input: N = 5, K = 7
     * arr[] = {10, 5, 3, 9, 2}
     * Output: 5 9 10 3 2
     * Explanation: Sorting the numbers accoding to
     * the absolute difference with 7, we have
     * array elements as 5, 9, 10, 3, 2.
     */
    static void sortABS(int arr[], int n, int k) {
        Map<Integer, List<Integer>> complements = new TreeMap<>();
        for (int elem :
                arr) {
            if (complements.containsKey(Math.abs(k - elem))) {
                complements.get(Math.abs(k - elem)).add(elem);
            } else {
                List<Integer> l = new ArrayList<>();
                l.add(elem);
                complements.put(Math.abs(k - elem), l);
            }
        }
        int idx = 0;
        Set<Integer> integers = complements.keySet();
        for (int key :
                integers) {
            List<Integer> l = complements.get(key);
            for (int i :
                    l) {
                arr[idx++] = i;
            }
        }
    }

    /**
     * Given an array arr of size n and an integer X.
     * Find if there's a triplet in the array which sums up to the given integer X.
     */
    public static boolean find3Numbers(int[] arr, int n, int x) {
        Arrays.sort(arr);
        for (int i = 0; i <= n - 3; i++) {
            int target = x - arr[i];
            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                int sum = arr[j] + arr[k];
                if (target == sum) return true;
                else if (target > sum) j++;
                else k--;
            }
        }
        return false;
    }

    /**
     * Given an array of size n,
     * find the minimum difference between any pair of elements in given array.
     */
    public static int MinimumDifference(int arr[], int n) {
        Arrays.sort(arr);
        if (n < 2) throw new RuntimeException("Number of elements in the array must be greater than 2");
        int first = arr[0];
        int second = arr[1];
        int diff = Math.abs(second - first);
        for (int i = 2; i < n; i++) {
            int cur = arr[i];
            int curDiff = Math.abs(cur - second);
            diff = Math.min(diff, curDiff);
            second = cur;
        }
        return diff;
    }

    /**
     * Given an array arr[] of N positive integers and a number K.
     * The task is to find the kth smallest element in the array.
     * <p>
     * Expected Time Complexity: O(NlogK)
     * Expected Auxilliary Space: O(K)
     */
    public static int kthSmallest(int arr[], int n, int k) {
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int curPivot = selectPivot(left, right);
            int pivot = lomutoPartition(arr, left, right, curPivot);
            if (pivot == k - 1) {
                return arr[pivot];
            } else if (pivot > k - 1) {
                right = pivot - 1;
            } else {
                left = pivot + 1;
            }
        }
        throw new IllegalArgumentException("K should be within the range of the array");
    }

    private static int lomutoPartition(int[] arr, int start, int end, int pivotIdx) {
        swap(arr, pivotIdx, end);
        int pivot = arr[end];
        int smallerIdx = start - 1;
        for (int curIdx = start; curIdx < end; ++curIdx) {
            if (arr[curIdx] <= pivot) {
                ++smallerIdx;
                swap(arr, smallerIdx, curIdx);
            }
        }
        swap(arr, smallerIdx + 1, end);
        return smallerIdx + 1;
    }

    private static int selectPivot(int start, int end) {
        return (int) ((Math.random() * (end - start)) + start);
    }

    private static void swap(int[] arr, int from, int to) {
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }

    /**
     * Given two arrays X and Y of positive integers, find the number
     * of pairs such that xy > yx (raised to power of) where x is an
     * element from X and y is an element from Y.
     * <p>
     * Input:
     * M = 3, X[] = [2 1 6]
     * N = 2, Y[] = [1 5]
     * Output: 3
     * Explanation:
     * The pairs which follow x^y > y^x are
     * as such: 2^1 > 1^2,  2^5 > 5^2 and 6^1 > 1^6 .
     * <p>
     * Input:
     * M = 4, X[] = [2 3 4 5]
     * N = 3, Y[] = [1 2 3]
     * Output: 5
     * Explanation:
     * The pairs for the given input are
     * 2^1 > 1^2 , 3^1 > 1^3 , 3^2 > 2^3 , 4^1 > 1^4 ,
     * 5^1 > 1^5 .
     * <p>
     * <p>
     * Expected Time Complexity: O((N + M)log(N)).
     * Expected Auxiliary Space: O(1).
     */
    static long countPairs(int x[], int y[], int M, int N) {
        int[] noOfY = new int[5];
        for (int i = 0; i < N; i++) {
            if (y[i] < 5) {
                noOfY[y[i]]++;
            }
        }
        Arrays.sort(y);
        long total = 0L;
        for (int num :
                x) {
            total += countPairsForX(num, y, noOfY);
        }
        return total;
    }

    static long countPairsForX(int x, int[] yArray, int[] noOfY) {
        if (x == 0) return 0L;
        if (x == 1) return (long) noOfY[0];
        int idx = Arrays.binarySearch(yArray, x);
        if (idx < 0) {
            idx = Math.abs(idx + 1);
        } else {
            while (idx < yArray.length && yArray[idx] == x) {
                ++idx;
            }
        }
        long answer = (long) yArray.length - idx;
        answer += (noOfY[0] + noOfY[1]);
        if (x == 2) answer -= (noOfY[3] + noOfY[4]);
        if (x == 3) answer += noOfY[2];
        return answer;
    }

    /**
     * Given two sorted arrays arr1[] and arr2[] of sizes n and m in
     * non-decreasing order. Merge them in sorted order without using
     * any extra space. Modify arr1 so that it contains the first N
     * elements and modify arr2 so that it contains the last M elements.
     * <p>
     * Input:
     * n = 4, arr1[] = [1 3 5 7]
     * m = 5, arr2[] = [0 2 6 8 9]
     * Output:
     * arr1[] = [0 1 2 3]
     * arr2[] = [5 6 7 8 9]
     * Explanation:
     * After merging the two
     * non-decreasing arrays, we get,
     * 0 1 2 3 5 6 7 8 9.
     * <p>
     * Input:
     * n = 2, arr1[] = [10, 12]
     * m = 3, arr2[] = [5 18 20]
     * Output:
     * arr1[] = [5 10]
     * arr2[] = [12 18 20]
     * Explanation:
     * After merging two sorted arrays
     * we get 5 10 12 18 20.
     * <p>
     * Expected Time Complexity:  O((n+m) log(n+m))
     * Expected Auxilliary Space: O(1)
     */
    public static void merge(long arr1[], long arr2[], int n, int m) {
        // Iterate through all elements of arr2[] starting from
        // the last element
        for (int i = m - 1; i >= 0; i--) {
            /* Find the smallest element in arr1[] greater than arr2[i]. Move all
               elements one position ahead till the smallest greater
               element is not found */
            int j;
            long last = arr1[n - 1];
            for (j = n - 2; j >= 0 && arr1[j] > arr2[i]; j--)
                arr1[j + 1] = arr1[j];

            // If there was a greater element
            if (j != n - 2 || last > arr2[i]) {
                arr1[j + 1] = arr2[i];
                arr2[i] = last;
            }
        }
    }

    /**
     * Given three sorted arrays A, B and C of size N, M and P respectively.
     * The task is to merge them into a single array which must be sorted in
     * increasing order.
     * <p>
     * Input:
     * N = 4, A[] = [1 2 3 4]
     * M = 5, B[] = [1 2 3 4 5]
     * P = 6, C[] = [1 2 3 4 5 6]
     * Output: 1 1 1 2 2 2 3 3 3 4 4 4 5 5 6
     * Explanation: Merging these three sorted
     * arrays, we have:
     * 1 1 1 2 2 2 3 3 3 4 4 4 5 5 6.
     * <p>
     * Input:
     * N = 2, A[] = [1 2]
     * M = 3, B[] = [2 3 4]
     * P = 4, C[] = [4 5 6 7]
     * Output: 1 2 2 3 4 4 5 6 7
     * Explanation: Merging three sorted arrays,
     * we have: 1 2 2 3 4 4 5 6 7.
     * <p>
     * Expected Time Complexity: O(N + M + P)
     * Expected Auxiliary Space: O(N + M + P) for the resultant array only.
     */
    static ArrayList<Integer> merge3sorted(int A[], int B[], int C[]) {
        // creating an empty list to store sorted numbers
        ArrayList<Integer> list = new ArrayList<>(A.length + B.length + C.length);
        int i = 0, j = 0, k = 0;

        // using merge concept and trying to find
        // smallest of three while all three arrays
        // contains at least one element
        while (i < A.length && j < B.length && k < C.length) {
            int a = A[i];
            int b = B[j];
            int c = C[k];
            if (a <= b && a <= c) {
                list.add(a);
                i++;
            } else if (b <= a && b <= c) {
                list.add(b);
                j++;
            } else {
                list.add(c);
                k++;
            }
        }
        // next three while loops are to sort two
        // of arrays if one of the three gets exhausted
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                list.add(A[i]);
                i++;
            } else {
                list.add(B[j]);
                j++;
            }
        }
        while (j < B.length && k < C.length) {
            if (B[j] < C[k]) {
                list.add(B[j]);
                j++;
            } else {
                list.add(C[k]);
                k++;
            }
        }
        while (i < A.length && k < C.length) {
            if (A[i] < C[k]) {
                list.add(A[i]);
                i++;
            } else {
                list.add(C[k]);
                k++;
            }
        }

        // if one of the array are left then
        // simply appending them as there will
        // be only largest element left
        while (i < A.length) {
            list.add(A[i]);
            i++;
        }
        while (j < B.length) {
            list.add(B[j]);
            j++;
        }
        while (k < C.length) {
            list.add(C[k]);
            k++;
        }
        return list;
    }

    /**
     * Given an array arr[](0-based indexing) of N integers which is closer sorted
     * (defined below) and an element x. The task is to find the index of element
     * x if it is present. If not present, then print -1.
     * Closer Sorted: The first array is sorted, but after sorting some elements
     * are moved to either of the adjacent positions, i.e, maybe to the arr[i+1]
     * or arr[i-1].
     * <p>
     * Input: N = 5, A[] = [3 2 10 4 40], x = 2
     * Output: 1
     * Explanation: 2 is present at index 1
     * (0-based indexing) in the given array.
     * <p>
     * Input: N = 4, A[] = [2 1 4 3], x = 5
     * Output: -1
     * Explanation:
     * 5 is not in the array so the output will
     * be -1.
     * <p>
     * <p>
     * Expected Time Complexity: O(Log(N)).
     * Expected Auxiliary Space: O(1).
     */
    static long closer(int arr[], int n, long x) {
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == x) {
                return mid;
            }
            if (mid > 0 && arr[mid - 1] == x) {
                return mid - 1;
            }
            if (mid < n - 1 && arr[mid + 1] == x) {
                return mid + 1;
            }
            if (arr[mid] < x) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Given arrival and departure times of all trains that reach a railway station.
     * Find the minimum number of platforms required for the railway station so
     * that no train is kept waiting.
     * Consider that all the trains arrive on the same day and leave on the same day.
     * Arrival and departure time can never be the same for a train but we can
     * have arrival time of one train equal to departure time of the other.
     * At any given instance of time, same platform can not be used for both
     * departure of a train and arrival of another train. In such cases, we need
     * different platforms.
     * <p>
     * Input: n = 6
     * arr[] = {0900, 0940, 0950, 1100, 1500, 1800}
     * dep[] = {0910, 1200, 1120, 1130, 1900, 2000}
     * Output: 3
     * Explanation:
     * Minimum 3 platforms are required to
     * safely arrive and depart all trains.
     * <p>
     * Input: n = 3
     * arr[] = {0900, 1100, 1235}
     * dep[] = {1000, 1200, 1240}
     * Output: 1
     * Explanation: Only 1 platform is required to
     * safely manage the arrival and departure
     * of all trains.
     * <p>
     * Note: Time intervals are in the 24-hour format(HHMM) ,
     * where the first two characters represent hour (between 00 to 23 )
     * and the last two characters represent minutes (this may be > 59).
     * <p>
     * Expected Time Complexity: O(nLogn)
     * Expected Auxiliary Space: O(n)
     */
    static int findPlatform(int arr[], int dep[], int n) {
        // add your code here
        Arrays.sort(arr);
        Arrays.sort(dep);
        int arrivalIdx = 1;
        int departureIdx = 0;
        int maxPlatforms = 1;
        int currentPlatforms = 1;
        while (arrivalIdx < n && departureIdx < n) {
            if (arr[arrivalIdx] <= dep[departureIdx]) {
                ++currentPlatforms;
                ++arrivalIdx;
            } else {
                --currentPlatforms;
                ++departureIdx;
            }
            maxPlatforms = Math.max(maxPlatforms, currentPlatforms);
        }
        return maxPlatforms;
    }

}
