package leetcode.medium.yandex_prep.validate_bst

class ValidateBSTSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun isValidBST(root: TreeNode?): Boolean {
        fun dfs(node: TreeNode?, leftBound: Long, rightBound: Long): Boolean {
            if (node == null) return true
            val nodeValid = node.`val` > leftBound && node.`val` < rightBound
            return nodeValid &&
                    dfs(node.left, leftBound, node.`val`.toLong()) &&
                    dfs(node.right, node.`val`.toLong(), rightBound)
        }
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }
}