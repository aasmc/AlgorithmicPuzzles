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
        if (!swapped) {
            break
        }
    }
}

/**
 * Sorts a given array of integers in place.
 * Unstable sort.
 * Time complexity: O(n^2)
 */
fun selectionSort(arr: IntArray) {
    for (i in 0 until arr.lastIndex) {
        var minIndex = i
        for (j in i + 1 until arr.size) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j
            }
        }
        swap(arr, minIndex, i)
    }
}

/**
 * Sorts a given array of integers in place.
 * Stable sort.
 * Time complexity: O(n^2)
 */
fun insertionSort(arr: IntArray) {
    for (i in 1..arr.lastIndex) {
        val key = arr[i]
        var sortedIndex = i - 1
        while (sortedIndex >= 0 && arr[sortedIndex] > key) {
            arr[sortedIndex + 1] = arr[sortedIndex]
            --sortedIndex
        }
        arr[sortedIndex + 1] = key
    }
}


fun mergeSortedArrays(first: IntArray, second: IntArray): IntArray {
    val result = IntArray(first.size + second.size)
    var firstIdx = 0
    var secondIdx = 0
    var currentIndex = 0
    while (firstIdx < first.size && secondIdx < second.size) {
        if (first[firstIdx] <= second[secondIdx]) {
            result[currentIndex++] = first[firstIdx++]
        } else {
            result[currentIndex++] = second[secondIdx++]
        }
    }
    while (firstIdx < first.size) {
        result[currentIndex++] = first[firstIdx++]
    }

    while (secondIdx < second.size) {
        result[currentIndex++] = second[secondIdx++]
    }
    return result
}


fun merge(arr: IntArray, low: Int, mid: Int, high: Int) {
    val left = IntArray(mid - low + 1) { index ->
        arr[index + low]
    }
    val right = IntArray(high - mid) { index ->
        arr[index + mid + 1]
    }
    var firstIdx = 0
    var secondIdx = 0
    var currentIdx = low
    while (firstIdx < left.size && secondIdx < right.size) {
        if (left[firstIdx] <= right[secondIdx]) {
            arr[currentIdx++] = left[firstIdx++]
        } else {
            arr[currentIdx++] = right[secondIdx++]
        }
    }

    while (firstIdx < left.size) {
        arr[currentIdx++] = left[firstIdx++]
    }

    while (secondIdx < right.size) {
        arr[currentIdx++] = right[secondIdx++]
    }
}

/**
 * Sorts a given array of integers.
 * Stable sort.
 * Time complexity: O(n^2)
 * Space complexity O(n)
 */
fun mergeSort(arr: IntArray) {
    mergeSortHelper(arr, 0, arr.lastIndex)
}

private fun mergeSortHelper(arr: IntArray, low: Int, high: Int) {
    if (high > low) {
        val mid = low + (high - low) / 2
        mergeSortHelper(arr, low, mid)
        mergeSortHelper(arr, mid + 1, high)
        merge(arr, low, mid, high)
    }
}

/**
 * Finds intersection of two sorted arrays.
 * @return list of common elements in the input arrays. the list has no duplicates.
 *          if the arrays have no common elements, it returns an empty list.
 */
fun intersectionOfTwoSortedArrays(first: IntArray, second: IntArray): List<Int> {
    val result = mutableListOf<Int>()
    var firstIdx = 0
    var secondIdx = 0
    while (firstIdx < first.size && secondIdx < second.size) {
        // don't process duplicates
        if (firstIdx > 0 && first[firstIdx] == first[firstIdx - 1]) {
            ++firstIdx
            continue
        }

        if (first[firstIdx] == second[secondIdx]) {
            result.add(first[firstIdx])
            firstIdx++
            secondIdx++
        }

        if (first[firstIdx] < second[secondIdx]) {
            firstIdx++
        } else if (first[firstIdx] > second[secondIdx]) {
            secondIdx++
        }
    }
    return result
}

/**
 * Finds a union of two sorted arrays.
 * @return list of all elements that are present in the arrays without duplicates
 *          and in sorted order.
 */
fun unionOfTwoSortedArrays(first: IntArray, second: IntArray): List<Int> {
    var firstIdx = 0
    var secondIdx = 0
    val result = mutableListOf<Int>()
    while (firstIdx < first.size && secondIdx < second.size) {
        // handle duplicates in first array
        if (firstIdx > 0 && first[firstIdx] == first[firstIdx - 1]) {
            ++firstIdx
            continue
        }
        // handle duplicates in second array
        if (secondIdx > 0 && second[secondIdx] == second[secondIdx - 1]) {
            ++secondIdx
            continue
        }
        if (first[firstIdx] < second[secondIdx]) {
            result.add(first[firstIdx++])
        } else if (first[firstIdx] > second[secondIdx]) {
            result.add(second[secondIdx++])
        } else {
            result.add(first[firstIdx++])
            ++secondIdx
        }
    }
    while (firstIdx < first.size) {
        if (firstIdx > 0 && first[firstIdx] != first[firstIdx - 1]) {
            result.add(first[firstIdx++])
        }
    }

    while (secondIdx < second.size) {
        if (secondIdx > 0 && second[secondIdx] != second[secondIdx - 1]) {
            result.add(second[secondIdx++])
        }
    }
    return result
}


/**
 * Counts the number of inversions in an array.
 * An inversion is when index i < index j, but
 * arr[i] > arr[j].
 *
 * This solution is based on merge sort. We count inversions during the merge part
 * of the sort.
 *
 * Time complexity O(N * LogN)
 */
fun countInversions(arr: IntArray): Int {
    return countInversionHelper(arr, 0, arr.lastIndex)
}

private fun countInversionHelper(arr: IntArray, left: Int, right: Int): Int {
    var res = 0
    if (left < right) {
        val mid = left + (right - left) / 2
        res += countInversionHelper(arr, left, mid)
        res += countInversionHelper(arr, mid + 1, right)
        res += countAndMerge(arr, left, mid, right)
    }
    return res
}

fun countAndMerge(arr: IntArray, left: Int, mid: Int, right: Int): Int {
    val leftArr = IntArray(mid - left + 1) { index ->
        arr[left + index]
    }
    val rightArr = IntArray(right - mid) { index ->
        arr[mid + index + 1]
    }
    var lIdx = 0
    var rIdx = 0
    var curIdx = left
    var res = 0
    while (lIdx < leftArr.size && rIdx < rightArr.size) {
        if (leftArr[lIdx] <= rightArr[rIdx]) {
            arr[curIdx++] = leftArr[lIdx++]
        } else {
            arr[curIdx++] = rightArr[rIdx++]
            // this is the key line of the counting part. We know that element at
            // index lIdx in left array is greater than element at index rIdx in right array,
            // this means that ALL elements in left array that go after lIdx are greater than
            // element at index rIdx in right array. Therefore, we may count the number of
            // inversions like that: sizeOfArray - indexOfGreaterElement
            res += leftArr.size - lIdx
        }
    }
    while (lIdx < leftArr.size) {
        arr[curIdx++] = leftArr[lIdx++]
    }
    while (rIdx < rightArr.size) {
        arr[curIdx++] = rightArr[rIdx++]
    }
    return res
}





























