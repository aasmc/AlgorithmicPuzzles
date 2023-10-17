package leetcode.ace_coding.binary_tree_dfs.path_sum

import leetcode.ace_coding.binary_tree_dfs.common.TreeNode

class PathSumSolution {

    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        val map = hashMapOf<Long, Int>(0L to 1)
        fun solve(node: TreeNode?, sum: Int, runningSum: Long): Int {
            if (node == null) return 0
            val currentSum = runningSum + node.`val`
            var count = map.getOrDefault(currentSum - sum, 0)
            map[currentSum] = map.getOrDefault(currentSum, 0) + 1
            count += solve(node.left, sum, currentSum)
            count += solve(node.right, sum, currentSum)
            map[currentSum] = map[currentSum]!! - 1
            return count
        }
        return solve(root, targetSum, 0)
    }

//    fun pathSum(root: TreeNode?, targetSum: Int): Int {
//        if (root == null) return 0
//        val current = helper(root, targetSum.toLong())
//        val left = pathSum(root.left, targetSum)
//        val right = pathSum(root.right, targetSum)
//        return current + left + right
//    }

    private fun helper(root: TreeNode?, sum: Long): Int {
        if (root == null) return 0
        var count = 0
        val current = root.`val`
        if (sum - current == 0L) {
            ++count
        }
        count += helper(root.left, sum - current)
        count += helper(root.right, sum - current)
        return count
    }

}