package advent_of_code.day10

import advent_of_code.readLinesFromFile

const val INITIAL_JOLTAGE = 0

fun getAdaptersFromInput(fileName: String = "input.txt"): List<JoltageAdapter> {
    return readLinesFromFile("day10", fileName)
        .map(JoltageAdapter::from)
}

fun getAllAdaptersSorted(initialList: List<JoltageAdapter>): List<JoltageAdapter> {
    val current = initialList.toMutableList()
    current.sortWith(compareBy { it.outputJoltage })
    current.add(JoltageAdapter(current.last().outputJoltage + 3))
    return current
}

fun getDifferences(sortedAdapters: List<JoltageAdapter>): Map<Int, Int> {
    val result = hashMapOf<Int, Int>()
    var currentOutput = INITIAL_JOLTAGE
    sortedAdapters
        .asSequence()
        .forEach { adapter ->
            if (currentOutput in adapter.possibleInputRange()) {
                result.compute(adapter.outputJoltage - currentOutput) { _, value ->
                    if (value == null) {
                        1
                    } else {
                        value + 1
                    }
                }
                currentOutput = adapter.outputJoltage
            }
        }
    return result
}

fun getPermutations(sortedAdapters: List<JoltageAdapter>): Long {
    val permutationsMap = hashMapOf(0 to 1L)
    sortedAdapters
        .forEach { adapter ->
            // the number of possible ways to get to n-th adapter is the sum of all possible ways
            // to get to n-th adapter from n-1, n-2 or n-3 adapters, if they are present of course
            permutationsMap[adapter.outputJoltage] = (1..3).map { prevAdapter ->
                permutationsMap.getOrDefault(adapter.outputJoltage - prevAdapter, 0)
            }.sum()
        }
    return permutationsMap.getValue(sortedAdapters.last().outputJoltage)
}

fun processResult(result: Map<Int, Int>) {
    var out = 0
    if (result[1] == null || result[3] == null) {
        println(out)
        return
    }
    out = result[1]!! * result[3]!!
    println(out)
}

fun main() {
    val input = getAdaptersFromInput()
    val sorted = getAllAdaptersSorted(input)
    val diff = getDifferences(sorted)
    processResult(diff)
    val permutations = getPermutations(sorted)
    println("Permutations: $permutations")
}


















