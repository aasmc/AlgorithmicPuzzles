package yandex_algo_training.year_2023.warmup.contest_results

fun main() {
    val a = readln().toInt()
    val b = readln().toInt()
    val n = readln().toInt()
    solve(a, b, n)
}

private fun solve(a: Int, b: Int, n: Int) {
    val minB = if (b % n == 0) b / n else b / n + 1

    if (a > minB) {
        println("Yes")
    } else {
        println("No")
    }
}