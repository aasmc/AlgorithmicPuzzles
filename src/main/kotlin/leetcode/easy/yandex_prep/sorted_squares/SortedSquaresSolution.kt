package leetcode.easy.yandex_prep.sorted_squares

import kotlin.math.abs

class SortedSquaresSolution {

    fun sortedSquares(nums: IntArray): IntArray {
        val result = IntArray(nums.size)
        var start = 0
        var end = nums.lastIndex
        for (idx in nums.lastIndex downTo 0) {
            if (abs(nums[start]) > abs(nums[end])) {
                result[idx] = nums[start] * nums[start]
                ++start
            } else {
                result[idx] = nums[end] * nums[end]
                --end
            }
        }
        return result
    }

}