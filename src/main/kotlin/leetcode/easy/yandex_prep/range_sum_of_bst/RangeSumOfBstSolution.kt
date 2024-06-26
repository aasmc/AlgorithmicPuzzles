package leetcode.easy.yandex_prep.range_sum_of_bst

class RangeSumOfBstSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
        if (root == null) return 0
        var sum = 0
        fun dfs(current: TreeNode) {
            if (current.`val` in low..high) {
                sum += current.`val`
            }
            if (current.left != null) {
                dfs(current.left!!)
            }
            if (current.right != null) {
                dfs(current.right!!)
            }
        }
        dfs(root)
        return sum
    }
}