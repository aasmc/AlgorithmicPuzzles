package leetcode.easy.sliding_window.max_average_subarray

class MaxAverageSubarraySolution {

    fun findMaxAverage(nums: IntArray, k: Int): Double {
        if (nums.isEmpty()) return 0.0
        var left = 0
        var sum = nums[left]
        for (right in 1 until k) {
            sum += nums[right]
        }
        var average = sum * 1.0 / k
        for (right in k until nums.size) {
            sum -= nums[left++]
            sum += nums[right]
            average = maxOf(average, sum * 1.0 / k)
        }
        return average
    }

    fun findMaxAverage2(nums: IntArray, k: Int): Double {
        var sum = nums.take(k).sumOf { it }
        var average = sum * 1.0 / k
        for (i in k until nums.size) {
            sum -= nums[i - k]
            sum += nums[i]
            average = maxOf(average, sum * 1.0 / k)
        }
        return average
    }

}