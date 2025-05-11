package leetcode.top_interview_150.binary_tree_general.count_complete_tree_nodes

import kotlin.math.pow

class CountCompleteTreeNodesSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun countNodes(root: TreeNode?): Int {
        if (root == null) return 0
        return countNodes(root.left) + countNodes(root.right) + 1
    }

    class Solution2 {
        fun countNodes(root: TreeNode?): Int {
            if (root == null) return 0
            val leftHeight = leftHeight(root)
            val rightHeight = rightHeight(root)
            if (leftHeight == rightHeight) {
                return 2.0.pow(leftHeight).toInt() - 1
            } else {
                return 1 + countNodes(root.left) + countNodes(root.right)
            }
        }

        private fun leftHeight(node: TreeNode?): Int {
            if (node == null) return 0
            return 1 + leftHeight(node.left)
        }

        private fun rightHeight(node: TreeNode?): Int {
            if (node == null) return 0
            return 1 + rightHeight(node.right)
        }
    }

}