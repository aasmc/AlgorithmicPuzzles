package leetcode.top_interview_150.binary_search_tree.max_path_sum

class MaxPathSumSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun maxPathSum(root: TreeNode?): Int {
        var result = Int.MIN_VALUE
        fun dfs(current: TreeNode?): Int {
            if (current == null) return 0
            val leftSum = maxOf(dfs(current.left), 0)
            val rightSum = maxOf(dfs(current.right), 0)
            result = maxOf(leftSum + rightSum + current.`val`, result)
            return  current.`val` + maxOf(leftSum, rightSum)
        }
        dfs(root)
        return result
    }

}