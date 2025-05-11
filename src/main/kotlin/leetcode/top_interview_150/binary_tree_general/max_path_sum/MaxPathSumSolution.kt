package leetcode.top_interview_150.binary_tree_general.max_path_sum

class MaxPathSumSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun maxPathSum(root: TreeNode?): Int {
        var result = Int.MIN_VALUE
        fun dfs(current: TreeNode?): Int {
            if (current == null) return 0
            // first calculate the maximum sum of the path of the left subtree,
            // if the left subtree is not split, since we cannot split the tree
            // more than once and still get a sequence of adjacent nodes, i.e. path.
            // we don't need to consider negative values, since they only decrease
            // the total sum, therefore we take maxOf(0, result of the calculation)
            val leftSum = maxOf(dfs(current.left), 0)
            // then calculate the maximum sum of the path of the right subtree
            // if the right subtree is not split
            val rightSum = maxOf(dfs(current.right), 0)
            // possibly update the result by calculating the sum of the path
            // with current node as the root, i.e. the split-point of the tree
            result = maxOf(leftSum + rightSum + current.`val`, result)
            // the return of the function is the maximum value of the right or
            // left path sum plus the current value, because we want to return
            // to the caller of the function the max sum path without splitting
            // the tree.
            return  current.`val` + maxOf(leftSum, rightSum)
        }
        dfs(root)
        return result
    }

}