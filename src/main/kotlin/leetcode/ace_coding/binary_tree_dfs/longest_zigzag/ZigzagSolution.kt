package leetcode.ace_coding.binary_tree_dfs.longest_zigzag

import leetcode.ace_coding.common.TreeNode

class ZigzagSolution {

    fun longestZigZag(root: TreeNode?): Int {
        var result = 0
        fun dfs(node: TreeNode?, goLeft: Boolean, count: Int) {
            if (node != null) {
                result = maxOf(result, count)
                if (goLeft) {
                    dfs(node.left, false, count + 1)
                    dfs(node.right, true, 1)
                } else {
                    dfs(node.left, false, 1)
                    dfs(node.right, true, count + 1)
                }
            }
        }
        dfs(root, false, 0)
        dfs(root, true, 0)
        return result
    }

}