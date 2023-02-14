package yandex_algo_training.contest01.goodstring05

fun main() {
    val cnt = readLine()!!.toInt()
    val letters = mutableListOf<Int>()
    repeat(cnt) {
        letters.add(readLine()!!.toInt())
    }
    val goodness = findMaxGoodness(cnt, letters)
    println(goodness)
}

fun findMaxGoodness(cnt: Int, letters: List<Int>): Int {
    if (cnt == 1) return 0
    var maxCnt = 0
    var prev = letters[0]
    for (i in 1 until letters.size) {
        val current = letters[i]
        maxCnt += minOf(prev, current)
        prev = current
    }
    return maxCnt
}