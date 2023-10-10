package leetcode.ace_coding.sliding_window.max_average_subarray

fun findMaxAverage(nums: IntArray, k: Int): Double {
    if (k >= nums.size) {
        return nums.sumOf { it } * 1.0 / nums.size
    }
    var sum = nums.take(k).sumOf { it }
    var max = sum * 1.0 / k
    for (i in k until nums.size) {
        sum -= nums[i - k]
        sum += nums[i]
        max = maxOf(max, sum * 1.0 / k)
    }
    return max
}