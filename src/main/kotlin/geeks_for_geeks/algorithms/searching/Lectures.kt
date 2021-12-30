package geeks_for_geeks.algorithms.searching

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * Searches for a [target] in an array sorted in increasing order.
 *
 * @return index of any element equal to [target] or -1 if none.
 */
fun binarySearch(arr: IntArray, target: Int): Int {
    var start = 0
    var end = arr.lastIndex
    while (start <= end) {
        val medium = start + (end - start) / 2
        if (arr[medium] == target) {
            return medium
        }
        if (arr[medium] < target) {
            start = medium + 1
        } else {
            end = medium - 1
        }
    }
    return -1
}

fun binarySearchRecursive(arr: IntArray, target: Int): Int {
    return binarySearchRecursiveHelper(arr, target, 0, arr.lastIndex)
}

private fun binarySearchRecursiveHelper(arr: IntArray, target: Int, start: Int, end: Int): Int {
    if (start > end) return -1
    val middle = start + (end - start) / 2
    if (arr[middle] == target) return middle
    return if (arr[middle] < target) {
        binarySearchRecursiveHelper(arr, target, middle + 1, end)
    } else {
        binarySearchRecursiveHelper(arr, target, start, middle - 1)
    }
}

fun firstOccurrenceInSortedArray(arr: IntArray, target: Int): Int {
    var start = 0
    var end = arr.lastIndex
    while (start <= end) {
        val mid = start + (end - start) / 2
        if (arr[mid] == target) {
            if (mid > 0 && arr[mid - 1] == target) {
                end = mid
            } else {
                return mid
            }
        } else
            if (arr[mid] < target) {
                start = mid + 1
            } else {
                end = mid - 1
            }
    }
    return -1
}

fun lastOccurrenceInSortedArray(arr: IntArray, target: Int): Int {
    var start = 0
    var end = arr.lastIndex
    while (start <= end) {
        val mid = start + (end - start) / 2
        if (arr[mid] == target) {
            if (mid < arr.lastIndex && arr[mid + 1] == target) {
                start = mid + 1
            } else {
                return mid
            }
        } else
            if (arr[mid] < target) {
                start = mid + 1
            } else {
                end = mid - 1
            }
    }
    return -1
}

/**
 * Time complexity is O(logN + k) where k is the number of occurrences of
 * [target]. In the worst case it is = O(N) when the number of occurrences of
 * [target] == arr.size.
 */
fun countOccurrencesInSortedArray(arr: IntArray, target: Int): Int {
    var start = 0
    var end = arr.lastIndex
    while (start <= end) {
        val mid = start + (end - start) / 2
        if (arr[mid] == target) {
            var result = 1
            var toLeft = mid - 1
            while (toLeft >= 0 && arr[toLeft] == target) {
                ++result
                --toLeft
            }
            var toRight = mid + 1
            while (toRight <= arr.lastIndex && arr[toRight] == target) {
                ++result
                ++toRight
            }
            return result
        }
        if (arr[mid] < target) {
            start = mid + 1
        } else {
            end = mid - 1
        }
    }
    return 0
}

fun countOccurrencesInSortedArrayEfficient(arr: IntArray, target: Int): Int {
    val first = firstOccurrenceInSortedArray(arr, target)
    if (first == -1) {
        return 0
    }
    val last = lastOccurrenceInSortedArray(arr, target)
    return (last - first + 1)
}

fun countOnesInBinarySortedArray(arr: IntArray): Int {
    val first = firstOccurrenceInSortedArray(arr, 1)
    if (first == -1) {
        return 0
    }
    return arr.size - first
}

/**
 * Time complexity O(sqrt(N))
 */
fun squareRootFloorNaive(num: Int): Int {
    var i = 1
    while (i * i <= num) {
        ++i
    }
    return i - 1
}

/**
 * Time complexity O(logN)
 */
