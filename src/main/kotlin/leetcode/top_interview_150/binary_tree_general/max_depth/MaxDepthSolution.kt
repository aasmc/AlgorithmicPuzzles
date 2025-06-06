package leetcode.top_interview_150.binary_tree_general.max_depth

class MaxDepthSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        fun dfs(root: TreeNode, sum: Int): Int {
            val left = root.left?.let { dfs(it, sum + 1) } ?: sum
            val right = root.right?.let { dfs(it, sum + 1) } ?: sum
            return maxOf(left, right)
        }
        return dfs(root, 1)
    }

}