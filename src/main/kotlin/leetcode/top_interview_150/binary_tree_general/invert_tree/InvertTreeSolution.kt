package leetcode.top_interview_150.binary_tree_general.invert_tree

class InvertTreeSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun invertTree(root: TreeNode?): TreeNode? {
        if (root != null) {
            val tmp = root.left
            root.left = root.right
            root.right = tmp
            invertTree(root.left)
            invertTree(root.right)
        }
        return root
    }

}