fun squareRootFloorEfficient(num: Int): Int {
    var start = 1
    var end = num
    var answer = -1
    while (start <= end) {
        val mid = start + (end - start) / 2
        val midSquare = mid * mid
        if (midSquare == num) {
            return mid
        }
        if (midSquare < num) {
            answer = mid
            start = mid + 1
        } else {
            end = mid - 1
        }
    }
    return answer
}

/**
 * Unbounded binary search algorithm.
 */
fun searchInInfiniteArray(arr: IntArray, target: Int): Int {
    if (arr[0] == target) return 0
    var i = 1
    // still need to check for array bounds, because arrays are
    // finite in kotlin
    while (i < arr.size && arr[i] < target) {
        i *= 2
    }
    if (i >= arr.size) return -1
    if (arr[i] == target) return i
    var start = i / 2 + 1
    var end = i - 1
    while (start <= end) {
        val mid = start + (end - start) / 2
        if (arr[mid] == target) return mid
        if (arr[mid] < target) {
            start = mid + 1
        } else {
            end = mid - 1
        }
    }
    return -1
}

fun searchInSortedRotatedArray(array: IntArray, target: Int): Int {
    var start = 0
    var end = array.lastIndex
    while (start <= end) {
        val mid = start + (end - start) / 2
        if (array[mid] == target) return mid
        // determine which half of the array is sorted
        if (array[start] < array[mid]) { // left half is sorted
            if (target >= array[start] && target < array[mid]) { // target is in this range start..mid
                end = mid - 1
            } else { // target is not in the range start..mid
                start = mid + 1
            }
        } else { // right half is sorted
            if (target > array[mid] && target <= array[end]) { // target is in range mid..end
                start = mid + 1
            } else { // target is not in range mid..end
                end = mid - 1
            }
        }
    }
    return -1
}

/**
 * An element is called a peak element if it is not smaller than
 * its neighbours.
 *
 * Here we can apply a binary search, because the condition states,
 * that a peak element is greater than or equal to its neighbours.
 * It means that after finding the middle element in the array - step 1
 * in binary search, we can determine if it is peak or not, and if not
 * then we can find an element that is greater than middle element.
 * That element will be either to the left or to the right of the middle
 * element. And therefore we can safely say that there will be a peak
 * element on the side that contains an element that is greater than the middle one.
 */
fun findPeakElementInArray(arr: IntArray): Int {
    var start = 0
    var end = arr.lastIndex
    while (start <= end) {
        val middle = start + (end - start) / 2
        if ((middle == 0 || arr[middle - 1] <= arr[middle]) &&
            (middle == arr.lastIndex || arr[middle + 1] <= arr[middle])
        ) {
            return arr[middle]
        }
        if (middle > 0 && arr[middle - 1] >= arr[middle]) {
            end = middle - 1
        } else {
            start = middle + 1
        }
    }
    // unreachable
    throw IllegalStateException(
        "This should never happen. " +
                "There's always a peak element in an array of integers"
    )
}

/**
 * Given an unsorted array of integers and a number x, find if there's
 * a pair of elements in the array whose sum is equal to x.
 * @return a pair of indices, pointing to the numbers, whose sum == x.
 *         if there's no such elements, return a pair containing -1, -1.
 */
fun findPairWithSumEqualToXInUnsortedArray(arr: IntArray, x: Int): Pair<Int, Int> {
    val map = hashMapOf<Int, Pair<Int, Int>>()
    for (i in arr.indices) {
        map[x - arr[i]] = i to arr[i]
    }
    var first = -1
    var second = -1
    var firstFound = false
    var secondFound = false
    for (i in arr.indices) {
        val pair = map[arr[i]]
        if (pair != null) {
            if (!firstFound) {
                firstFound = true
                first = pair.first
            } else if (!secondFound) {
                secondFound = true
                second = pair.first
            }
        }
        if (firstFound && secondFound) break
    }
    if (firstFound && secondFound) {
        return first to second
    }
    return -1 to -1
}

