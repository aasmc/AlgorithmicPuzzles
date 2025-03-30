package leetcode.ace_coding.binary_tree_dfs.max_depth

import leetcode.ace_coding.common.TreeNode

class MaxDepthSolution {

    fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        val left = if (root.left == null) 1 else maxDepth(root.left) + 1
        val right = if (root.right == null) 1 else maxDepth(root.right) + 1
        return maxOf(left, right)
    }


}