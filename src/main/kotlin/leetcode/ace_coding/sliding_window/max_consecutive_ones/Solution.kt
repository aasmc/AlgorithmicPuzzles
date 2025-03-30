package leetcode.ace_coding.sliding_window.max_consecutive_ones

fun longestOnes(nums: IntArray, k: Int): Int {
    var maxLength = Int.MIN_VALUE
    var flips = k
    var left = 0
    var right = 0
    while (right < nums.size) {
        if (nums[right] == 1) {
            ++right
            maxLength = maxOf(maxLength, right - left)
        } else {
            if (flips > 0) {
                --flips
                ++right
                maxLength = maxOf(maxLength, right - left)
            } else {
                if (nums[left] == 0) {
                    ++flips
                }
                ++left
            }
        }
    }
    return maxLength
}