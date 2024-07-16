package leetcode.medium.prefix_sum.contiguous_array

class ContiguousArraySolution {

    fun findMaxLength(nums: IntArray): Int {
        val diffToIndex = hashMapOf<Int, Int>()
        var res = 0
        var zeroCnt = 0
        var oneCnt = 0
        for ((idx, num) in nums.withIndex()) {
            if (num == 0) {
                ++zeroCnt
            } else {
                ++oneCnt
            }
            val diff = oneCnt - zeroCnt
            if (diff !in diffToIndex) {
                diffToIndex[diff] = idx
            }
            res = if (oneCnt == zeroCnt) {
                idx + 1
            } else {
                maxOf(res, idx - diffToIndex[diff]!!)
            }
        }
        return res
    }

}