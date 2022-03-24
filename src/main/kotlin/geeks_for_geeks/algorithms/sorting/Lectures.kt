package geeks_for_geeks.algorithms.sorting

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

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

/**
 * Performs stable partition of the part of the array [arr] from [start] to [end] indices,
 * so that all elements less than or equal to the element at [pivot] index are to the left of the [pivot],
 * and all other elements are to the right of the [pivot].
 *
 * Time complexity O(N)
 * Space complexity O(N)
 *
 * This is an inefficient partition algo, but it is stable - the order of equal elements doesn't change.
 *
 * @param arr array to be partitioned
 * @param start index of the first element that is subject to partitioning
 * @param end index of the last element that is subject to partitioning
 * @param pivot index of the pivot element around which the partitioning is performed.
 *
 * @return index of the pivot element after partitioning.
 */
fun partitionStable(arr: IntArray, start: Int, end: Int, pivot: Int): Int {
    val tmp = IntArray(end - start + 1)
    var tmpIdx = 0
    for (i in start..end) {
        if (arr[i] < arr[pivot]) {
            tmp[tmpIdx++] = arr[i]
        }
    }

    for (i in start..end) {
        if (arr[i] == arr[pivot]) {
            tmp[tmpIdx++] = arr[i]
        }
    }
    // compute the index of the pivot element after partitioning
    val res = start + tmpIdx - 1
    for (i in start..end) {
        if (arr[i] > arr[pivot]) {
            tmp[tmpIdx++] = arr[i]
        }
    }
    for (i in start..end) {
        arr[i] = tmp[i - start]
    }
    return res
}

/**
 * Performs unstable partition of the part of the array [arr] from [start] to [end] indices,
 * so that all elements less than or equal to the element at [pivotIdx] index are to the left of the [pivotIdx],
 * and all other elements are to the right of the [pivotIdx].
 *
 * Time complexity O(N)
 * Space complexity O(1)
 *
 * @param arr array to be partitioned
 * @param start index of the first element that is subject to partitioning
 * @param end index of the last element that is subject to partitioning
 * @param pivotIdx index of the pivot element around which the partitioning is performed.
 *
 * @return index of the pivot element after partitioning.
 */
fun lomutoPartition(arr: IntArray, start: Int, end: Int, pivotIdx: Int): Int {
    swap(arr, pivotIdx, end)
    val pivot = arr[end]
    var smallerIdx = start - 1
    for (currIdx in start until end) {
        if (arr[currIdx] <= pivot) {
            ++smallerIdx
            swap(arr, smallerIdx, currIdx)
        }
    }
    swap(arr, smallerIdx + 1, end)
    return smallerIdx + 1
}

fun hoarePartition(arr: IntArray, start: Int, end: Int, pivotIdx: Int): Int {
    swap(arr, start, pivotIdx)
    val pivot = arr[start]
    var i = start - 1
    var j = end + 1
    while (true) {
        do {
            ++i
        } while (arr[i] < pivot)
        do {
            --j
        } while (arr[j] > pivot)
        if (j <= i) {
            return j
        }
        swap(arr, i, j)
    }
}

fun quickSortLomuto(arr: IntArray) {
    quickSortLomutoRecursive(arr, 0, arr.lastIndex)
}

private fun quickSortLomutoRecursive(arr: IntArray, start: Int, end: Int) {
    if (start < end) {
        val currentPivot = selectPivot(start, end)
        val newPivot = lomutoPartition(arr, start, end, currentPivot)
        quickSortLomutoRecursive(arr, start, newPivot - 1)
        quickSortLomutoRecursive(arr, newPivot + 1, end)
    }
}

fun quickSortHoare(arr: IntArray) {
    quickSortHoareRecursive(arr, 0, arr.lastIndex)
}

private fun quickSortHoareRecursive(arr: IntArray, start: Int, end: Int) {
    if (start < end) {
        val currentPivot = selectPivot(start, end)
        val newPivot = hoarePartition(arr, start, end, currentPivot)
        quickSortHoareRecursive(arr, start, newPivot)
        quickSortHoareRecursive(arr, newPivot + 1, end)
    }
}

private fun selectPivot(start: Int, end: Int): Int {
    return Random.nextInt(start, end)
}

/**
 * Returns the k-th smallest element in the given array of distinct integers.
 * [k] must be within the range (0 until [arr.size]).
 *
 * This algorithm is called QuickSelect.
 * On average its Time Complexity is O(n).
 * Worst case Time Complexity is O(n^2).
 * It modifies the array.
 */
fun kThSmallestElement(arr: IntArray, k: Int): Int {
    var left = 0
    var right = arr.lastIndex
    while (left <= right) {
        val curPivot = selectPivot(left, right)
        val pivot = lomutoPartition(arr, left, right, curPivot)
        if (pivot == k - 1) {
            return arr[pivot]
        } else if (pivot > k - 1) {
            right = pivot - 1
        } else {
            left = pivot + 1
        }
    }
    throw IllegalArgumentException("K should be within the range of the array")
}

