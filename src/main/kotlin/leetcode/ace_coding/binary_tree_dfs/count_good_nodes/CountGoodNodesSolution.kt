package leetcode.ace_coding.binary_tree_dfs.count_good_nodes

import leetcode.ace_coding.common.TreeNode

class CountGoodNodesSolution {

    fun goodNodes(root: TreeNode?): Int {
        if (root == null) return 0
        var count = 0
        fun helper(node: TreeNode?, max: Int) {
            if (node != null) {
                var cMax = max
                if (node.`val` >= max) {
                    ++count
                    cMax = node.`val`
                }
                helper(node.left, cMax)
                helper(node.right, cMax)
            }
        }
        helper(root, Int.MIN_VALUE)
        return count
    }


}