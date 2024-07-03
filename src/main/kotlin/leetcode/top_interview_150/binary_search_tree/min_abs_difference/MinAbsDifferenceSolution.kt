package leetcode.top_interview_150.binary_search_tree.min_abs_difference

import kotlin.math.abs

class MinAbsDifferenceSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun getMinimumDifference(root: TreeNode?): Int {
        if (root == null) return 0
        var result = Int.MAX_VALUE
        var previous: TreeNode? = null
        fun dfs(node: TreeNode?) {
            if (node == null) return
            dfs(node.left)
            if (previous != null) {
                result = minOf(result, abs(previous!!.`val` - node.`val`))
            }
            previous = node
            dfs(node.right)
        }
        dfs(root)
        return result
    }

}