package geeks_for_geeks.algorithms.sorting;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Solutions {

    private static void swap(int[] arr, int from, int to) {
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }

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
}
