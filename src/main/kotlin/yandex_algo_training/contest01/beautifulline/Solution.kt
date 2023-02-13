package yandex_algo_training.contest01.beautifulline

fun main() {
    val k = readLine()!!.toInt()
    val line = readLine()!!
    val chToOccurrences = countOccurrences(line)
    val cnt = maxChToCount(chToOccurrences)
    println(cnt + k)
}


private fun countOccurrences(line: String): Map<Char, Int> {
    val map = hashMapOf<Char, Int>()
    line.forEach { ch ->
        map.merge(ch, 1, Int::plus)
    }
    return map
}

private fun maxChToCount(map: Map<Char, Int>): Int {
    var maxCount = 0
    map.forEach { (ch, cnt) ->
        if (cnt > maxCount) {
            maxCount = cnt
        }
    }
    return maxCount
}