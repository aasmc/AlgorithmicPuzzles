package leetcode.medium.yandex_prep.continuous_subarray_sum

class ContinuousSubarraySumSolution {

    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        val remainderToIndex = hashMapOf<Int, Int>()
        var sum = 0
        for((idx, num) in nums.withIndex()) {
            sum += num
            val remainder = sum % k
            if (remainder == 0 && idx > 0) return true
            if (remainder !in remainderToIndex) {
                remainderToIndex[remainder] = idx
            } else {
                if (idx - remainderToIndex[remainder]!! > 1) {
                    return true
                }
            }
        }
        return false
    }

}