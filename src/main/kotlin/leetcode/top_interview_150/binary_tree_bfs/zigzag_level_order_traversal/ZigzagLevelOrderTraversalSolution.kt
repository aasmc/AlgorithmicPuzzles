package leetcode.top_interview_150.binary_tree_bfs.zigzag_level_order_traversal

import java.util.ArrayList
import java.util.LinkedList

class ZigzagLevelOrderTraversalSolution {


    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()
        val queue = LinkedList<TreeNode>()
        val result = arrayListOf<LinkedList<Int>>()
        queue.add(root)
        var leftToRight = true
        while (queue.isNotEmpty()) {
            val size = queue.size
            val list = LinkedList<Int>()
            for (i in 0 until size) {
                val node = queue.removeFirst()
                if (leftToRight) {
                    list.addLast(node.`val`)
                } else if (!leftToRight) {
                    list.addFirst(node.`val`)
                }
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
            result.add(list)
            leftToRight = !leftToRight
        }
        return result
    }

}