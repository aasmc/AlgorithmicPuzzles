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
        } else if (arr[i] > arr[secondMax]){
            secondMax = i
        }
    }
    return secondMax
}





























