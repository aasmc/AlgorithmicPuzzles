package yandex_algo_training.contest05.second

import java.lang.StringBuilder

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val dp = Array<IntArray>(n + 1) {
        IntArray(m + 1) { 0 }
    }
    repeat(n) { row ->
        val colNums = readLine()!!.split(" ").map { it.toInt() }
        for (col in colNums.indices) {
            dp[row + 1][col + 1] = colNums[col]
        }
    }

    for (i in 1 until dp.size) {
        for (j in 1 until dp[0].size) {
            val max = maxOf(dp[i - 1][j], dp[i][j - 1])
            dp[i][j] = dp[i][j] + max
        }
    }

    val sb = StringBuilder()
    var row = dp.size - 1
    var col = dp[0].size - 1
    while (row >= 1 && col >= 1) {
        if (row == 1 && col == 1) break
        val current = dp[row][col]
        if (current - dp[row - 1][col] <= current - dp[row][col - 1]) {
            sb.append("D").append(" ")
            --row
        } else {
            sb.append("R").append(" ")
            --col
        }
    }
    val reversed = sb.reversed()
    println(dp[n][m])
    println(reversed.trim())
}