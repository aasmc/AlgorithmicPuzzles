package yandex_algo_training.year_2022.contest05.first

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val dp = Array<IntArray>(n + 1) {
        IntArray(m + 1) { Int.MAX_VALUE }
    }
    repeat(n) { row ->
        val colNums = readLine()!!.split(" ").map { it.toInt() }
        for (col in colNums.indices) {
            dp[row + 1][col + 1] = colNums[col]
        }
    }
    dp[0][0] = 0
    dp[0][1] = 0
    dp[1][0] = 0
    for (i in 1 until dp.size) {
        for (j in 1 until dp[0].size) {
            val min = minOf(dp[i][j - 1], dp[i - 1][j])
            dp[i][j] = min + dp[i][j]
        }
    }
    println(dp[n][m])
}