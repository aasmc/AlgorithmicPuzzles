package advent_of_code.day3

import advent_of_code.readLinesFromFile

fun main() {
    val field = readLinesFromFile("day3", "input.txt")

    val treeCount = solve(field, 3 to 1)
    println(treeCount)

    val vectors = listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)

    val res = vectors.map {
        solve(field, it).toBigInteger()
    }.reduce { a, b ->
        a * b
    }
    println(res)
}

/**
 * Traverses a list of strings, according to an initial configuration parameter
 * given as [vector] of Int to Int, where the first Int means: "Go right the number of rows"
 * and the second Int means: "Go down the number of lines". While traversing the lines,
 * it counts if the necessary position is a tree (#) or a space (.)
 *
 * To comply with the "Go down the number of lines" demand, we check that
 * the index of the string (here marked as [y]) is divisible by the number of lines
 * we are supposed to go down.
 *
 * To comply with the "Go right the number of rows" demand, we:
 *   - first, divide the index by the number of lines we are supposed to skip to prevent from
 *     using incorrect indices. E.g. [vector] is as follows: dx = 1, dy = 2. It means we need to go
 *     down 2 lines and right 1 row. After step one we again need to go down 2 lines and right 1 step.
 *     To achieve this we calculate the right coordinate (x) in the following way:
 *     x = y / dy where dy - is the number of lines we are supposed to skip. That is
 *     for dx = 1, and dy = 2
 *     y = 0, x = 0
 *     y = 1 -> skipped
 *     y = 2, x = 1
 *     y = 3 -> skipped
 *     y = 4, x = 2
 *     etc.
 *   - second, we need to take into account the width of the initial field, that can be repeated
 *     many times, according to the condition of the task. We achieve this by applying the modulo
 *     operation with the width of the initial field.
 */
fun solve(field: List<String>, vector: Pair<Int, Int>): Int {
    val (dx, dy) = vector
    val width = field[0].length
    val treeCount = field.indices.count { y ->
        y % dy == 0 && field[y][y / dy * dx % width] == '#'
    }
    return treeCount
}

































