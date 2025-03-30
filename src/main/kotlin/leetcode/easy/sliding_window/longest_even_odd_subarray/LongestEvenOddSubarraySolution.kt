package leetcode.easy.sliding_window.longest_even_odd_subarray

class LongestEvenOddSubarraySolution {

    fun longestAlternatingSubarray(nums: IntArray, threshold: Int): Int {
        var left = Int.MIN_VALUE
        for (i in nums.indices) {
            if (nums[i] % 2 == 0 && nums[i] <= threshold) {
                left = i
                break
            }
        }
        if (left == Int.MIN_VALUE) return 0
        var shouldBeOdd = true
        var result = 1
        var right = left + 1
        while (right < nums.size) {
            if ((shouldBeOdd && nums[right] % 2 != 0 && nums[right] <= threshold) ||
                (!shouldBeOdd && nums[right] % 2 == 0 && nums[right] <= threshold)) {
                result = maxOf(result, right - left + 1)
                shouldBeOdd = !shouldBeOdd
                ++right
            } else {
                ++left
                while (left < nums.size && (nums[left] % 2 != 0 || nums[left] > threshold)) {
                    ++left
                }
                right = left + 1
                shouldBeOdd = true
            }
        }
        return result
    }



}