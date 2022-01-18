package geeks_for_geeks.algorithms.searching;

public class Solutions {

    /**
     * Linear search.
     *
     * @param arr array of integers to be searched in
     * @param N   size of the array
     * @param X   element to be searched
     * @return index of the element or -1 if not present
     */
    static int search(int arr[], int N, int X) {

        for (int i = 0; i < N; i++) {
            if (arr[i] == X) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Binary search.
     *
     * @param arr
     * @param N
     * @param K
     * @return 1 if element is present in the array, -1 if not.
     */
    static int searchInSorted(int arr[], int N, int K) {
        int start = 0;
        int end = N;
        while (start <= end) {
            int middle = start + (end - start) / 2;
            if (arr[middle] == K) {
                return 1;
            }
            if (arr[middle] < K) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return -1;
    }

    // Function to count number of ones in the binary array
    // N: size of array
    // arr[]: input array
    public static int countOnes(int arr[], int N) {

        // Your code here
        int last = findFirstOccurrence(arr, N, 1);
        if (last == -1) {
            return 0;
        }
        return last + 1;
    }

    private static int findFirstOccurrence(int[] arr, int N, int target) {
        int start = 0;
        int end = N - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                if (mid < N - 1 && arr[mid + 1] == target) {
                    start = mid + 1;
                } else {
                    return mid;
                }
            } else {
                if (arr[mid] < target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

    long floorSqrt(long x) {
        long start = 0;
        long end = x;
        long answer = -1;
        while (start <= end) {
            long mid = start + (end - start) / 2;
            long midSquare = mid * mid;
            if (midSquare == x) {
                return mid;
            }
            if (midSquare < x) {
                answer = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return answer;
    }

    static int majorityElement(int a[], int size) {
        int count = 1;
        int index = 0;
        for (int i = 1; i < size; ++i) {
            if (a[i] == a[index]) {
                ++count;
            } else {
                --count;
            }
            if (count == 0) {
                count = 1;
                index = i;
            }
        }
        count = 0;
        for (int i = 0; i < size; ++i) {
            if (a[index] == a[i]) {
                ++count;
            }
        }
        if (count > size / 2) {
            return a[index];
        }
        return -1;
    }

}





















