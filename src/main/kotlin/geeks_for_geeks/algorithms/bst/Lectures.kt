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
tailrec fun <T : Comparable<T>> findKthSmallestElement(root: LCountNode<T>?, k: Long): T? {
    if (root == null) return null
    val currentCount = root.lCount + 1
    return when {
        currentCount == k -> root.data
        currentCount > k -> findKthSmallestElement(root.left, k)
        else -> findKthSmallestElement(root.right, k - currentCount)
    }
}


data class TreeNode<T : Comparable<T>>(
    var data: T,
    var left: TreeNode<T>? = null,
    var right: TreeNode<T>? = null
)

/**
 * Checks if a given [root] of the [TreeNode] represents a BST.
 *
 * Inefficient (Time Complexity O(N^2)) approach, is for every node of the tree:
 *  - find max in the left subtree
 *  - find min in right subtree
 *  - check if the current node is greater than the max and smaller that the min.
 *
 *  First Efficient algo (works only for elements where we can specify MAX and MIN possible
 *  values):
 *  - pass a range of possible values for every node
 *  - for root: range is -Infinity to +Infinity
 *  - for left child of a node, in range, we change upper bound (exclusive) as the node's
 *    value
 *  - for right child of a node, in range we change lower bound (exclusive) as the node's value.
 */
fun checkForBSTEfficientOne(root: TreeNode<Int>?): Boolean {
    fun helper(root: TreeNode<Int>?, fromExclusive: Int, toExclusive: Int): Boolean {
        if (root == null) return true
        val currentCorrect = root.data in (fromExclusive + 1) until toExclusive
        // && is a shot-circuit operator, so we will not check left or right subtree,
        // if current root is not valid
        return currentCorrect &&
                helper(root.left, fromExclusive, root.data) &&
                helper(root.right, root.data, toExclusive)
    }
    return helper(root, Int.MIN_VALUE, Int.MAX_VALUE)
}

/**
 * Checks if a given [root] of the [TreeNode] represents a BST.
 *
 * This algo takes into account the fact that a tree is a BST
 * if and only if its inOrder traversal produces a sorted result.
 *
 */
fun <T : Comparable<T>> checkForBSTEfficientTwo(root: TreeNode<T>?): Boolean {
    var predecessor: T? = null
    fun helper(root: TreeNode<T>?): Boolean {
        if (root == null) return true
        if (!helper(root.left)) return false
        if (predecessor == null) {
            predecessor = root.data // leftMost leaf node in inOrder traversal
            return true
        }
        if (root.data <= predecessor!!) return false
        predecessor = root.data
        return helper(root.right)
    }
    return helper(root)
}

/**
 * Given a root of a binary tree which is almost a BST, with only two nodes
 * violating the invariants of the BST, and swapping those two nodes will
 * turn this tree into a BST, find those two nodes and perform the swap.
 *
 * Return the root of the BST after swapping.
 */
fun <T : Comparable<T>> fixTreeToBSTWithTwoSwappedNodes(root: TreeNode<T>): TreeNode<T> {
    // tracks the previous element in inOrder traversal
    var prev: TreeNode<T>? = null
    // tracks first violation of the sort order
    var firstViolation: TreeNode<T>? = null
    // tracks second violation of the sort order
    var secondViolation: TreeNode<T>? = null

    fun helper(root: TreeNode<T>?) {
        if (root != null) {
            // in inOrder traversal we first go to the left subtree
            helper(root.left)

            // then we visit current node
            // if this node's value is less than the value of the previous node
            // it means there's a violation of the sort order
            if (prev != null && root.data < prev!!.data) {
                if (firstViolation == null) {
                    firstViolation = prev
                }
                secondViolation = root
            }
            prev = root
            helper(root.right)
        }
    }
    helper(root)
    // since for simplicity we maintain a mutable data structure,
    // we simply swap values in the two incorrect nodes.
    if (firstViolation != null && secondViolation != null) {
        val tmp = firstViolation!!.data
        firstViolation!!.data = secondViolation!!.data
        secondViolation!!.data = tmp
    }
    return root
}


