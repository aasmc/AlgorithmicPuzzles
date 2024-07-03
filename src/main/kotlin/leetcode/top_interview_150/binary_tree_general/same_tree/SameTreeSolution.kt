package leetcode.top_interview_150.binary_tree_general.same_tree

class SameTreeSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        return (p?.`val` == q?.`val`) &&
                isSameTree(p?.left, q?.left) &&
                isSameTree(p?.right, q?.right)
    }

}