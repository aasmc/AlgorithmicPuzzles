package leetcode.easy.arrays.summary_ranges

class SummaryRangesSolution {

    fun summaryRanges(nums: IntArray): List<String> {
        if (nums.isEmpty()) return emptyList()
        if (nums.size == 1) {
            return listOf(nums[0].toString())
        }
        var left = 0
        val result = arrayListOf<String>()
        for (right in 1 until nums.size) {
            if (nums[right] != nums[right - 1] + 1) {
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