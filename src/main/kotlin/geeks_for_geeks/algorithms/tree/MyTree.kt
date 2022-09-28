package geeks_for_geeks.algorithms.tree

import geeks_for_geeks.algorithms.arrays.reverseInGroups
import java.lang.Integer.max
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.abs

/**
 * A simple binary tree.
 */
class MyTree<T : Comparable<T>> private constructor() {

    private var root: Node<T>? = null

    constructor(data: T) : this() {
        root = Node(data)
    }

    constructor(root: Node<T>) : this() {
        this.root = root
    }

    /**
     * Time complexity: O(N)
     * Space Complexity: O(height of binary tree + 1), i.e. it is recursive call stack
     */
    fun inorderTraversalRecursive(visit: (T) -> Unit) {
        inorderRecHelper(root, visit)
    }

    private fun inorderRecHelper(root: Node<T>?, visit: (T) -> Unit) {
        if (root != null) {
            inorderRecHelper(root.left, visit)
            visit(root.data)
            inorderRecHelper(root.right, visit)
        }
    }

    fun preOrderTraversalRecursive(visit: (T) -> Unit) {
        preOrderRecHelper(root, visit)
    }

    private fun preOrderRecHelper(root: Node<T>?, visit: (T) -> Unit) {
        if (root != null) {
            visit(root.data)
            preOrderRecHelper(root.left, visit)
            preOrderRecHelper(root.right, visit)
        }
    }

    fun postOrderTraversalRecursive(visit: (T) -> Unit) {
        postOrderRecHelper(root, visit)
    }

    private fun postOrderRecHelper(root: Node<T>?, visit: (T) -> Unit) {
        if (root != null) {
            postOrderRecHelper(root.left, visit)
            postOrderRecHelper(root.right, visit)
            visit(root.data)
        }
    }

    fun height(): Int {
        return heightRecHelper(root)
    }


    private fun heightRecHelper(root: Node<T>?): Int {
        if (root == null) {
            return 0
        }
        return max(heightRecHelper(root.left), heightRecHelper(root.right)) + 1
    }

    /**
     * Visits all nodes at level [k] in this tree.
     */
    fun nodesAtKDistance(k: Int, visit: (T) -> Unit) {
        nodesAtKDistanceRecHelper(k, root, visit)
    }

    private fun nodesAtKDistanceRecHelper(k: Int, root: Node<T>?, visit: (T) -> Unit) {
        if (root == null) return
        if (k == 0) {
            visit(root.data)
        } else {
            nodesAtKDistanceRecHelper(k - 1, root.left, visit)
            nodesAtKDistanceRecHelper(k - 1, root.right, visit)
        }
    }

    fun levelOrderTraversal(visit: (T) -> Unit) {
        levelOrderQueueHelper(root, visit)
    }

