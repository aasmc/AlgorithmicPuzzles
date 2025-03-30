package leetcode.top_interview_150.binary_tree_bfs.average_levels

import java.util.LinkedList

class AverageLevelsSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun averageOfLevels(root: TreeNode?): DoubleArray {
        if (root == null) return doubleArrayOf()

        val queue = LinkedList<TreeNode>()
        val list = arrayListOf<Double>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            var sum = 0L
            for (i in 0 until size) {
                val node = queue.removeFirst()
                sum += node.`val`
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
            list.add(sum * 1.0 / size)
        }
        val result = DoubleArray(list.size)
        list.forEachIndexed { index, value ->
            result[index] = value
        }
        return result
    }

}