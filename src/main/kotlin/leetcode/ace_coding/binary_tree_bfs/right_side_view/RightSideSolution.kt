package leetcode.ace_coding.binary_tree_bfs.right_side_view

import leetcode.ace_coding.common.TreeNode
import java.util.LinkedList

class RightSideSolution {

    fun rightSideView(root: TreeNode?): List<Int> {
        val queue = LinkedList<TreeNode>()
        val result = mutableListOf<Int>()
        if (root == null) return emptyList()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val current = queue.poll()
                if (i == size - 1) {
                    result.add(current.`val`)
                }
                current.left?.let {
                    queue.add(it)
                }

                current.right?.let {
                    queue.add(it)
                }
            }
        }
        return result
    }

}