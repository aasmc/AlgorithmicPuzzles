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




























