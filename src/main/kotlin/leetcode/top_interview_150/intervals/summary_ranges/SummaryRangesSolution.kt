package leetcode.top_interview_150.intervals.summary_ranges

import kotlin.math.abs

class SummaryRangesSolution {

    fun summaryRanges(nums: IntArray): List<String> {
        if (nums.isEmpty()) return emptyList()
        var start = 0
        val result = arrayListOf<String>()
        for (end in 1..nums.lastIndex) {
            if (currentElementIsStartOfNextRange(nums, end)) {
                val interval = if (prevRangeContainsOneElement(start, end)) {
                    "${nums[start]}"
                } else {
                    "${nums[start]}->${nums[end - 1]}"
                }
                result.add(interval)
                start = end
            }
        }
        if (start == nums.lastIndex) {
            result.add("${nums.last()}")
        } else {
            result.add("${nums[start]}->${nums.last()}")
        }
        return result
    }

    private fun prevRangeContainsOneElement(start: Int, end: Int) =
        start + 1 == end

    private fun currentElementIsStartOfNextRange(nums: IntArray, end: Int) =
        diffBetweenCurrentAndPrevious(nums, end) > 1

    private fun diffBetweenCurrentAndPrevious(nums: IntArray, end: Int) =
        abs(nums[end] - nums[end - 1])

}