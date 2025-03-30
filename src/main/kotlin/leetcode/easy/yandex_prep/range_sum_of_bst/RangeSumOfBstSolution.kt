package leetcode.easy.yandex_prep.range_sum_of_bst

class RangeSumOfBstSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
        if (root == null) return 0
        fun dfs(current: TreeNode): Int {
            var sum = 0
            if (current.`val` in low..high) {
                sum += current.`val`
            }
            if (current.left != null) {
                sum += dfs(current.left!!)
            }
            if (current.right != null) {
                sum += dfs(current.right!!)
            }
            return sum
        }
        return dfs(root)
    }
}