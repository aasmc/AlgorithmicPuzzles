package leetcode.easy.arrays.sorted_array_to_bst

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class SortedArrayToBSTSolution {

    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        fun helper(left: Int, right: Int): TreeNode? {
            if (left > right) return null
            val mid = left + (right - left) / 2
            val node = TreeNode(nums[mid])
            node.left = helper(left, mid - 1)
            node.right = helper(mid + 1, right)
            return node
        }
        return helper(0, nums.lastIndex)
    }

}