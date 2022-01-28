package geeks_for_geeks.algorithms.sorting

private fun swap(arr: IntArray, from: Int, to: Int) {
    val tmp = arr[from]
    arr[from] = arr[to]
    arr[to] = tmp
}

/**
 * Sorts a given array of integers in place.
 * Stable sort.
 * Time complexity: O(n^2)
 * This algorithm is optimized for arrays that are sorted or partially sorted.
 */
fun bubbleSort(arr: IntArray) {
    for (i in arr.indices) {
        // optimization for the case when the entire array or part of the array is sorted
        var swapped = false
        for (j in 0 until arr.size - i - 1) {
            if (arr[j] > arr[j + 1]) {
                swap(arr, j, j + 1)
                swapped = true
            }
        }
        if(!swapped) {
            break
        }
    }
}