package leetcode.top_interview_150.divide_and_conquer.convert_sorted_arr_to_bst

class ConvertSortedArrToBstSolution {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        fun construct(left: Int, right: Int): TreeNode? {
            if (left > right) return null
            val mid = left + (right - left) / 2
            val node = TreeNode(nums[mid])
            node.left = construct(left, mid - 1)
            node.right = construct(mid + 1, right)
            return node
        }
        return construct(0, nums.lastIndex)
    }

}