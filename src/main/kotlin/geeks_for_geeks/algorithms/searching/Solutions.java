package geeks_for_geeks.algorithms.searching;

import java.lang.reflect.GenericDeclaration;

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
        while(low < high)
        {
            int mid = low + (high - low) / 2;
            if (arr[mid] == arr[high])
                high--;

            else if(arr[mid] > arr[high])
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
}





















