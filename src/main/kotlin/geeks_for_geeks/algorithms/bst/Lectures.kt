package geeks_for_geeks.algorithms.bst

import java.util.*

/**
 * For every element in the given [arr] array of integers,
 * find a ceiling value that is in the left part of the array.
 * If there's no ceiling value, then return -1.
 *
 * A ceiling value is the smallest value that is greater than
 * or equal to this value.
 *
 * E.g. INPUT: {2,  8,  30, 15, 25, 12}
 *     OUTPUT: {-1, -1, -1, 30, 30, 15}
 *
 * Time Complexity O(NlogN)
 * Space Complexity O(N)
 *
 * @return [arr] with calculated values. The array is of the same
 *          length as the input array.
 */
fun ceilingOnLeftSideOfArray(arr: IntArray): IntArray {
    if (arr.isEmpty()) return intArrayOf()
    val tree = TreeSet<Int>()
    val result = IntArray(arr.size) { -1 }
    tree.add(arr[0])
    for (i in 1 until arr.size) { // overall NlogN
        val elem = arr[i]
        val ceiling = tree.ceiling(elem) // logN
        if (ceiling != null) {
            result[i] = ceiling
        }
        tree.add(elem) // logN
    }
    return result
}