package leetcode.top_interview_150.sliding_window.minimum_size_subarray_sum

class MinSizeBSubarraySumSolution {

    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var sum = 0
        var result = Int.MAX_VALUE
        var left = 0
        for (right in nums.indices) {
            sum += nums[right]
            while (sum >= target) {
                result = minOf(result, right - left + 1)
                sum -= nums[left++]
            }
        }
        return if (result == Int.MAX_VALUE) 0 else result
    }

}