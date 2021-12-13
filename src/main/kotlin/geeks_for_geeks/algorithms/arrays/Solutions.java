package geeks_for_geeks.algorithms.arrays;

import java.util.ArrayList;
import java.util.Arrays;

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
}

























