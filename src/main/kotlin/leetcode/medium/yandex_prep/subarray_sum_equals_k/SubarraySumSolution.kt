package leetcode.medium.yandex_prep.subarray_sum_equals_k

class SubarraySumSolution {

    fun subarraySum(nums: IntArray, k: Int): Int {
        var count = 0
        val prefixSumToCount = hashMapOf(0 to 1)
        var sum = 0
        for (num in nums) {
            sum += num
            val prefix = sum - k
            if (prefixSumToCount.containsKey(prefix)) {
                count += prefixSumToCount[prefix]!!
            }
            prefixSumToCount.merge(sum, 1, Int::plus)
        }
        return count
    }

}