/**
 * Use two pointers: left and right.
 * Step 1. Compare the sum of elements at indices left and right.
 *   - if equal to x, then return pair of left to right
 *   - if greater than x, then move pointer that points to the greater of the elements
 *   - if smaller than x, then move pointer that points to the smaller of the elements
 */
fun findPairWithSumEqualToXInSortedArray(arr: IntArray, x: Int): Pair<Int, Int> {
    var left = 0
    var right = arr.lastIndex
    while (left < right) {
        val sum = arr[left] + arr[right]
        if (sum == x) {
            return left to right
        }
        if (sum > x) {
            --right
        } else {
            ++left
        }
    }
    return -1 to -1
}

/**
 * Given a sorted array of integers and number x, find if there's a
 * triplet with sum = x.
 *
 * @return first triplet in the array whose sum = x or triplet of -1, -1, -1
 */
fun findTripletWithSumInSortedArray(arr: IntArray, x: Int): Triple<Int, Int, Int> {
    for (i in arr.indices) {
        val pair = findPairSum(arr, x - arr[i], i + 1, arr.lastIndex)
        if (pair.first != -1) {
            return Triple(i, pair.first, pair.second)
        }
    }
    return Triple(-1, -1, -1)
}

private fun findPairSum(arr: IntArray, x: Int, from: Int, to: Int): Pair<Int, Int> {
    var left = from
    var right = to
    while (left < right) {
        val sum = arr[left] + arr[right]
        if (sum == x) {
            return left to right
        }
        if (sum > x) {
            --right
        } else {
            ++left
        }
    }
    return -1 to -1
}

/**
 * Given two sorted arrays, find the median of them.
 *
 * Partition the arrays into two parts, so that the number of elements in first parts of
 * the arrays is equal to the number of elements in the second parts of the arrays.
 * to do this we use binary search for the lesser array to find the middle point,
 * and use formula (first.size + second.size + 1) /  2 - firstMiddle to find
 * partition point for the larger array. If the number of elements in both
 * arrays is even, then we have equal number in both parts, else the left part
 * will be larger by one element.
 * start of right part in the second array
 * check if the last element in the left part of the first array is smaller than
 * the first element in the right part of the second array and the last element in
 * the first part of the second array is smaller than the first element in the
 * right part of the first array
 */
fun findMedianOfTwoSortedArrays(first: IntArray, second: IntArray): Double {
    return if (first.size <= second.size) {
        findMedianHelper(first, second)
    } else {
        findMedianHelper(second, first)
    }
}

private fun findMedianHelper(first: IntArray, second: IntArray): Double {
    val n1 = first.size
    val n2 = second.size
    var start = 0
    var end = n1

    while (start <= end) {
        val partitionFirst = start + (end - start ) /2
        val partitionSecond = ((n1 + n2 + 1) / 2) - partitionFirst

        val minFirst = if (partitionFirst == n1) Int.MAX_VALUE else first[partitionFirst]
        val maxFirst = if (partitionFirst == 0) Int.MIN_VALUE else first[partitionFirst - 1]
        val minSecond = if (partitionSecond == n2) Int.MAX_VALUE else second[partitionSecond]
        val maxSecond = if (partitionSecond == 0) Int.MIN_VALUE else second[partitionSecond - 1]
        if (maxFirst <= minSecond && maxSecond <= minFirst) {
            if (!odd(first, second)) {
                return (max(maxFirst, maxSecond) + min(minFirst, minSecond)) / 2.0
            } else {
                return max(maxFirst, maxSecond).toDouble()
            }
        }
        else if (maxFirst > minSecond) {
            end = partitionFirst - 1
        } else {
            start = partitionFirst + 1
        }
    }
   // unreachable
    throw IllegalStateException("There's always a median in two sorted arrays!")
}

private fun odd(first: IntArray, second: IntArray): Boolean {
    return (first.size + second.size) and 1 != 0
}

/**
 * Function to compare doubles.
 */
fun Double.equalsDelta(other: Double) =
    abs(this - other) < max(Math.ulp(this), Math.ulp(other)) * 2





















