package leetcode.easy.two_pointers.two_sum_4_bst

class TwoSum4Solution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun findTarget(root: TreeNode?, k: Int): Boolean {
        val complements = hashSetOf<Int>()
        if (root == null) return false
        fun dfs(node: TreeNode): Boolean {
            if (complements.contains(k - node.`val`)) {
                return true
            }
            complements.add(node.`val`)
            val leftHas = node.left?.let { dfs(it) } ?: false
            if (leftHas) return true
            return node.right?.let { dfs(it) } ?: false
        }
        return dfs(root)
    }

}