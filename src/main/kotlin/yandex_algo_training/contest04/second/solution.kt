package yandex_algo_training.contest04.second

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val dp = IntArray(n + k)
    for (i in 0 until k) {
        dp[i] = 0
    }
    dp[k] = 1
    for (i in k + 1 until dp.size) {
        var sum = 0
        for (j in 1 .. k) {
            sum += dp[i - j]
        }
        dp[i] = sum
    }
    println(dp[n + k - 1])
}