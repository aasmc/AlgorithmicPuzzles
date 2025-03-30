package leetcode.top_interview_150.binary_tree_bfs.level_order_traversal

import java.util.LinkedList

class LevelOrderTraversalSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun levelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()
        val queue = LinkedList<TreeNode>()
        val result = mutableListOf<MutableList<Int>>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            val list = mutableListOf<Int>()
            for (i in 0 until size) {
                val node = queue.removeFirst()
                list.add(node.`val`)
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
            result.add(list)
        }
        return result
    }

}