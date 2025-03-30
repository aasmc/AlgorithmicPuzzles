package advent_of_code.year_2022.day_06

import advent_of_code.readLinesFromFile

fun main() {
    val chars = readLinesFromFile("year_2022/day_06", )
        .first()
        .toCharArray()

    val signals = chars.toList()

    println(findPositionOfMarker(14, signals))
}

fun findPositionOfMarker(markerLength: Int, signals: List<Char>): Int {
    val windows = signals.windowed(markerLength, 1)
    var result = markerLength
    for(i in windows.indices) {
        val window = windows[i]
        if (areAllSignalsDifferent(window)) {
            result = i + markerLength
            break
        }
    }
    return result
}

private fun areAllSignalsDifferent(window: List<Char>): Boolean {
    return window.toSet().size == window.size
}