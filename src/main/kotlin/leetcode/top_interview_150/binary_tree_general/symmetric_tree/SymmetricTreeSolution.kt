package leetcode.top_interview_150.binary_tree_general.symmetric_tree

class SymmetricTreeSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return false
        fun dfs(l: TreeNode?, r: TreeNode?): Boolean {
            if (l == null && r == null) return true
            if (l == null || r == null) return false
            return l.`val` == r.`val` &&
                    dfs(l.left, r.right) &&
                    dfs(l.right, r.left)
        }
        return dfs(root.left, root.right)
    }

}