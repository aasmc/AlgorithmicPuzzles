package leetcode.medium.yandex_prep.longest_subarray_of_ones

class LongestSubarrayOfOnes {

    fun longestSubarray(nums: IntArray): Int {
        var left = 0
        var res = 0
        var zeroes = 0
        for (right in nums.indices) {
            zeroes += if (nums[right] == 1) 0 else 1
            while (zeroes > 1) {
                zeroes -= if (nums[left] == 0) 1 else 0
                ++left
            }
            res = maxOf(res, right - left)
        }
        return res
    }

}