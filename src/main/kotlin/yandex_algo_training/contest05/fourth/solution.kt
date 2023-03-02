package yandex_algo_training.contest05.fourth

fun main() {
    val infinity = 100_000
    val n = readLine()!!.toInt()
    val days = IntArray(n)
    repeat(n) { idx ->
        days[idx] = readLine()!!.toInt()
    }
    val dp = Array<IntArray>(n + 1) {
        IntArray(n + 2) { 0 }
    }

    for (i in dp[0].indices) {
        dp[0][i] = infinity
    }

    for (i in dp.indices) {
        dp[i][dp[0].lastIndex] = infinity
    }


    dp[0][1] = 0

    for (i in 1 until dp.size) {
        for (j in 1 until dp[0].size - 1) {
            if (days[i - 1] <= 100) {
                val buy = dp[i - 1][j] + days[i - 1]
                val coupon = dp[i - 1][j + 1]
                dp[i][j] = minOf(buy, coupon)
            } else {
                val coupon = dp[i - 1][j + 1]
                val buy = dp[i - 1][j - 1] + days[i - 1]
                dp[i][j] = minOf(buy, coupon)
            }
        }
    }

    println()
}