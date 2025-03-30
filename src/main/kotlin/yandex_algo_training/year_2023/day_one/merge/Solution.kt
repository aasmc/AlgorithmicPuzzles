package yandex_algo_training.year_2023.day_one.merge

fun main() {
    val n = readln().toInt()

    val first = if (n != 0) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    } else {
        readln()
        intArrayOf()
    }
    val m = readln().toInt()
    val second = if (m != 0) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    } else {
        readln()
        intArrayOf()
    }
    val result = IntArray(n + m)
    merge(first, second, result)
    println(result.joinToString(separator = " "))
}

private fun merge(first: IntArray,
                  second: IntArray,
                  to: IntArray) {
    mergeHelper(first, 0, first.size, second, 0, second.size, to, 0)
}

private fun mergeHelper(first: IntArray,
                        firstFrom: Int,
                        firstTo: Int,
                        second: IntArray,
                        secondFrom: Int,
                        secondTo: Int,
                        to: IntArray,
                        toStart: Int) {
    var idx = toStart
    var firstIdx = firstFrom
    var secondIdx = secondFrom
    while (firstIdx < firstTo && secondIdx < secondTo) {
        if (first[firstIdx] <= second[secondIdx]) {
            to[idx++] = first[firstIdx++]
        } else {
            to[idx++] = second[secondIdx++]
        }
    }

    while (firstIdx < firstTo) {
        to[idx++] = first[firstIdx++]
    }

    while (secondIdx < secondTo) {
        to[idx++] = second[secondIdx++]
    }
}