package yandex_algo_training.year_2022.contest07.fifth

import java.util.ArrayList

fun main() {
    val nStations = readLine()!!.trim().toInt()
    val nLines = readLine()!!.trim().toInt()
    val metroLines = ArrayList<HashSet<Int>>(nLines)
    repeat(nLines) {
        val description = readLine()!!.trim().split(" ").map { it.toInt() }
        metroLines.add(description.drop(1).toHashSet())
    }
    val (from, to) = readLine()!!.trim().split(" ").map { it.toInt() }

    println(findMinTurns(nLines, metroLines, from, to))
}

fun findMinTurns(
    nLines: Int,
    metroLines: ArrayList<HashSet<Int>>,
    from: Int,
    to: Int
): Int {
    var current = hashSetOf<Int>()
    for (i in 0 until nLines) {
        if (from in metroLines[i]) {
            current.addAll(metroLines[i])
        }
    }

    if (to in current) return 0
    var step = 1
    while (step < nLines) {
        val next = current.toHashSet()
        for (i in 0 until nLines) {
            val candidate = metroLines[i]
            if (current.intersect(candidate).isNotEmpty()) {
                next.addAll(candidate)
            }
        }
        if (to in next) {
            return step
        }
        current = next
        ++step
    }
    return -1
}
