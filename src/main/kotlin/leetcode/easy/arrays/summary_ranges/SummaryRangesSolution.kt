package leetcode.easy.arrays.summary_ranges

import kotlin.math.abs

class SummaryRangesSolution {

    fun summaryRanges(nums: IntArray): List<String> {
        if (nums.isEmpty()) return emptyList()
        var left = 0
        val result = mutableListOf<String>()
        for (right in 1 until nums.size) {
            if (abs(nums[right] - nums[right - 1]) > 1) {
                // calculate interval
                val interval = if ((right - 1) == left) {
                    "${nums[left]}"
                } else {
                    "${nums[left]}->${nums[right - 1]}"
                }
                result.add(interval)
                left = right
            }
        }

        if (left == nums.lastIndex) {
            result.add("${nums.last()}")
        } else {
            result.add("${nums[left]}->${nums.last()}")
        }
        return result
    }
}