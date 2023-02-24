package yandex_algo_training.contest04.third

fun main() {
    val n = readLine()!!.trim().toInt()
    val dp = IntArray(n + 1) {0}
    val prev = mutableListOf<Int>()

    for (i in 2..n) {
        var min = dp[i - 1] + 1
        if (i % 2 == 0) {
            min = minOf(min, dp[i / 2] + 1)
        }
        if (i % 3 == 0) {
            min = minOf(min, dp[i / 3] + 1)
        }
        dp[i] = min
    }
    var i = n
    prev.add(n)
    while (i > 1) {
        if (dp[i] == dp[i - 1] + 1) {
            prev.add(i - 1)
            --i
        } else if (i % 2 == 0 && dp[i] == dp[i / 2] + 1) {
            prev.add(i / 2)
            i /= 2
        } else {
            prev.add(i / 3)
            i /= 3
        }
    }
    println(dp.last())
    println(prev.reversed().joinToString(separator = " "))
}