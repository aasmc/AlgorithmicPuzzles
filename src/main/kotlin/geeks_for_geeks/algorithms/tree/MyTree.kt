package geeks_for_geeks.algorithms.tree

import java.util.*
import kotlin.math.max

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

    fun clear() {
        root = null
    }

    data class Node<T>(
        val data: T,
        var left: Node<T>? = null,
        var right: Node<T>? = null
    )
}