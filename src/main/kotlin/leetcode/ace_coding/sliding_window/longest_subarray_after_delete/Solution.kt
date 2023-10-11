package leetcode.ace_coding.sliding_window.longest_subarray_after_delete

fun longestSubarray(nums: IntArray): Int {
    var zeroCount = 0
    var maxSize = 0

    var windowStart = 0
    var windowEnd = 0

    while (windowEnd < nums.size) {
        zeroCount += if (nums[windowEnd] == 1) 0 else 1
        while (zeroCount > 1) {
            zeroCount -= if (nums[windowStart++] == 0) 1 else 1
        }
        maxSize = maxOf(maxSize, windowEnd - windowStart)
        ++windowEnd
    }
    return maxSize;
}