package yandex_algo_training.contest01.diego03

fun main() {
    val diegoNum = readLine()!!.toInt()
    val diegoStr = readLine()!!.trim()
    val diegoStickers = if (diegoNum == 0) {
        emptyList<Int>()
    } else {
        diegoStr.split(" ").toSet().map { it.toInt() }.sorted()
    }
    val numCollectionaires = readLine()!!.toInt()
    val collectionairesStr = readLine()!!
    val collectionaireStickers = if (numCollectionaires == 0) {
        emptyList<Int>()
    } else {
        collectionairesStr.split(" ").map { it.toInt() }
    }
    val answer = countNeededStickers(collectionaireStickers, diegoStickers)
    answer.forEach { println(it) }
}

fun countNeededStickers(
    collectionaireStickers: List<Int>,
    diegoStickers: List<Int>
): List<Int> {
    if (collectionaireStickers.isEmpty()) return emptyList()
    if (diegoStickers.isEmpty()) {
        return List(collectionaireStickers.size) { 0 }
    }
    val result = mutableListOf<Int>()
    collectionaireStickers.forEach { s ->
        if (s > diegoStickers[diegoStickers.lastIndex]) {
            result.add(diegoStickers.size)
        } else {
            val index = leftBinSearch<Int>(0, diegoStickers.size - 1) { idx ->
                diegoStickers[idx] >= s
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

