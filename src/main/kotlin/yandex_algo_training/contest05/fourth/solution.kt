package yandex_algo_training.contest05.fourth

fun main() {
    val n = readLine()!!.toInt()
    val days = IntArray(n)
    repeat(n) { idx ->
        days[idx] = readLine()!!.toInt()
    }
    val dp = Array<IntArray>(n + 2) {
        IntArray(n + 2) { 0 }
    }
    for (i in 1..n) {
        dp[i][0] = days[i - 1]
    }
    for (i in 1..n) {
        dp[0][i] = i
    }
    for (j in 1 until dp.size - 1) {
        for (i in 1 until dp[0].size - 1) {
            val min = minOf(dp[i - 1][j + 1], dp[i - 1][j - 1], dp[i - 1][j])
            dp[i][j] = min
        }
    }
    println()
}