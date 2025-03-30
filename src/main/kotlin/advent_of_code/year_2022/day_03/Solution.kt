package advent_of_code.year_2022.day_03

import advent_of_code.readLinesFromFile

fun main() {
    val rucksacks = readLinesFromFile("year_2022/day_03", )
        .map(Rucksack::fromString)

    val priorities = rucksacks
        .mapNotNull(Rucksack::findSameItem)
        .sumOf { it.priority }
    println(priorities)

    val groups = rucksacks.chunked(3).map {
        val (first, second, third) = it
        ElfGroup(first, second, third)
    }.mapNotNull(ElfGroup::findSameItem)
        .sumOf { it.priority }
    println(groups)
}

data class ElfGroup(
    val first: Rucksack,
    val second: Rucksack,
    val third: Rucksack
) {
    fun findSameItem(): Item? {
        val firstSet = first.total.toSet()
        val secondSet = second.total.toSet()
        val thirdSet = third.total.toSet()
        val union = firstSet.intersect(secondSet).intersect(thirdSet)
        return union.firstOrNull()
    }
}

data class Rucksack(
    val left: List<Item>,
    val right: List<Item>
) {

    val total = left + right

    fun findSameItem(): Item? {
        return left.intersect(right.toSet()).firstOrNull()
    }

    companion object {
        fun fromString(line: String): Rucksack {
            val chars = line.toCharArray()
            val leftChars = CharArray(chars.size / 2)
            chars.copyInto(
                destination = leftChars,
                startIndex = 0,
                endIndex = chars.size / 2
            )
            val leftItems = leftChars.map { Item(it) }
            val rightChars = CharArray(chars.size / 2)
            chars.copyInto(
                destination = rightChars,
                startIndex = chars.size / 2,
                endIndex = chars.size
            )
            val rightItems = rightChars.map { Item(it) }
            return Rucksack(leftItems, rightItems)
        }
    }
}

data class Item(
    val symbol: Char,
    val priority: Int = if (symbol.isUpperCase()) {
        symbol.code - UPPERCASE_DIFF
    } else {
        symbol.code - LOWERCASE_DIFF
    }
)

const val UPPERCASE_DIFF = 38
const val LOWERCASE_DIFF = 96