/**
 * Gets the minimum difference between a maximum and a minimum element
 * in the subarray of [m] elements.
 * If [m] is greater than the size of the array, return -1
 *
 * Time Complexity O(nLogn)
 */
fun minimumDifference(arr: IntArray, m: Int): Int {
    if (m > arr.size) {
        return -1
    }
    quickSortHoare(arr)
    var res = arr[m - 1] - arr[0]
    var i = 1
    while ((i + m - 1) < arr.size) {
        res = min(res, arr[i + m - 1] - arr[i])
        ++i
    }
    return res
}

/**
 * Segregates the array so that all elements that don't satisfy the predicate,
 * go first, and then go elements that satisfy the predicate.
 */
fun segregateArrayByPredicate(array: IntArray, predicate: (Int) -> Boolean) {
    var i = -1
    var j = array.size
    while (true) {
        do {
            ++i
        } while (!predicate(array[i]))
        do {
            --j
        } while (predicate(array[j]))
        if (i >= j) {
            return
        }
        swap(array, i, j)
    }
}

/**
 * Segregates the array so that all elements that satisfy the first predicate
 * go first, all the elements that satisfy the second predicate go last,
 * and elements that don't satisfy the predicates, go in between.
 *
 * E.g. input = [0, 1, 1, 2, 0, 1]
 * firstPredicate = elem < 1
 * secondPredicate = elem > 1
 * result = [0, 0, 1, 1, 1, 2]
 */
fun segregateArrayByTwoPredicates(
    arr: IntArray,
    firstPredicate: (Int) -> Boolean,
    secondPredicate: (Int) -> Boolean
) {
    var low = 0
    var mid = 0
    var high = arr.lastIndex
    while (mid <= high) {
        if (firstPredicate(arr[mid])) {
            swap(arr, low, mid)
            ++low
            ++mid
        } else if (!secondPredicate(arr[mid])) {
            ++mid
        } else {
            swap(arr, mid, high)
            --high
        }
    }
}

fun minDiffInArray(arr: IntArray): Int {
    quickSortHoare(arr)
    var res = Int.MAX_VALUE
    for (i in 1 until arr.size) {
        res = min(res, abs(arr[i] - arr[i - 1]))
    }
    return res
}

data class Interval(val start: Int, val end: Int)

fun mergeOverlappingIntervals(arr: MutableList<Interval>): List<Interval> {
    // step 1. sort in increasing order by start time
    arr.sortBy { it.start }
    var idx = 0
    for (i in 1 until arr.size) {
        if (overlapIntervals(arr[idx], arr[i])) {
            arr[idx] = mergeTwoIntervals(arr[idx], arr[i])
        } else {
            ++idx
            arr[idx] = arr[i]
        }
    }
    return arr.subList(0, idx + 1)
}

fun overlapIntervals(first: Interval, second: Interval): Boolean {
    return if (first.start > second.start) {
        first.start in second.start..second.end
    } else {
        second.start in first.start..first.end
    }
}

fun mergeTwoIntervals(first: Interval, second: Interval): Interval {
    return Interval(
        start = min(first.start, second.start),
        end = max(first.end, second.end)
    )
}

/**
 * Given two arrays with arrival times and departure times (from 0 to 2359
 * aka minutes of the day), count the maximum number of people present at any
 * moment in time.
 *
 * Time complexity O(N*Log(N))
 */
fun countMaxNumberOfGuests(arrivals: IntArray, departures: IntArray): Int {
    arrivals.sort()
    departures.sort()
    // initial setup
    // the first guest
    var arrivalIdx = 1
    // no one has left yet
    var departureIdx = 0
    // initially there's one guest
    var maxGuests = 1
    var currentGuests = 1
    while (arrivalIdx < arrivals.size && departureIdx < departures.size) {
        if (arrivals[arrivalIdx] <= departures[departureIdx]) {
            ++currentGuests
            ++arrivalIdx
        } else {
            --currentGuests
            ++departureIdx
        }
        maxGuests = max(maxGuests, currentGuests)
    }
    return maxGuests
}


/**
 * In place sorting algorithm. Unstable.
 * Efficient in terms of memory writes.
 * Allows no duplicates in the array.
 *
 * Time Complexity O(N^2)
 */
fun cycleSortNoDuplicates(arr: IntArray) {
    for (cycleStart in 0 until arr.lastIndex) {
        var item = arr[cycleStart]
        var position = cycleStart
        for (i in cycleStart + 1 until arr.size) {
            if (arr[i] < item) {
                position++
            }
        }
        var tmp = item
        item = arr[position]
        arr[position] = tmp
        while (position != cycleStart) {
            position = cycleStart
            for (j in cycleStart + 1 until arr.size) {
                if (arr[j] < item) {
                    position++
                }
            }
            tmp = item
            item = arr[position]
            arr[position] = tmp
        }
    }
}




















