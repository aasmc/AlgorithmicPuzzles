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

fun tribonacci2(n: Int): Int {
    if (n == 0) return 0
    if (n == 1) return 1

    var a = 0
    var b = 1
    var c = 1
    for (i in 3 .. n) {
        val tmp = a + b + c
        a = b
        b = c
        c = tmp
    }
    return c
}