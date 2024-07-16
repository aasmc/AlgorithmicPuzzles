package leetcode.medium.prefix_sum.contiguous_subarray_sum

class ContiguousSubArraySumSolution {

    fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
        // intuition:
        // for every prefix sum we check if there already has been a remainder
        // of the division of the prefix sum by k. If there is such a remainder,
        // it means that we previously had a prefix sum whose remainder was equal
        // to the current prefix sum's remainder, and that means that we have added
        // a multiple of k to the previous prefix sum. So this means that there is
        // a contiguous subarray whose sum is a multiple of k.
        // E.g. nums = [23, 2, 4, 6, 7], k = 6
        // we iterate of the numbers
        // prefix sum = 23
        // remainder = 23 % 6 = 5, we never had this remainder
        // prefix sum = 23 + 2 = 25
        // remainder = 25 % 6 = 1, we never had this remainder
        // prefix sum = 25 + 4 = 29
        // remainder = 29 % 6 = 5, we already had this remainder, and this means
        // that since the previous remainder we have added a multiple of k to the prefix sum

        val remainderToIndex = hashMapOf<Int, Int>(0 to -1)
        var sum = 0
        for ((idx, num) in nums.withIndex()) {
            sum += num
            val remainder = sum % k
            if (remainder !in remainderToIndex) {
                remainderToIndex[remainder] = idx
            } else if (idx - remainderToIndex[remainder]!! > 1) {
                return true
            }
        }
        return false
    }

}