    private fun levelOrderQueueHelper(root: Node<T>?, visit: (T) -> Unit) {
        if (root == null) {
            return
        }
        val queue: Queue<Node<T>> = LinkedList()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val node = queue.poll()
            node.left?.let {
                queue.add(it)
            }
            node.right?.let {
                queue.add(it)
            }
            visit(node.data)
        }
    }

    fun levelOrderLineByLine(): List<List<T>> {
        return levelOrderLineByLineHelper(root)
    }

    private fun levelOrderLineByLineHelper(root: Node<T>?): List<List<T>> {
        if (root == null) return emptyList()
        val q = LinkedList<Node<T>?>()
        q.add(root)
        // add null marker that signifies the end of current level
        q.add(null)
        val result = MutableList(0) {
            listOf<T>()
        }
        var list: MutableList<T> = mutableListOf<T>()
        while (q.size > 1) {
            val current = q.poll()
            // this means we reached the end of level and
            // all elements from the next level have been added to the queue
            if (current == null) {
                result.add(list)
                list = mutableListOf()
                q.add(null)
                continue
            }
            list.add(current.data)
            current.left?.let { q.add(it) }
            current.right?.let { q.add(it) }
        }
        if (list.isNotEmpty()) {
            result.add(list)
        }
        return result.toList()
    }

    fun size(): Int {
        return sizeHelper(root)
    }

    private fun sizeHelper(root: Node<T>?): Int {
        if (root == null) {
            return 0
        }
        return 1 + sizeHelper(root.left) + sizeHelper(root.right)
    }

    fun maxValue(): T? {
        return maxValueRecHelper(root)
    }

    private fun maxValueRecHelper(root: Node<T>?): T? {
        if (root == null) {
            return null
        }
        return nodeMax(root.data, nodeMax(maxValueRecHelper(root.left), maxValueRecHelper(root.right)))
    }

    private fun <T : Comparable<T>> nodeMax(lhs: T?, rhs: T?): T? {
        return when {
            lhs == null && rhs == null -> null
            lhs == null -> rhs
            rhs == null -> lhs
            else -> {
                if (lhs >= rhs) {
                    lhs
                } else {
                    rhs
                }
            }
        }
    }

    /**
     * The function creates a list of leftmost nodes from all levels of a tree.
     * Basically, it is a pre-order traversal of a tree.
     * First we initialize variable maxLevel = 0, it will be used to find out
     * if we are traversing the right node of the same level.
     *
     * Second we check if current root is null, then we stop processing.
     * If not and current level is greater than maxLevel, means we are processing
     * the leftmost mode, so add it to the result list and change maxLevel to current level.
     *
     * Third. Make two recursive calls to root.left and root.right, also incrementing the level variable.
     *
     * By incrementing level, we make sure that when we go to the right child at the same level,
     * we will not process it.
     */
    fun leftViewOfTreeRecursive(): List<T> {
        var maxLevel = 0

        fun leftViewOfTreeRecHelper(
            root: Node<T>?,
            level: Int,
            visit: (T) -> Unit
        ) {
            if (root == null) return
            if (maxLevel < level) {
                visit(root.data)
                maxLevel = level
            }
            leftViewOfTreeRecHelper(root.left, level + 1, visit)
            leftViewOfTreeRecHelper(root.right, level + 1, visit)
        }

        val result = mutableListOf<T>()
        leftViewOfTreeRecHelper(root, 1) {
            result.add(it)
        }
        return result.toList()
    }

    fun leftViewOfTreeIterative(): List<T> {
        if (root == null) return emptyList()
        val queue = LinkedList<Node<T>>()
        val result = mutableListOf<T>()
        queue.add(root!!)
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) { // traverse current level
                val current = queue.poll() // remove first element from the queue
                if (i == 0) { // we are processing leftmost child
                    result.add(current.data)
                }
                // enqueue next level elements
                current.left?.let { queue.add(it) }
                current.right?.let { queue.add(it) }
            }
        }
        return result.toList()
    }

    /**
     * Checks if sum of children of any node in the tree is equal to their parent.
     * If there's only a root node, then it returns true.
     * If a node has only one child, then it checks if the child's value is equal to
     * this node's value.
     */
    fun isChildrenSumCompliant(): Boolean {
        return isChildrenSumCompliantRecHelper(root)
    }

    /**
     * Checks if this tree is height balanced, i.e. the difference
     * between the height of the any node's left subtree and right subtree is not more than one.
     */
    fun isHeightBalanced(): Boolean {
        return isHeightBalancedHelper(root) >= 0
    }

    private fun isHeightBalancedHelper(root: Node<T>?): Int {
        if (root == null) return 0
        // check if left subtree is balanced
        val leftHeight = isHeightBalancedHelper(root.left)
        if (leftHeight == -1) return -1
        // check if the right subtree is balanced
        val rightHeight = isHeightBalancedHelper(root.right)
        if (rightHeight == -1) return -1
        // if imbalanced, return -1
        if (abs(rightHeight - leftHeight) > 1) return -1
        // return the height of the tree
        return max(rightHeight, leftHeight) + 1
    }

    private fun isChildrenSumCompliantRecHelper(root: Node<T>?): Boolean {
        if (root == null) return true
        if (root.data !is Int) throw UnsupportedOperationException("Cannot perform isChildrenSumCompliant not on Int")
        if (root.left == null && root.right == null) {
            return true
        }
        var sum = 0
        root.left?.let {
            sum += (it.data as Int)
        }
        root.right?.let {
            sum += (it.data as Int)
        }
        return (root.data as Int) == sum
                && isChildrenSumCompliantRecHelper(root.left)
                && isChildrenSumCompliantRecHelper(root.right)
    }

    fun maxWidth(): Int {
        if (root == null) return 0
        val queue = LinkedList<Node<T>>()
        queue.add(root!!)
        var max = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            max = max(size, max)
            for (i in 0 until size) {
                val current = queue.poll()
                current.left?.let { queue.add(it) }
                current.right?.let { queue.add(it) }
            }
        }
        return max
    }

    /**
     * Converts this binary tree to a doubly linked list and returns
     * the head of the list. Conversion happens in place, i.e. no new
     * memory is allocated for the Nodes.
     *
     * Order of the nodes is determined by in-order traversal of the tree,
     * i.e Left - Root - Right.
     */
    fun toDoublyLinkedList(): Node<T>? {
        var previous: Node<T>? = null
        fun recursiveHelper(root: Node<T>?): Node<T>? {
            if (root == null) return root
            // process left subtree
            var head = recursiveHelper(root.left)
            // This means we have reached the leftmost leaf node, so make it the head of our list
            if (previous == null) {
                head = root
            } else {
                // connect root's left pointer to previous
                root.left = previous
                // connect previous' right pointer to root
                previous!!.right = root
            }
            // move previous pointer to current node
            previous = root
            // traverse the right subtree
            recursiveHelper(root.right)
            return head
        }
        return recursiveHelper(root)
    }

    /**
     * Traverses the tree in level order going by spiral,
     * i.e. first root -> next level goes from right to left,
     * next level goes from left to right and so on.
     */
    fun levelOrderTraversalSpiralFormUsingStack(visit: (T) -> Unit) {
        fun helper(root: Node<T>?) {
            if (root == null) return
            val queue = LinkedList<Node<T>>()
            queue.add(root)
            // holds nodes in reverse order
            val stack = Stack<Node<T>>()
            var reverse = false
            while (queue.isNotEmpty()) {
                val count = queue.size
                for (i in 0 until count) {
                    val current = queue.poll()
                    if (reverse) {
                        stack.push(current)
                    } else {
                        visit(current.data)
                    }
                    current.left?.let { queue.add(it) }
                    current.right?.let { queue.add(it) }
                }
                if (reverse) {
                    while (stack.isNotEmpty()) {
                        visit(stack.pop().data)
                    }
                }
                reverse = !reverse
            }
        }
        helper(root)
    }

    fun levelOrderTraversalSpiralFormUsingStackEfficient(visit: (T) -> Unit) {
        fun helper(root: Node<T>?) {
            if (root == null) return
            val leftToRight = Stack<Node<T>>()
            leftToRight.push(root)
            val rightToLeft = Stack<Node<T>>()

            while (leftToRight.isNotEmpty() || rightToLeft.isNotEmpty()) {
                while (leftToRight.isNotEmpty()) {
                    val current = leftToRight.pop()
                    visit(current.data)
                    current.left?.let { rightToLeft.push(it) }
                    current.right?.let { rightToLeft.push(it) }
                }
                while (rightToLeft.isNotEmpty()) {
                    val current = rightToLeft.pop()
                    visit(current.data)
                    current.right?.let { leftToRight.push(it) }
                    current.left?.let { leftToRight.push(it) }
                }
            }
        }
        helper(root)
    }

    /**
     * Returns the number of nodes on the longest path between two leaf nodes.
     * The problem can be solved using the following approach:
     * diameter = currentRoot.left.height + currentRoot.right.height + 1
     *
     * Time Complexity O(N^2)
     */
    fun getDiameterNaive(): Int {
        fun helper(root: Node<T>?): Int {
            if (root == null) return 0
            // diameter of the current root
            val d1 = 1 + heightRecHelper(root.left) + heightRecHelper(root.right)
            // diameter of the left child
            val d2 = helper(root.left)
            // diameter of the right child
            val d3 = helper(root.right)
            return max(d1, max(d2, d3))
        }
        return helper(root)
    }

    fun getDiameterUsingHashMap(): Int {
        val heights = hashMapOf<Node<T>, Int>()

        // precompute heights for all the nodes
        fun fillHeights(root: Node<T>?): Int {
            if (root == null) return 0
            val lh = fillHeights(root.left)
            val rh = fillHeights(root.right)
            val height = max(lh, rh) + 1
            heights[root] = height
            return height
        }
        fillHeights(root)
        fun helper(root: Node<T>?): Int {
            if (root == null) {
                return 0
            }
            val lh = heights[root.left] ?: 0
            val rh = heights[root.right] ?: 0
            val d1 = 1 + lh + rh
            val d2 = helper(root.left)
            val d3 = helper(root.right)
            return max(d1, max(d2, d3))
        }
        return helper(root)
    }

    fun getDiameterEfficient(): Int {
        var result = 0
        fun helper(root: Node<T>?): Int {
            if (root == null) return 0
            val lh = helper(root.left)
            val rh = helper(root.right)
            result = max(result, 1 + lh + rh)
            return 1 + max(lh, rh)
        }
        helper(root)
        return result
    }

    fun lowestCommonAncestorInefficient(left: T, right: T): Node<T>? {
        val leftPath = findPath(root, left)
        val rightPath = findPath(root, right)
        if (leftPath.isEmpty() || rightPath.isEmpty()) return null
        var i = 0
        // corner case when either of the paths contains only root element
        if (leftPath.size == 1 || rightPath.size == 1) {
            return if (leftPath[0] == rightPath[0]) {
                leftPath[0]
            } else {
                null
            }
        }
        while (i < leftPath.size - 1 && i < rightPath.size - 1) {
            if (leftPath[i + 1] != rightPath[i + 1]) {
                return leftPath[i]
            }
            ++i
        }
        return null
    }

    private fun findPath(root: Node<T>?, data: T): List<Node<T>> {
        val result = arrayListOf<Node<T>>()
        fun helper(root: Node<T>?, path: ArrayList<Node<T>>, data: T): Boolean {
            if (root == null) return false
            path.add(root)
            if (root.data == data) return true
            if (helper(root.left, path, data) || helper(root.right, path, data)) {
                return true
            }
            // current root is not on the path, so remove it.
            result.removeAt(result.size - 1)
            return false
        }
        helper(root, result, data)
        return result
    }

    /**
     * Returns the lowest common ancestor of the two given nodes.
     * This algorithm assumes that both the nodes are present in the tree.
     */
    fun lowestCommonAncestorEfficient(left: T, right: T): Node<T>? {
        fun helper(root: Node<T>?, left: T, right: T): Node<T>? {
            if (root == null) return null
            // case 1. current root contains either left or right value
            // we assume that both right and left values are in the tree,
            // so here we return current root
            if (root.data == left || root.data == right) {
                return root
            }
            val leftLca = helper(root.left, left, right)
            val rightLca = helper(root.right, left, right)
            // case 2. both leftLca and rightLca are not null, means that
            // either leftLca contains left value and rightLca contains right value
            // or vice versa, this means current root is the lca of left and right
            if (leftLca != null && rightLca != null) {
                return root
            }
            // case 3. leftLca is not null, means both the values are
            // in the left subtree, so we return leftLca
            // case 4. either rightLca is not null, then both the values
            // are in the right subtree, so we return it,
            // or rightLca is null then we have no lca, so return null
            return leftLca ?: rightLca
        }
        return helper(root, left, right)
    }

    /**
     * Returns the number of nodes that lie between a given [leaf] and
     * the farthest [Node] from it.
     */
    fun distanceToFarthestNodeFromLeaf(leaf: T): Int {
        var result = 0
        /**
         * This function returns the height of the given [root], as well as
         * sets [Distance] from the given [root] to [leaf] if [leaf] is
         * a descendant of the current [root]. If [leaf] is not a descendant
         * of the given [root], then [Distance] is kept as -1.
         */
        fun helper(root: Node<T>?, leaf: T, distance: Distance): Int {
            // height of an empty tree is 0, and we don't update Distance
            if (root == null) return 0
            // if current root is the leaf node we are burning the tree from,
            // then update distance to 0 and return height as 1.
            if (root.data == leaf) {
                distance.distance = 0
                return 1
            }
            val leftDistance = Distance(-1)
            val rightDistance = Distance(-1)
            val leftHeight = helper(root.left, leaf, leftDistance)
            val rightHeight = helper(root.right, leaf, rightDistance)
            // if leaf is in the left subtree of the current root
            if (leftDistance.distance != -1) {
                // update current distance
                distance.distance = leftDistance.distance + 1
                // compute the result as max of the previous result
                // and height of the right subtree and distance to the leaf
                result = max(result, rightHeight + distance.distance)
                // if leaf is in the right subtree of the current root
            } else if (rightDistance.distance != -1) {
                // update current distance
                distance.distance = rightDistance.distance + 1
                // compute the result as max of the previous result
                // and height of the left subtree and distance to the leaf
                result = max(result, leftHeight + distance.distance)
            }
            // return height of the current root
            return max(leftHeight, rightHeight) + 1
        }
        val initialDistance = Distance(-1)
        helper(root, leaf, initialDistance)
        return result
    }

    data class Distance(var distance: Int)

    fun clear() {
        root = null
    }

    data class Node<T>(
        val data: T,
        var left: Node<T>? = null,
        var right: Node<T>? = null
    )

    companion object {
        /**
         * Given an in-order and pre-order traversals of the tree in the form of
         * arrays of [T] constructs a binary tree, and returns the root of
         * the tree.
         *
         * Time Complexity O(N^2)
         */
        fun <T> constructFromInorderAndPreOrder(inOrder: Array<T>, preOrder: Array<T>): Node<T> {
            var preOrderIdx = 0
            fun helper(inOrder: Array<T>, preOrder: Array<T>, inStart: Int, inEnd: Int): Node<T>? {
                if (inStart > inEnd) return null
                val root = Node(preOrder[preOrderIdx++])
                var inIdx = 0
                for (i in inStart..inEnd) {
                    if (inOrder[i] == root.data) {
                        inIdx = i
                        break
                    }
                }
                root.left = helper(inOrder, preOrder, inStart, inIdx - 1)
                root.right = helper(inOrder, preOrder, inIdx + 1, inEnd)
                return root
            }
            return helper(inOrder, preOrder, 0, preOrder.size - 1)
                ?: throw IllegalArgumentException("Cannot construct a tree from $inOrder $preOrder")
        }

        /**
         * Given an in-order and pre-order traversals of the tree in the form of
         * arrays of [T] constructs a binary tree, and returns the root of
         * the tree.
         *
         * Pre Condition -> the tree must contain no duplicate elements.
         *
         * Time Complexity O(N)
         */
        fun <T> constructFromInorderAndPreOrderWithHash(inOrder: Array<T>, preOrder: Array<T>): Node<T> {
            var preOrderIdx = 0
            val hash_ = hashMapOf<T, Int>()
            inOrder.forEachIndexed { idx, value ->
                hash_[value] = idx
            }

            fun helper(
                inOrder: Array<T>,
                preOrder: Array<T>,
                inStart: Int,
                inEnd: Int,
                hash: HashMap<T, Int>
            ): Node<T>? {
                if (inStart > inEnd) return null
                val root = Node(preOrder[preOrderIdx++])
                val inIdx = hash[root.data]
                    ?: throw IllegalArgumentException("Invalid parameters. Cannot construct a tree from: ${inOrder.contentToString()} and ${preOrder.contentToString()}")
                root.left = helper(inOrder, preOrder, inStart, inIdx - 1, hash)
                root.right = helper(inOrder, preOrder, inIdx + 1, inEnd, hash)
                return root
            }
            return helper(inOrder, preOrder, 0, preOrder.size - 1, hash_)
                ?: throw IllegalArgumentException("Cannot construct a tree from $inOrder $preOrder")
        }
    }
}




















