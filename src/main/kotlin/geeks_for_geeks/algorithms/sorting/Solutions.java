package geeks_for_geeks.algorithms.sorting;

import java.util.*;

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

    private static int selectPivot(int start, int end) {
        return (int) ((Math.random() * (end - start)) + start);
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

    private Pair findPairOfSum(int sum, int[] arr) {
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
     *
     * Given an array of size n and a range [a, b]. The task is to partition the
     * array around the range such that array is divided into three parts.
     * 1) All elements smaller than a come first.
     * 2) All elements in range a to b come next.
     * 3) All elements greater than b appear in the end.
     * The individual elements of three sets can appear in any order.
     *
     */
    public void threeWayPartition(int array[], int a, int b)
    {
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

    private static void swap(int[] arr, int from, int to) {
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }

    //Function to arrange all letters of a string in lexicographical
    //order using Counting Sort.
    public static String countSort(String arr)
    {
        int[] letters = new int[26];
        for(int i = 0; i < arr.length(); ++i) {
            letters[arr.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; ++i) {
            for(int j = 0; j < letters[i]; ++j) {
                sb.append((char)('a' + i));
            }
        }
        return sb.toString();
    }

}
