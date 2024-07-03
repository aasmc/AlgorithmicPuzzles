package leetcode.top_interview_150.binary_tree_general.count_complete_tree_nodes

class CountCompleteTreeNodesSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun countNodes(root: TreeNode?): Int {
        if (root == null) return 0
        return countNodes(root.left) + countNodes(root.right) + 1
    }

}