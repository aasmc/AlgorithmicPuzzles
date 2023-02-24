package yandex_algo_training.contest04.fourth

fun main() {
    val n = readLine()!!.trim().toInt()
    val array = Array<IntArray>(n) {
        IntArray(3)
    }
    repeat(n) { idx ->
        val (a, b, c) = readLine()!!.trim().split(" ").map { it.toInt() }
        array[idx][0] = a
        array[idx][1] = b
        array[idx][2] = c
    }
    val dp = IntArray(n)
    dp[0] = array[0][0]
    if (n > 1) {
        dp[1] = minOf(array[0][1], array[0][0] + array[1][0])
    }
    if (n > 2) {
        val a1a2a3 = array[0][0] + array[1][0] + array[2][0]
        val b1a3 = array[0][1] + array[2][0]
        val a1b2 = array[0][0] + array[1][1]
        val c1 = array[0][2]
        val min = minOf(
            a1a2a3,
            b1a3,
            a1b2,
            c1
        )
        dp[2] = min
    }
    for (i in 3 until n) {
        val min = minOf(dp[i - 1] + array[i][0],
            dp[i - 2] + array[i - 1][1],
            dp[i - 3] + array[i - 2][2]
        )
        dp[i] = min
    }
    println(dp[n - 1])
}