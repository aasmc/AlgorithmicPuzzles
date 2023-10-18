package leetcode.ace_coding.binary_tree_bfs.max_level_sum

import leetcode.ace_coding.common.TreeNode
import java.util.LinkedList

class MaxLevelSumSolution {

    fun maxLevelSum(root: TreeNode?): Int {
        val queue = LinkedList<TreeNode?>()
        var maxSum = Int.MIN_VALUE
        var minLevel = 0
        var level = 0
        queue.addLast(root)
        while (queue.isNotEmpty()) {
            val size = queue.size
            ++level
            var curSum = 0
            for (i in 0 until size) {
                val node = queue.removeFirst()!!
                curSum +=  node.`val`
                node.left?.let { queue.addLast(it) }
                node.right?.let { queue.addLast(it) }
            }
            if (curSum > maxSum) {
                maxSum = curSum
                minLevel = level
            }
        }
        return minLevel
    }

}