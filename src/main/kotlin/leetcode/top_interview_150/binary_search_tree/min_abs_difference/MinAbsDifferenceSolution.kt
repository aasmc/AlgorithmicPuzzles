package leetcode.top_interview_150.binary_search_tree.min_abs_difference

import kotlin.math.abs

class MinAbsDifferenceSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun getMinimumDifference(root: TreeNode?): Int {
        if (root == null || (root.left == null && root.right == null)) return 0
        var result = Int.MAX_VALUE
        var previous: Int? = null
        fun dfs(node: TreeNode?) {
            if (node != null) {
                dfs(node.left)
                previous?.let {
                    result = minOf(result, node.`val` - it)
                }
                previous = node.`val`
                dfs(node.right)
            }
        }
        dfs(root)
        return result
    }

}