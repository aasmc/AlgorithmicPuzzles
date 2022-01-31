package advent_of_code.year_2021.day_03

import advent_of_code.readLinesFromFile

fun main() {
    val input =
        readLinesFromFile("year_2021/day_03")

    partOne(input)
    partTwo(input)
}

fun partOne(input: List<String>) {
    val columns = input[0].indices
    val gammaRate = buildString {
        for (column in columns) {
            val (ones, zeroes) = input.countBitsInColumn(column)
            val commonBit = if (zeroes > ones) "0" else "1"
            append(commonBit)
        }
    }
    val epsilonRate = gammaRate.invertBinaryString()
    val result = gammaRate.toInt(2) * epsilonRate.toInt(2)
    println(result)
}

private fun String.invertBinaryString() = this
    .asIterable()
    .joinToString("") { if (it == '0') "1" else "0" }

fun partTwo(input: List<String>) {
    fun rating(type: RatingType): String {
        val columns = input[0].indices
        var candidates = input
        for (column in columns) {
            val (ones, zeroes) = candidates.countBitsInColumn(column)
            val mostCommon = if (ones >= zeroes) '1' else '0'
            candidates = candidates.filter {
                when (type) {
                    RatingType.OXYGEN -> it[column] == mostCommon
                    RatingType.CO2 -> it[column] != mostCommon
                }
            }
            if (candidates.size == 1) break
        }
        return candidates.single()
    }
    val result = rating(RatingType.OXYGEN).toInt(2) * rating(RatingType.CO2).toInt(2)
    println(result)
}

private enum class RatingType {
    OXYGEN, CO2
}

private data class BitCount(val ones: Int, val zeroes: Int)

private fun List<String>.countBitsInColumn(column: Int): BitCount {
    var zeroes = 0
    var ones = 0
    for (line in this) {
        if (line[column] == '0') ++zeroes
        if (line[column] == '1') ++ones
    }
    return BitCount(ones, zeroes)
}