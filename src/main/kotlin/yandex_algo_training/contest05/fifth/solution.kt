package yandex_algo_training.contest05.fifth

fun main() {
    val n = readLine()!!.trim().toInt()
    val first = readLine()!!.trim().split(" ").map { it.toInt() }
    val m = readLine()!!.trim().toInt()
    val second = readLine()!!.trim().split(" ").map { it.toInt() }
    val width = n + 1
    val height = m + 1
    val dp = Array<IntArray>(width) {
        IntArray(height) { 0 }
    }
    for (i in 1 until width) {
        for (j in 1 until height) {
            if (first[i - 1] == second[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1
            } else {
                dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    val res = mutableListOf<Int>()
    var i = first.size
    var j = second.size
    while (i != 0 && j != 0) {
        if (first[i - 1] == second[j - 1]) {
            res.add(first[i - 1])
            --i
            --j
        } else if (dp[i][j - 1] > dp[i - 1][j]) {
            --j
        } else {
            --i
        }
    }
    res.reverse()
    val answer = res.joinToString(separator = " ")
    println(answer)
}