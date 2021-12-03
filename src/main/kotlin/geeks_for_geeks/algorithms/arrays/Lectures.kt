package geeks_for_geeks.algorithms.arrays

/**
 * Inserts an integer [elem] into array [arr] at specified index.
 * If the array is full, then no insertion happens.
 *
 * Here we distinguish between the size of the array and capacity of the array.
 *
 * Returns size of the array.
 */
fun insertIntoArray(
    arr: ArrayList<Int>,
    elem: Int,
    index: Int,
    size: Int,
    capacity: Int
): Int {
    if (size == capacity) {
        return size
    }
    for (i in size - 1 downTo index) {
        arr.add(i + 1, arr[i])
    }
    arr[index] = elem
    return size + 1
}

/**
 * Deletes an element from integer array [arr], if the element [elem]
 * is present in the array. Shifts all elements that are stored in the array
 * after the [elem] to one position towards the beginning of the array.
 *
 *
 * @return new size of the array.
 */
fun deleteFromArray(arr: ArrayList<Int>, elem: Int, size: Int): Int {
    var index = -1
    for (i in arr.indices) {
        if (arr[i] == elem) {
            index = i
        }
    }
    if (index == -1) {
        return size
    }
    for (i in index until size - 1) {
        arr[i] = arr[i + 1]
    }
    return size - 1
}

/**
 * Returns index of the second largest element in a given array.
 * If the array contains only one element, its index is returned.
 *
 * @return index of the second largest element in the array
 *         or -1 if all elements are the same
 * @throws IllegalArgumentException if the array is empty.
 */
fun secondLargestElementInArray(arr: IntArray): Int {
    if (arr.isEmpty()) {
        throw IllegalArgumentException("Array $arr is empty")
    }
    if (arr.size == 1) {
        return 0
    }
    var max = 0
    var secondMax = 0

    for (i in 1 until arr.size) {
        if (arr[i] > arr[max]) {
            secondMax = max
            max = i
        } else if (arr[i] > arr[secondMax]) {
            secondMax = i
        }
    }
    if (max == secondMax) {
        return -1
    }
    return secondMax
}

/**
 * Checks if a given array is sorted in ascending order.
 * Time complexity O(N).
 *
 * @return true if the array [arr] is sorted in ascending order
 *         false otherwise.
 * @throws IllegalArgumentException if the array is empty.
 */
fun checkIfSortedArray(arr: IntArray): Boolean {
    if (arr.isEmpty()) {
        throw IllegalArgumentException("Array $arr is empty.")
    }
    var checkIndex = 0
    for (i in 1 until arr.size) {
        if (arr[checkIndex] > arr[i]) {
            return false
        }
        checkIndex = i
    }
    return true
}

fun reverseArray(arr: IntArray) {
    var left = 0
    var right = arr.lastIndex
    while (left < right) {
        swap(arr, left++, right--)
    }
}

private fun swap(arr: IntArray, left: Int, right: Int) {
    val tmp = arr[left]
    arr[left] = arr[right]
    arr[right] = tmp
}

/**
 * Removes duplicate elements from a sorted array.
 * It doesn't deallocate elements or create a new array without
 * duplicates. It simply returns new size of the array, where all
 * the elements from 0 to size are sorted and contain no duplicates.
 *
 * @return number of distinct elements .
 */
fun removeDuplicatesFromArray(arr: IntArray): Int {
    if (arr.size == 1) {
        return 1
    }
    var positionAfterUnique = 1
    for (i in 1 until arr.size) {
        if (arr[i] != arr[positionAfterUnique - 1]) {
            arr[positionAfterUnique] = arr[i]
            ++positionAfterUnique
        }
    }
    return positionAfterUnique
}

























