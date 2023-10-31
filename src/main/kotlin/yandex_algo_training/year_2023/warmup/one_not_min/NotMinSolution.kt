package yandex_algo_training.year_2023.warmup.one_not_min

fun main() {

    val (m, n) = readln().split(" ").map { it.toInt() }
    val sequence = readln().split(" ").map { it.toInt() }
    repeat(n) {
        val (from, to) = readln().split(" ").map { it.toInt() }
        solve(sequence, from, to)
    }
}

private fun solve(sequence: List<Int>, from: Int, to: Int) {
    val found = find(sequence, from, to + 1)
    if (found != to + 1) {
        println(sequence[found])
    } else {
        println("NOT FOUND")
    }
}


private fun find(sequence: List<Int>, from: Int, to: Int): Int {
    var minIdx = from
    for (i in from until to) {
        if (sequence[i] < sequence[minIdx]) {
            minIdx = i
        }
    }
    for (i in from until  to) {
        if (sequence[i] != sequence[minIdx]) {
            return i
        }
    }
    return to
}