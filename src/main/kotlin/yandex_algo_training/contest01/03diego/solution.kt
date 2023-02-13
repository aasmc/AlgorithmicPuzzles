package yandex_algo_training.contest01.`03diego`

fun main() {
    val diegoNum = readLine()!!.toInt()
    val diegoStickers = readLine()!!.split(" ")
        .map { it.toInt() }.toSet().sorted()
    val numCollectionaires = readLine()!!.toInt()
    val collectionaireStickers = readLine()!!.split(" ").map { it.toInt() }
    val answer = countNeededStickers(collectionaireStickers, diegoStickers)
    answer.forEach { println(it) }
}

private fun countNeededStickers(collectionaireStickers: List<Int>, diegoStickers: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    collectionaireStickers.forEach { s ->
        if (s <= diegoStickers[0]) {
            result.add(0)
        } else if (s > diegoStickers[diegoStickers.lastIndex]) {
            result.add(diegoStickers.size)
        } else {
            val index = leftBinSearch<Int>(0, diegoStickers.size - 1) { idx ->
                diegoStickers[idx] > s
            }

            result.add(index)
        }
    }
    return result
}

fun <T> leftBinSearch(
    left: Int,
    right: Int,
    check: (Int) -> Boolean
): Int {
    var l = left
    var r = right
    while (l < r) {
        val m = l + (r - l) / 2
        if (check(m)) { // acceptable
            r = m
        } else { // not acceptable
            l = m + 1
        }
    }
    return l
}


