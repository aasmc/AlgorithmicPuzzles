package yandex_algo_training.year_2022.contest05.third

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val dp = Array<IntArray>(n + 1) {
        IntArray(m + 1) { 0 }
    }
    dp[1][1] = 1

    for (i in 2 until dp.size) {
        for (j in 2 until dp[0].size) {
            dp[i][j] = dp[i - 2][j - 1] + dp[i - 1][j - 2]
        }
    }
    println(dp[n][m])
}