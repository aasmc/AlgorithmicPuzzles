package leetcode.top_interview_150.kadanes.max_subarray

class MaxSubarraySolution {

    fun maxSubArray(nums: IntArray): Int {
        var maxEnding = nums[0]
        var res = nums[0]
        for (i in 1 until nums.size) {
            maxEnding = maxOf(nums[i], maxEnding + nums[i])
            res = maxOf(res, maxEnding)
        }
        return res
    }

}