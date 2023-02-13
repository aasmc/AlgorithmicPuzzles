package yandex_algo_training.contest01.diego

fun main() {
    val diegoNum = readLine()!!.toInt()
    val diegoStickers = readLine()!!.split(" ")
        .map { it.toInt() }.sorted()
    val numCollectionaires = readLine()!!.toInt()
    val collectionaireStickers = readLine()!!.split(" ").map { it.toInt() }
    val answer = countNeededStickers(collectionaireStickers, diegoStickers)
    answer.forEach { println(it) }
}

private fun countNeededStickers(collectionaireStickers: List<Int>, diegoStickers: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    collectionaireStickers.forEach { s ->
        if (s < diegoStickers[0]) {
            result.add(0)
        } else if (s > diegoStickers[diegoStickers.lastIndex]) {
            result.add(diegoStickers.size)
        } else {
            val index = rightBinSearch(0, diegoStickers.size - 1, emptyList<Int>()) { idx, _ ->
                diegoStickers[idx] > s
            }

            result.add(index)
        }
    }
    return result
}

fun <T> rightBinSearch(
    left: Int,
    right: Int,
    checkParams: List<T>,
    check: (Int, List<T>) -> Boolean
): Int {
    var l = left
    var r = right
    while (l < r) {
        // round to upper
        val m = l + 1 + (r - l) / 2
        if (check(m, checkParams)) { // acceptable
            l = m
        } else { // not acceptable
            r = m - 1
        }
    }
    return l
}


