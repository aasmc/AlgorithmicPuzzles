package leetcode.top_interview_150.binary_search_tree.flatten_tree

class FlattenTreeSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun flatten(root: TreeNode?): Unit {
        if (root == null) return
        fun preorder(current: TreeNode) {
            // first flatten the left subtree
            current.left?.let { preorder(it) }
            // save the left subtree in the temporary variable
            val left = current.left
            // nullify the left pointer
            current.left = null
            // get the pointer to the last node of the flattened left subtree
            var lastLeft = left
            while (lastLeft?.right != null) {
                lastLeft = lastLeft.right
            }
            // save right subtree to the temporary variable
            val right = current.right
            // reset the right pointer to the flattened left subtree
            if (left != null) {
                current.right = left
            }
            // flatten the right pointer
            right?.let { preorder(it) }
            // set the flattened left subtree last node's right pointer to the
            // flattened right subtree
            if (lastLeft != null) {
                lastLeft.right = right
            }
        }
        preorder(root)
    }

}