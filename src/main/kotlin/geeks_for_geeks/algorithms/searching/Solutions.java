package geeks_for_geeks.algorithms.searching;

import java.awt.*;
import java.lang.reflect.GenericDeclaration;
import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    static int leftIndex(int N, int arr[], int X) {
        int start = 0;
        int end = N - 1;
        while (start <= end) {
            int middle = start + (end - start) / 2;
            if (arr[middle] == X) {
                if (middle > 0 && arr[middle - 1] == X) {
                    end = middle;
                } else {
                    return middle;
                }
            } else {
                if (arr[middle] > X) {
                    end = middle - 1;
                } else {
                    start = middle + 1;
                }
            }
        }
        return -1;
    }

    // Function to find the peak element
    // arr[]: input array
    // n: size of array a[]
    public int peakElement(int[] arr, int n) {
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if ((mid == 0 || arr[mid - 1] <= arr[mid]) &&
                    (mid == n - 1 || arr[mid + 1] <= arr[mid])) {
                return mid;
            } else if (mid > 0 && arr[mid - 1] >= arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    // Function to find floor of x
    // arr: input array
    // n is the size of array
    static int findFloor(long arr[], int n, long x) {
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] > x) {
                end = mid - 1;
            } else {
                if (mid != n - 1 && arr[mid + 1] < x) {
                    start = mid + 1;
                } else {
                    return mid;
                }
            }
        }
        return -1;
    }

    //Function to find the minimum element in sorted and rotated array.
    static int minNumber(int arr[], int low, int high) {
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == arr[high])
                high--;

            else if (arr[mid] > arr[high])
                low = mid + 1;
            else
                high = mid;
        }
        return arr[high];
    }

    public int findMin(int[] nums) {
        // If the list has just one element then return that element.
        if (nums.length == 1) {
            return nums[0];
        }

        // initializing left and right pointers.
        int left = 0, right = nums.length - 1;

        // if the last element is greater than the first element then there is no rotation.
        // e.g. 1 < 2 < 3 < 4 < 5 < 7. Already sorted array.
        // Hence the smallest element is first element. A[0]
        if (nums[right] > nums[0]) {
            return nums[0];
        }

        // Binary search way
        while (right >= left) {
            // Find the mid element
            int mid = left + (right - left) / 2;

            // if the mid element is greater than its next element then mid+1 element is the smallest
            // This point would be the point of change. From higher to lower value.
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            // if the mid element is lesser than its previous element then mid element is the smallest
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }

            // if the mid elements value is greater than the 0th element this means
            // the least value is still somewhere to the right as we are still dealing with elements
            // greater than nums[0]
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                // if nums[0] is greater than the mid value then this means the smallest value is somewhere to
                // the left
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * You are given an array of N+2 integer elements. All elements of the
     * array are in range 1 to N. Also, all elements occur once except
     * two numbers which occur twice. Find the two repeating numbers.
     */
    public int[] twoRepeated(int arr[], int N) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        boolean firstFound = false;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[Math.abs(arr[i]) - 1] < 0) {
                if (firstFound) {
                    second = arr[i];
                } else {
                    firstFound = true;
                    first = arr[i];
                }
            } else {
                arr[Math.abs(arr[i]) - 1] = -arr[Math.abs(arr[i]) - 1];
            }
        }
        return new int[]{Math.abs(first), Math.abs(second)};
    }

    /**
     * You are given heights of consecutive buildings. You can move from the
     * roof of a building to the roof of next adjacent building. You need
     * to find the maximum number of consecutive steps you can put forward such
     * that you gain an increase in altitude with each step.
     */
    static int maxStep(int A[], int N) {
        if (A.length == 1) {
            return 0;
        }
        int result = 0;
        int currentSteps = 0;
        for (int i = 1; i < N; ++i) {
            if (A[i] > A[i - 1]) {
                currentSteps++;
            } else {
                result = Math.max(result, currentSteps);
                currentSteps = 0;
            }
        }
        result = Math.max(result, currentSteps);
        return result;
    }

    /**
     * Given an integer array representing the heights of N buildings, the
     * task is to delete N-2 buildings such that the water that can be trapped
     * between the remaining two building is maximum.
     * Note: The total water trapped between two buildings is gap
     * between them (number of buildings removed) multiplied by height of the
     * smaller building.
     */
    static int maxWater(int height[], int n) {
        if (height.length <= 2) {
            return 0;
        }

        int maxRes = 0;
        int start = 0;
        int end = n - 1;
        while (start < end) {
            if (height[start] < height[end]) {
                maxRes = Math.max(maxRes, height[start] * (end - start - 1));
                ++start;
            } else if (height[start] > height[end]) {
                maxRes = Math.max(maxRes, height[end] * (end - start - 1));
                --end;
            } else {
                maxRes = Math.max(maxRes, height[start] * (end - start - 1));
                --end;
                ++start;
            }
        }
        return maxRes;
    }

    /**
     * Given an array arr[] of N positive integers, where elements are consecutive
     * (sorted). Also, there is a single element which is repeating X (any variable)
     * number of times. Now, the task is to find the element which is repeated and
     * number of times it is repeated.
     * Note: If there's no repeating element, Return {-1,-1}.
     */
    public static Point findRepeating(Integer arr[], int n) {
        int repeating = -1;
        int count = -1;
        if (arr.length <= 1) {
            return new Point(repeating, count);
        }
        if (n - (arr[n - 1] - arr[0]) == 1) {
            return new Point(repeating, count);
        }
        int start = 0;
        int end = n - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] >= mid + arr[0]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        count = n - (arr[n - 1] - arr[0]);
        repeating = arr[start];
        return new Point(repeating, count);
    }

    /**
     * Given an array arr[] of size N and an element k.
     * The task is to find all elements in array that appear
     * more than n/k times.
     * <p>
     * Example 1:
     * Input:
     * N = 8
     * arr[] = {3,1,2,2,1,2,3,3}
     * k = 4
     * Output: 2
     * Explanation: In the given array, 3 and
     * 2 are the only elements that appears
     * more than n/k times.
     * <p>
     * Example 2:
     * Input:
     * N = 4
     * arr[] = {2,3,3,2}
     * k = 3
     * Output: 2
     * Explanation: In the given array, 3 and 2
     * are the only elements that appears more
     * than n/k times. So the count of elements
     * are 2.
     */
    public int countOccurence(int[] arr, int n, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(n);
        for (int num : arr) {
            map.compute(num, (key, v) -> v == null ? 1 : v + 1);
        }
        int border = n / k;
        int count = 0;
        for (int num : map.values()) {
            if (num > border) {
                ++count;
            }
        }
        return count;
    }

    public static int findPages(int[] A, int N, int M) {
        int high = 0;
        int low = Integer.MIN_VALUE;
        for (int j : A) {
            high += j;
            low = Math.max(low, j);
        }
        int result = -1;
        while (low <= high) {
            int mid = high + (low - high) / 2;
            if (isBookAllowed(A, M, mid)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    private static boolean isBookAllowed(int[] books, int students, int answer) {
        int sum = 0;
        int requiredStudents = 1;
        for (int pages : books) {
            if (sum + pages > answer) {
                sum = pages;
                ++requiredStudents;
            } else {
                sum += pages;
            }
        }
        return requiredStudents <= students;
    }

    static ArrayList<Integer> subarraySum(int[] arr, int n, int s) {
        int start = 0;
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += arr[i];
            while (sum >= s) {
                if (sum == s) {
                    return new ArrayList<>(List.of(start + 1, i + 1));
                }
                sum -= arr[start];
                ++start;
            }
        }
        return new ArrayList<>(List.of(-1));
    }

    public static int findMedian(int arr[], int n, int brr[], int m) {
        if (n <= m) {
            return findMedianHelper(arr, n, brr, m);
        } else {
            return findMedianHelper(brr, m, arr, n);
        }
    }

    private static int findMedianHelper(int[] arr, int n, int[] brr, int m) {
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int partitionFirst = start + (end - start) / 2;
            int partitionSecond = ((n + m + 1) / 2) - partitionFirst;

            int minFirst = (partitionFirst == n) ? Integer.MAX_VALUE : arr[partitionFirst];
            int maxFirst = (partitionFirst == 0) ? Integer.MIN_VALUE : arr[partitionFirst - 1];
            int minSecond = (partitionSecond == m) ? Integer.MAX_VALUE : brr[partitionSecond];
            int maxSecond = (partitionSecond == 0) ? Integer.MIN_VALUE : brr[partitionSecond - 1];

            if (maxFirst <= minSecond && maxSecond <= minFirst) {
                if ((n + m) % 2 == 0) {
                    return (Math.max(maxFirst, maxSecond) + Math.min(minFirst, minSecond)) / 2;
                } else {
                    return Math.max(maxFirst, maxSecond);
                }
            } else if (maxFirst > minSecond) {
                end = partitionFirst - 1;
            } else {
                start = partitionFirst + 1;
            }
        }
        return -1;
    }
}





















