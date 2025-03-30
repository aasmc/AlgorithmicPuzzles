package leetcode.dp.min_cost_stairs

fun minCostClimbingStairs(cost: IntArray): Int {
    val dp = IntArray(cost.size + 1) { 0 }
    dp[0] = cost[0]
    dp[1] = minOf(cost[1], cost[1] + cost[0])
    for (i in 2 until cost.size) {
        dp[i] = minOf(dp[i - 1] + cost[i], dp[i - 2] + cost[i])
    }
    dp[dp.lastIndex] = minOf(dp[dp.lastIndex - 1], dp[dp.lastIndex - 2])
    return dp[dp.lastIndex]
}