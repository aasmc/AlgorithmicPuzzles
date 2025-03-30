package leetcode.top_interview_150.binary_search_tree.kth_smallest_value

class KthSmallestElementSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun kthSmallest(root: TreeNode?, k: Int): Int {
        var currentIdx = 0
        var result = 0
        var found = false
        fun inorder(node: TreeNode?) {
            if (!found && node != null) {
                inorder(node.left)
                ++currentIdx
                if (currentIdx < k) {
                    inorder(node.right)
                } else if (currentIdx == k) {
                    result = node.`val`
                    found = true
                }
            }
        }
        inorder(root)
        return result
    }

}