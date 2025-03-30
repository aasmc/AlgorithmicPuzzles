package leetcode.easy.prefix_sum.range_sum_query


class NumArray(nums: IntArray) {

    private val prefixSums = IntArray(nums.size + 1)

    init {
        for (i in 1 until prefixSums.size) {
            prefixSums[i] = nums[i - 1] + prefixSums[i - 1]
        }
    }

    fun sumRange(left: Int, right: Int): Int {
        return prefixSums[right + 1] - prefixSums[left]
    }

}

