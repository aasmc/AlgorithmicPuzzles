package advent_of_code.day1

import advent_of_code.readInputFromFile

fun main() {
    val numbers = readInputFromFile("day1", "input.txt")
        .map(String::toInt)

    // List<T>.associate - specify keys and values to create a map
    // List<T>.associateBy - specify keys, while values are elements of the list
    // List<T>.associateWith - specify values, whiles keys are elements of the list

    // Create a map of Int to Int where Keys are numbers that we need to add to the value to get number 2020
    val pair = numbers.findPairOfSum(2020)
    println(pair)

    println(pair?.let { (x, y) -> x * y })

    // create a map with keys - elements of the list of numbers
    // and values - Pairs of numbers which, if summed with the key, give 2020
    // Map: x -> (y, z) where y + z + x = 2020
    val complementPairs: Map<Int, Pair<Int, Int>?> = numbers.associateWith { x ->
        numbers.findPairOfSum(2020 - x)
    }

    val triple = numbers.findTripleOfSum()
    println(triple)
    println(triple?.let { (x, y, z) -> x * y * z })
}

fun List<Int>.findTripleOfSum(): Triple<Int, Int, Int>? = firstNotNullOfOrNull { x ->
    findPairOfSum(2020 - x)?.let { pair ->
        Triple(x, pair.first, pair.second)
    }
}

fun List<Int>.findPairOfSum(
    sum: Int
): Pair<Int, Int>? {
    val complements = associateBy {
        sum - it
    }
    return firstNotNullOfOrNull { number ->
        complements[number]?.let { complement ->
            Pair(number, complement)
        }
    }
}