package leetcode.top_interview_150.binary_search_tree.sum_root_to_leaf

class SumRootToLeafNumbersSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun sumNumbers(root: TreeNode?): Int {
        fun dfs(root: TreeNode?, value: Int): Int {
            if (root == null) return 0
            val currentValue = value * 10 + root.`val`
            if (root.left == null && root.right == null) {
                return currentValue
            }
            return dfs(root.left, currentValue) + dfs(root.right, currentValue)
        }
        return dfs(root, 0)
    }

}