package leetcode.ace_coding.binary_tree_dfs.leaf_similar

import leetcode.ace_coding.common.TreeNode

class LeafSimilarSolution {

    fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
        return formLeafSequence(root1) == formLeafSequence(root2)
    }

    private fun formLeafSequence(root: TreeNode?): List<Int> {
        val result = arrayListOf<Int>()
        fun helper(root: TreeNode?) {
            if (root != null) {
                if (root.left == null && root.right == null) {
                    result.add(root.`val`)
                } else {
                    helper(root.left)
                    helper(root.right)
                }
            }
        }
        helper(root)
        return result
    }

}