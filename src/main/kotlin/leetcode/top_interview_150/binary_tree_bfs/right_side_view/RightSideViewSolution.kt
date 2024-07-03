package leetcode.top_interview_150.binary_tree_bfs.right_side_view

import java.util.*

class RightSideViewSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun rightSideView(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()
        val queue = LinkedList<TreeNode>()
        val result = arrayListOf<Int>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val current = queue.removeFirst()
                if (i == size - 1) {
                    result.add(current.`val`)
                }
                current.left?.let { queue.add(it) }

                current.right?.let { queue.add(it) }
            }
        }
        return result
    }
}