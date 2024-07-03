package leetcode.top_interview_150.binary_tree_general.next_right_pointer

import java.util.LinkedList

class NextRightPointerSolution {

    class Node(var `val`: Int) {
        var left: Node? = null
        var right: Node? = null
        var next: Node? = null
    }

    fun connect(root: Node?): Node? {
        if (root == null) return null

        val queue = LinkedList<Node>()
        queue.addLast(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val current = queue.removeFirst()
                if (i < size - 1) {
                    val next = queue.firstOrNull()
                    current.next = next
                }
                current.left?.let { queue.addLast(it) }
                current.right?.let { queue.addLast(it) }
            }
        }
        return root
    }

}