package yandex_algo_training.year_2022.contest01.beautifulline02

fun main() {
    val k = readLine()!!.toInt()
    val line = readLine()!!
    println(yandex_algo_training.year_2022.contest01.beautifulline02.calculateAnswer(line, k))
}

fun calculateAnswer(line: String, k: Int): Int {
    var maxLength = 0
    var start = 0
    var end = 0
    var maxFrequency = 0
    val charToFrequency = hashMapOf<Char, Int>()
    while (end < line.length) {
        val current = line[end]
        charToFrequency.merge(current, 1, Int::plus)
        maxFrequency = maxOf(maxFrequency, charToFrequency[current]!!)

        val isNotInWindow = (end - start + 1 - maxFrequency) > k
        if (isNotInWindow) {
            val startChar = line[start]
            charToFrequency.merge(startChar, -1, Int::plus)
            ++start
        }
        maxLength = maxOf(end - start + 1, maxLength)
        ++end
    }
    return maxLength
}