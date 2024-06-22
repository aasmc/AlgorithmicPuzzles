package leetcode.easy.sliding_window.min_difference_between_highest_lowest_scores

class MinDiffScoresSolution {
    fun minimumDifference(nums: IntArray, k: Int): Int {
        nums.sort()
        var res = Int.MAX_VALUE
        var left = 0
        var right = k - 1
        while (right < nums.size) {
            res = minOf(res, nums[right++] - nums[left++])
        }
        return res
    }
}