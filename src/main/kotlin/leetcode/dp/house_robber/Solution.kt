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
    dp[1] = maxOf(nums[0], nums[1])
    for (i in 2 until nums.size) {
        val prevMax = dp[i - 1]
        val prevPrevMax = dp[i - 2]
        dp[i] = maxOf(prevMax, prevPrevMax + nums[i])
    }
    return dp[dp.lastIndex]
}

fun rob2(nums: IntArray): Int {
    var rob1 = 0; var rob2 = 0

    for (house in nums) {
        val temp = maxOf(rob1 + house, rob2)
        rob1 = rob2
        rob2 = temp
    }

    return rob2
}