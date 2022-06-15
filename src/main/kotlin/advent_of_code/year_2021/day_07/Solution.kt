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

private fun solve(positionsToNumberOfCrabs: Map<Int, Int>, fuelCost: (Int) -> Int): Int =
    positionsToNumberOfCrabs.keys.asRange().minOf { targetPosition ->
        positionsToNumberOfCrabs.map { (currentPosition, crabCount) ->
            // how much fuel is needed for all crabs in current position to move to the target position
            fuelCost((targetPosition - currentPosition).absoluteValue) * crabCount
        }.sum()
    }
