package yandex_algo_training.year_2023.warmup.rabbit_geometry

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val field = Array<IntArray>(n) {
        IntArray(m)
    }
    repeat(n) { row ->
        val cols = readln().split(" ").map { it.toInt() }.toIntArray()
        field[row] = cols
    }
    solve(field, n, m)
}

private fun solve(dp: Array<IntArray>, rows: Int, cols: Int) {

    val row = rows - 2
    val col = cols - 2
    for (i in row downTo 0) {
        for (j in col downTo 0) {
            if (dp[i][j] == 0) {
                dp[i][j] = 0
            } else {
                dp[i][j] = minOf(dp[i][j + 1], dp[i + 1][j], dp[i + 1][ j + 1]) + dp[i][j]
            }
        }
    }
    var max = Int.MIN_VALUE
    for (arr in dp) {
        for (elem in arr) {
            if (max < elem) {
                max = elem
            }
        }
    }
    println(max)
}