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
 *          length as the input array. If the input array is empty,
 *          returns an empty array.
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

/**
 * An augmented BST node that contains additional information
 * [lCount] - the number of nodes in the left subtree of the
 * current [LCountNode].
 */
data class LCountNode<T : Comparable<T>>(
    var data: T,
    var left: LCountNode<T>? = null,
    var right: LCountNode<T>? = null,
    var lCount: Long = 0L
)

/**
 * Finds k-th smallest element in a given BST.
 *
 * Algorithm is based on the following idea: we need to
 * compare [lCount] + 1 of a current root with [k]:
 *
 *   - if lCount + 1 == k, then this is our element
 *   - if lCount + 1 > k, make a recursive call for the left subtree with
 *     the same [k], since our value must be in the left subtree.
 *   - if lCount + 1 < k, our value is in the right subtree, and we need
 *     to make a recursive call there, BUT we also need to update [k]
 *     since for the right subtree we will be looking not the k-th smallest
 *     element, but (k - lCount + 1)-th element.
 */
tailrec fun <T: Comparable<T>> findKthSmallestElement(root: LCountNode<T>?, k: Long): T? {
    if (root == null) return null
    val currentCount = root.lCount + 1
    return when {
        currentCount == k -> root.data
        currentCount > k -> findKthSmallestElement(root.left, k)
        else -> findKthSmallestElement(root.right, k - currentCount)
    }
}





























