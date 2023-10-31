package yandex_algo_training.year_2022.contest04.fifth

fun main() {
    val n = readLine()!!.toInt()
    val coords = readLine()!!.trim().split(" ")
        .map { it.toInt() }.sorted()

    val ans = if (n > 2) {
        solution(coords, n)
    } else {
        coords[1] - coords[0]
    }
    println(ans)
}

fun solution(coords: List<Int>, n: Int): Int {
    val dp = IntArray(n + 1)
    dp[2] = coords[1] - coords[0]
    dp[3] = coords[2] - coords[0]
    for (i in 4..n) {
        dp[i] = minOf(dp[i - 1], dp[i - 2]) + coords[i - 1] - coords[i - 2]
    }
    return dp[n]
}