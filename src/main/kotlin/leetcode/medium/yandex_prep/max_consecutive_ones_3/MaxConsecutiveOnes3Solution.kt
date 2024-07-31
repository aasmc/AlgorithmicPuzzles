package leetcode.medium.yandex_prep.max_consecutive_ones_3

class MaxConsecutiveOnes3Solution {

    fun longestOnes(nums: IntArray, k: Int): Int {

        var res = -1
        var flipsCount = k
        var left = 0
        var right = 0

        while (right < nums.size) {
            if (nums[right] == 1) {
                res = maxOf(res, right - left + 1)
                ++right
            } else {
                if (flipsCount > 0) {
                    --flipsCount
                    res = maxOf(res, right - left + 1)
                    ++right
                } else {
                    if (nums[left] == 0) {
                        ++flipsCount
                    }
                    ++left
                }
            }
        }

        return res
    }

}