package leetcode.dp.climbing_stairs

fun climbStairs(n: Int): Int {
    val dp = IntArray(n + 1) { 0 }
    dp[0] = 1
    dp[1] = 2
    for (i in 2 until n) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }
    return dp[n - 1]
}

fun climbStairs2(n: Int): Int {
    if (n == 1) return 1
    var a = 1
    var b = 2
    for (i in 2 until n) {
        val tmp = a + b
        a = b
        b = tmp
    }
    return b
}

