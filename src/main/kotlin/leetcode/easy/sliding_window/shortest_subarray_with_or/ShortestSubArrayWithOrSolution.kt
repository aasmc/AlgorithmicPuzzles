package leetcode.easy.sliding_window.shortest_subarray_with_or

class ShortestSubArrayWithOrSolution {

    fun minimumSubarrayLength(nums: IntArray, k: Int): Int {
        var result = Int.MAX_VALUE
        for (left in nums.indices) {
            var sum = 0
            for (right in left..nums.lastIndex) {
                sum = sum or nums[right]
                if (sum >= k) {
                    result = minOf(result, right - left + 1)
                }
            }
        }

        return if (result == Int.MAX_VALUE) -1 else result
    }

}