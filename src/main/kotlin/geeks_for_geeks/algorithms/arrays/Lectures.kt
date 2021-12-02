package geeks_for_geeks.algorithms.arrays

/**
 * Inserts an integer [elem] into array [arr] at specified index.
 * If the array is full, then no insertion happens.
 *
 * Returns size of the array.
 */
fun insertIntoArray(
    arr: IntArray,
    elem: Int,
    index: Int,
    size: Int,
    capacity: Int
): Int {
    if (size == capacity) {
        return size
    }
    for (i in size - 1 downTo index) {
        arr[i + 1] = arr[i]
    }
    arr[index] = elem
    return size + 1
}
































