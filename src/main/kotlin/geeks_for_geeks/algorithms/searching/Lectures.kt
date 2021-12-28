package geeks_for_geeks.algorithms.searching

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
fun squareRootFloorNaive(num: Int) : Int {
    var i = 1
    while (i * i <= num) {
        ++i
    }
    return i - 1
}

/**
 * Time complexity O(logN)
 */
fun squareRootFloorEfficient(num: Int) : Int {
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

fun searchInInfiniteArray(arr: IntArray, target: Int): Int {
    if (arr[0] == target) return 0
    var i = 1
    // still need to check for array bounds, because arrays are
    // finite in kotlin
    while (i < arr.size && arr[i] < target) {
        i *= 2
    }
    if(i >= arr.size) return -1
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















