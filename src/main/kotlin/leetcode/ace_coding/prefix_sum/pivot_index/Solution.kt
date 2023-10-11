package leetcode.ace_coding.prefix_sum.pivot_index

fun pivotIndex(nums: IntArray): Int {
    var prefix = 0
    val prefixes = IntArray(nums.size)
    for (i in nums.indices) {
        prefixes[i] = prefix
        prefix = nums[i] + prefixes[i]
    }
    var leftMostIndex = -1
    var suffix = 0
    for (i in nums.lastIndex downTo 0) {
        if (prefixes[i] == suffix) {
            leftMostIndex = i
        }
        suffix += nums[i]
    }
    return leftMostIndex
}