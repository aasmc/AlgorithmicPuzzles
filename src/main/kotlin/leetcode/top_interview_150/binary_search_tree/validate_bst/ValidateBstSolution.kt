package leetcode.top_interview_150.binary_search_tree.validate_bst

class ValidateBstSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun isValidBST(root: TreeNode?): Boolean {
        if (root == null) return true
        fun dfs(node: TreeNode?, leftBoundary: Long, rightBoundary: Long): Boolean {
            if (node == null) return true
            val nodeValid = node.`val` in (leftBoundary + 1) until rightBoundary
            return nodeValid &&
                    dfs(node.left, leftBoundary, node.`val`.toLong()) &&
                    dfs(node.right, node.`val`.toLong(), rightBoundary)
        }
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }

}