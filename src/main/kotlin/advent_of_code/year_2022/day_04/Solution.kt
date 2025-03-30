package advent_of_code.year_2022.day_04

import advent_of_code.readLinesFromFile

fun main() {
    val ranges = readLinesFromFile("year_2022/day_04", )
        .map(RangeHolder::fromString)

    val overlaps = ranges
        .count(RangeHolder::fullyOverlaps)

    println(overlaps)

    val partialOverlaps = ranges
        .count(RangeHolder::partiallyOverlaps)

    println(partialOverlaps)
}

data class RangeHolder(
    val left: IntRange,
    val right: IntRange
) {

    fun partiallyOverlaps(): Boolean {
        return left.first in right || left.last in right ||
                right.first in left || right.last in left
    }

    fun fullyOverlaps(): Boolean {
        return (left.first in right && left.last in right) ||
                (right.first in left && right.last in left)
    }

    companion object {
        fun fromString(line: String): RangeHolder {
            val (left, right) = line.split(",")
            val leftRange = convertToRange(left)
            val rightRange = convertToRange(right)
            return RangeHolder(leftRange, rightRange)
        }

        private fun convertToRange(line: String): IntRange {
            val (lFrom, lTo) = line.split("-")
            val from = lFrom.toInt()
            val to = lTo.toInt()
            return from..(to)
        }
    }
}

