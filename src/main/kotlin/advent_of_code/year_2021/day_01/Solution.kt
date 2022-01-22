package advent_of_code.year_2021.day_01

import advent_of_code.readLinesFromFile

fun main() {
    val measurements = readLinesFromFile("year_2021/day_01")
        .map(String::toInt)

    partOneSolution(measurements)
    partTwoSolution(measurements)
}

private fun partTwoSolution(measurements: List<Int>) {
    // this solution is based on the following idea:
    // we need to compare sliding windows of size 3
    // e.g. A - B - C <=> B - C - D
    // To find out which window is larger we simply need to
    // compare A and D, because B and C are the same in both widows

    val res = measurements.windowed(4).count { window ->
        window[0] < window[3]
    }
    println(res)
}

private fun partOneSolution(measurements: List<Int>) {
    val res = measurements.windowed(2).count { (a, b) -> a < b }
    println(res)
}