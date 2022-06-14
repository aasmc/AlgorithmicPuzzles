package advent_of_code.year_2021.day_07

import advent_of_code.readTextFromFile
import kotlin.math.absoluteValue

fun main() {
    val positions = readTextFromFile("year_2021/day_07", "input.txt")
        .split(",").map { it.toInt() }

    val positionToNumCrabs = positions.groupingBy { it }.eachCount()



    println(solve(positionToNumCrabs) { it })
    println(solve(positionToNumCrabs) { distance ->
        (distance * (distance + 1)) / 2
    })
}

private fun Set<Int>.asRange(): IntRange =
    this.minOf { it }..this.maxOf { it }

private fun solve(crabs: Map<Int, Int>, fuelCost: (Int) -> Int): Int =
    crabs.keys.asRange().minOf { target ->
        crabs.map { (crab, crabCount) ->
            fuelCost((target - crab).absoluteValue) * crabCount
        }.sum()
    }
