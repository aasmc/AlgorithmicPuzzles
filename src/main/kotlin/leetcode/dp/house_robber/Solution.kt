package leetcode.dp.house_robber

fun rob(nums: IntArray): Int {
    if (nums.size == 1) {
        return nums[0]
    }
    if (nums.size == 2) {
        return maxOf(nums[0], nums[1])
    }
    val dp = IntArray(nums.size) { 0 }
    dp[0] = nums[0]
    dp[1] = nums[1]
    for (i in 2 until nums.size) {
        var currentMax = dp[i - 1]
        for (j in i - 2 downTo 0) {
            currentMax = maxOf(currentMax, dp[j] + nums[i])
        }
        dp[i] = currentMax
    }
    return dp[dp.lastIndex]
}