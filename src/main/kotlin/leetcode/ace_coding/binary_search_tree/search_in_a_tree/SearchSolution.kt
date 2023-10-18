package leetcode.ace_coding.binary_search_tree.search_in_a_tree

import leetcode.ace_coding.common.TreeNode

class SearchSolution {

    fun searchBST(root: TreeNode?, target: Int): TreeNode? {
        var current = root

        while (current != null) {
            if (current.`val` == target) {
                return current
            }
            current = if (current.`val` > target) {
                current.left
            } else {
                current.right
            }
        }
        return current
    }

}