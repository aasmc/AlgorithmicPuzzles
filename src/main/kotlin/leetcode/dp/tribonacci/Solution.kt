package leetcode.dp.tribonacci

fun tribonacci(n: Int): Int {
    if (n == 0) return 0
    if (n == 1) return 1
    val dp = IntArray(n + 1) { 0 }
    dp[0] = 0
    dp[1] = 1
    dp[2] = 1
    for (i in 3..n) {
        dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
    }
    return dp[n]
}