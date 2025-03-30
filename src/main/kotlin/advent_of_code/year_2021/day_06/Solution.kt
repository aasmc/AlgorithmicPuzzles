package advent_of_code.year_2021.day_06

import advent_of_code.readTextFromFile

private const val DAYS_TO_CHECK = 80
private const val DAYS_TO_CHECK_PART_TWO = 256

fun main() {
    val numFishPerDayPartOne = parseInput(readTextFromFile("year_2021/day_06", "input.txt"))
    val numFishPerDayPartTwo = numFishPerDayPartOne.clone()
    val partOne = simulateDays(DAYS_TO_CHECK, numFishPerDayPartOne)
    val partTwo = simulateDays(DAYS_TO_CHECK_PART_TWO, numFishPerDayPartTwo)
    println(partOne)
    println(partTwo)
}

private fun parseInput(input: String): LongArray =
    LongArray(9).apply {
        input.split(",").map { it.toInt() }.forEach { this[it] += 1L }
    }

private fun simulateDays(days: Int, fishPerDay: LongArray): Long {
    repeat(days) {
        fishPerDay.rotateLeftByOneInPlace()
        fishPerDay[6] += fishPerDay[8]
    }
    return fishPerDay.sumOf { it }
}

private fun LongArray.rotateLeftByOneInPlace() {
    val leftMost = first()
    this.copyInto(this, startIndex = 1)
    this[this.lastIndex] = leftMost
}



