package yandex_algo_training.contest01.lecture10

fun main() {
    val line = readLine()!!
    val ans = getAnswer(line)
    val sorted = ans.keys.sorted()
    for (k in sorted) {
        println("$k: ${ans[k]}")
    }
}

private fun getAnswer(line: String): Map<Char, Long> {
    val charToCount = hashMapOf<Char, Long>()
    for ((idx, ch) in line.withIndex()) {
        val before = idx + 1L
        val after = line.length - idx
        val count = before * after
        charToCount.merge(ch, count, Long::plus)
    }
    return charToCount
}