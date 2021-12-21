package geeks_for_geeks.algorithms.arrays;

import java.lang.reflect.Array;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Solutions {
    public void insertAtIndex(int arr[], int sizeOfArray, int index, int element) {
        //Your code here, Geeks
        for (int i = sizeOfArray - 1; i > index; --i) {
            arr[i] = arr[i - 1];
        }
        arr[index] = element;
    }

    //Function to find median of the array elements.
    public int median(int A[], int N) {

        Arrays.sort(A);

        //Your code here
        //If median is fraction then conver it to integer and return
        if ((N & 1) == 0) {
            int l = N / 2 - 1;
            int r = N / 2;
            return (A[l] + A[r]) / 2;
        }
        return A[N / 2];
    }

    //Function to find median of the array elements.
    public int mean(int A[], int N) {
        //Your code here
        int sum = 0;
        for (int i = 0; i < N; ++i) {
            sum += A[i];
        }
        return sum / N;
    }

    public int majorityWins(int arr[], int n, int x, int y) {
        // code here
        int countX = 0;
        int countY = 0;
        for (int elem :
                arr) {
            if (elem == x) {
                ++countX;
            } else if (elem == y) {
                ++countY;
            }
        }
        if (countX == countY) {
            return Math.min(x, y);
        }
        if (countX > countY) {
            return x;
        } else {
            return y;
        }
    }

    // Function to find largest and second largest element in the array
    public static ArrayList<Integer> largestAndSecondLargest(int sizeOfArray, int arr[]) {
        ArrayList<Integer> result = new ArrayList<>(2);
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int i = 0; i < sizeOfArray; ++i) {
            if (arr[i] > max) {
                secondMax = max;
                max = arr[i];
            } else if (arr[i] > secondMax && arr[i] != max) {
                secondMax = arr[i];
            }
        }
        if (secondMax == max) {
            secondMax = -1;
        }
        result.add(max);
        result.add(secondMax);

        return result;
    }

    // Function to find maximum for every adjacent pairs in the array.
    static void maximumAdjacent(int sizeOfArray, int arr[]) {
        if (sizeOfArray == 0) return;
        if (sizeOfArray == 1) {
            System.out.println(arr[0]);
        }
        int currentMax = arr[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < sizeOfArray; ++i) {
            currentMax = Math.max(currentMax, arr[i]);
            sb.append(currentMax);
            sb.append(' ');
            currentMax = arr[i];
        }
        System.out.print(sb);
    }

    //Function to reverse every sub-array group of size k.
    void reverseInGroups(ArrayList<Integer> arr, int n, int k) {

        if (k == 1)
            return;
        for (int i = 0; i < n; i += k) {
            int start = i;
            int end = Math.min(start + k - 1, n - 1);
            reverse(arr, start, end);
        }
    }

    private void reverse(ArrayList<Integer> arr, int start, int end) {
        while (start < end) {
            Collections.swap(arr, start, end);
            ++start;
            --end;
        }
    }

    //Function to rotate an array by d elements in counter-clockwise direction.
    static void rotateArr(int arr[], int d, int n) {
        if (arr.length == d) return;
        d = d % n;
        reverseArray(arr, 0, d - 1);
        reverseArray(arr, d, n - 1);
        reverseArray(arr, 0, n - 1);
    }

    private static void reverseArray(int[] arr, int start, int end) {
        while (start < end) {
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            ++start;
            --end;
        }
    }

    //Function to find minimum adjacent difference in a circular array.
    // arr[]: input array
    // n: size of array
    public static int minAdjDiff(int arr[], int n) {

        // Your code here
        if (n == 1) return arr[0];
        int min = Math.abs(arr[0] - arr[1]);
        for (int i = 2; i < n; ++i) {
            int currentMin = Math.abs(arr[i - 1] - arr[i]);
            min = Math.min(min, currentMin);
        }
        int lastMin = Math.abs(arr[n - 1] - arr[0]);
        min = Math.min(min, lastMin);
        return min;
    }

    public static int maxOccured(int L[], int R[], int n, int maxx) {
        int[] prefixSum = new int[maxx + 2];
        for (int i = 0; i < n; ++i) {
            prefixSum[L[i]]++;
            prefixSum[R[i] + 1]--;
        }
        int max = prefixSum[0];
        int res = 0;
        for (int j = 1; j <= maxx; ++j) {
            prefixSum[j] += prefixSum[j - 1];
            if (prefixSum[j] > max) {
                max = prefixSum[j];
                res = j;
            }
        }
        return res;
    }

    // arr: input array
    // n: size of the array
    //Function to sort the array into a wave-like array.
    public static void convertToWave(int arr[], int n) {

        // Your code here
        if (n == 1) return;
        int left = 0;
        int right = 1;
        int index;
        for (index = 2; index < n; index += 2) {
            swap(arr, left, right);
            left = index;
            right = left + 1;
        }
        if (index == n) {
            left = n - 2;
            right = n - 1;
            swap(arr, left, right);
        }
    }

    private static void swap(int[] arr, int from, int to) {
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }

    //Function to count the frequency of all elements from 1 to N in the array.
    public static void frequencyCount(int arr[], int N, int P) {
        int i = 0;
        while (i < N) {
            if (arr[i] <= 0) {
                ++i;
                continue;
            }
            int idx = arr[i] - 1;

            if (idx > N - 1) {
                arr[i] = 0;
                ++i;
                continue;
            }
            if (arr[idx] > 0) {
                arr[i] = arr[idx];
                arr[idx] = -1;
            } else {
                arr[idx]--;
                arr[i] = 0;
                ++i;
            }
        }
        for (i = 0; i < N; ++i) {
            arr[i] = arr[i] * -1;
        }
    }

    // a: input array
    // n: size of array
    // Function to find equilibrium point in the array.
    public static int equilibriumPoint(long arr[], int n) {
        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += arr[i];
        }
        long leftSum = 0;
        int index = -1;
        for (int j = 0; j < n; ++j) {
            if (leftSum == totalSum - arr[j]) {
                index = j;
                break;
            }
            leftSum += arr[j];
            totalSum -= arr[j];
        }
        return index;
    }

    //Function to find the leaders in the array.
    static ArrayList<Integer> leaders(int arr[], int n) {
        // Your code here
        ArrayList<Integer> result = new ArrayList<>();
        int currentLeader = arr[n - 1];
        result.add(currentLeader);
        for (int i = n - 2; i >= 0; --i) {
            if (arr[i] >= currentLeader) {
                currentLeader = arr[i];
                result.add(currentLeader);
            }
        }
        result.sort(Comparator.reverseOrder());
        return result;
    }

    //Function to find the smallest positive number missing from the array.
    static int missingNumber(int arr[], int size) {
        return findMissingInSegregatedArray(arr, size, segregate(arr, size));
    }

    private static int segregate(int[] arr, int size) {
        int result = 0;
        for (int i = 0; i < size; ++i) {
            if (arr[i] <= 0) {
                if (i != result) {
                    int tmp = arr[i];
                    arr[i] = arr[result];
                    arr[result] = tmp;
                }
                ++result;
            }
        }
        return result;
    }

    private static int findMissingInSegregatedArray(int[] arr, int size, int startOfPositive) {
        int diff = size - startOfPositive;
        for (int i = startOfPositive; i < size; ++i) {
            int x = Math.abs(arr[i]);
            if (x - 1 < diff && arr[x + startOfPositive - 1] > 0) {
                arr[x + startOfPositive - 1] = -arr[x + startOfPositive - 1];
            }
        }
        for (int i = startOfPositive; i < size; i++) {
            if (arr[i] > 0) {
                return i - startOfPositive + 1;
            }
        }
        return diff + 1;
    }

    // temp: input array
    // n: size of array
    //Function to rearrange  the array elements alternately.
    public static void rearrange(int arr[], int n) {
        int maxIndex = n - 1;
        int minIndex = 0;
        int maxElem = arr[maxIndex] + 1;
        for (int i = 0; i < n; ++i) {
            if ((i & 1) == 0) { // even
                arr[i] += (arr[maxIndex--] % maxElem) * maxElem;
            } else { // odd
                arr[i] += (arr[minIndex++] % maxElem) * maxElem;
            }
        }
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] / maxElem;
        }
    }

    // arr: input array
    // n: size of array
    //Function to rearrange an array so that arr[i] becomes arr[arr[i]]
    //with O(1) extra space.
    static void arrange(long arr[], int n) {
        for (int i = 0; i < n; ++i) {
            arr[i] += (arr[(int) arr[i]] % n) * n;
        }
        for (int i = 0; i < n; i++) {
            arr[i] /= n;
        }
    }

    // A[]: input array
    // N: size of array
    // Function to find the maximum index difference.
    static int maxIndexDiff(int A[], int N) {
        int[] rightMax = new int[N];

        rightMax[N - 1] = A[N - 1];
        for (int i = N - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], A[i]);
        }
        int maxDiff = -1;
        int i = 0;
        int j = 0;
        while (i < N && j < N) {
            if (rightMax[j] >= A[i]) {
                maxDiff = Math.max(maxDiff, j - i);
                ++j;
            } else {
                ++i;
            }
        }
        return maxDiff;
    }

    // arr: input array
    // n: size of array
    // Function to find the trapped water between the blocks.
    static long trappingWater(int arr[], int n) {
        if (arr.length == 0 || arr.length == 1 || arr.length == 2) {
            return 0;
        }
        // Your code here
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = arr[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
        }
        rightMax[n - 1] = arr[n - 1];
        for (int j = n - 2; j >= 0; --j) {
            rightMax[j] = Math.max(rightMax[j + 1], arr[j]);
        }
        long res = 0;
        for (int k = 0; k < n; ++k) {
            res += (Math.min(leftMax[k], rightMax[k]) - arr[k]);
        }
        return res;
    }

    //Function to find the days of buying and selling stock for max profit.
    ArrayList<ArrayList<Integer>> stockBuySell(int A[], int n) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        for(int i  = 1; i < n; i++){
            ArrayList<Integer> temp = new ArrayList();
            if(A[i - 1] < A[i]){
                temp.add(i - 1);
                ++i;
                while (i < n) {
                    if (A[i - 1] <= A[i]) {
                        ++i;
                    } else {
                        break;
                    }
                }
                temp.add(i - 1);
                ans.add(temp);
            }
        }

        return ans;
    }
}























