fun findAndSwapTwoElementsInArrayToMakeItSorted(arr: IntArray) {
    var prev = Int.MIN_VALUE
    var firstIdx: Int? = null
    var secondIdx: Int? = null
    for (i in arr.indices) {
        if (arr[i] < prev) { // if the sort order is violated
            // if it is the first violation
            if (firstIdx == null) {
                // save index of the element that violates the sort order, i.e. index of
                // the previous element
                firstIdx = i - 1
            }
            // always save the index of the second element
            secondIdx = i
        }
        prev = arr[i]
    }
    if (firstIdx != null && secondIdx != null) {
        val tmp = arr[firstIdx]
        arr[firstIdx] = arr[secondIdx]
        arr[secondIdx] = tmp
    }
}

/**
 * Given a root of the binary tree and a sum, find if there are
 * two elements in the tree with the sum of data fields equal
 * to the given sum.
 */
fun findPairSumUsingHashMap(root: TreeNode<Int>?, sum: Int): Boolean {
    if (root == null) return false
    val cache = hashMapOf<Int, Int>()
    fun helper(root: TreeNode<Int>?) {
        if (root != null) {
            helper(root.left)
            cache[root.data] = sum - root.data
            helper(root.right)
        }
    }
    helper(root)
    for (v in cache.values) {
        if (cache.containsKey(v)) return true
    }
    return false
}

fun findPairSumUsingHashSet(root: TreeNode<Int>?, sum: Int): Boolean {
    if (root == null) return false
    val cache = hashSetOf<Int>()
    fun helper(root: TreeNode<Int>?): Boolean {
        if (root == null) return false
        if (helper(root.left)) return true
        if (cache.contains(sum - root.data)) {
            return true
        } else {
            cache.add(root.data)
        }
        return helper(root.right)
    }
    return helper(root)
}

/**
 * Computes the sum of nodes of a given BST which are on the same
 * vertical line in the tree, i.e. they are at the same horizontal distance from
 * root. Horizontal distance of root is 0, when moving to the left of the root,
 * we decrement the horizontal distance, when moving to the right - we increment.
 *
 * @return a list of integers, representing sums of node values in the tree. The list
 *         is ordered according to the horizontal distance from root, starging from the
 *         leftmost node and ending in the rightmost node.
 */
fun verticalSumInBinaryTree(root: TreeNode<Int>?): List<Int> {
    val distanceToSum = TreeMap<Int, Int>()
    fun helper(root: TreeNode<Int>?, distance: Int) {
        if (root != null) {
            helper(root.left, distance - 1)
            distanceToSum.merge(distance, root.data, Int::plus)
            helper(root.right, distance + 1)
        }
    }
    helper(root, 0)
    return distanceToSum.values.toList()
}

/**
 * Traverses a given tree in vertical order: according to the horizontal
 * distances from root. Elements higher in the vertical order come first,
 * if two elements are at the same level, then first comes the left element,
 * then goes the right element.
 *
 * @return a list of lists of elements, representing the vertical levels of
 *         the given tree.
 */
fun verticalOrderTraversal(root: TreeNode<Int>?): List<List<Int>> {
    if (root == null) return emptyList()
    val distanceToNodes = TreeMap<Int, MutableList<Int>>()
    // holds pairs of the tree nodes to their distance from root
    val queue = LinkedList<Pair<TreeNode<Int>, Int>>()
    queue.push(root to 0)
    while (queue.isNotEmpty()) {
        val (node, distance) = queue.poll()
        if (distanceToNodes.containsKey(distance)) {
            distanceToNodes[distance]!!.add(node.data)
        } else {
            distanceToNodes[distance] = mutableListOf(node.data)
        }
        node.left?.let { queue.add(it to distance - 1) }
        node.right?.let { queue.add(it to distance + 1) }
    }
    return distanceToNodes.values.toList()
}
















