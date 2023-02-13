package yandex_algo_training.contest01.`01gistogram`

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

fun main() {
    val lines = Files.lines(Path.of("input.txt"))
        .collect(Collectors.toList())
    val joined = lines.joinToString(separator = "")
    val (maxCount, map) = countOccurrences(joined)
    val sortedKeys = map.keys.sorted()
    for (row in maxCount  downTo 1) {
        for (sym in sortedKeys) {
            if (map[sym]!! >= row) {
                print("#")
            } else {
                print(" ")
            }
        }
        println()
    }
    println(sortedKeys.joinToString(separator = ""))
}

private fun countOccurrences(joined: String): Pair<Int, Map<Char, Int>> {
    val map = hashMapOf<Char, Int>()
    var maxCount = 0
    joined.forEach { ch ->
        if (ch != ' ' && ch != '\n') {
            map.merge(ch, 1, Int::plus)
            maxCount = maxOf(maxCount, map[ch]!!)
        }
    }
    return maxCount to map
}