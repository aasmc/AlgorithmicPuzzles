package advent_of_code.year_2021.day_05

import advent_of_code.readLinesFromFile
import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {
    val lines =
        readLinesFromFile("year_2021/day_05", "input.txt")
            .map { Line.fromString(it) }

    println(solvePartOne(lines))
    println(solvePartTwo(lines))

}

fun solvePartOne(lines: List<Line>): Int {
    val onlyHorizontalOrVertical: (line: Line) -> Boolean = { line ->
        line.x1 == line.x2 || line.y1 == line.y2
    }

    val filtered = lines.filter(onlyHorizontalOrVertical)

    val points = filtered.flatMap {
        it.pointsOnLine()
    }

    return points.groupBy { it }
        .count { it.value.size > 1 }
}

fun solvePartTwo(lines: List<Line>): Int {
    val points = lines.flatMap {
        it.pointsOnLine()
    }
    return points.groupBy { it }
        .count { it.value.size > 1 }
}

data class Point(
    val x: Int,
    val y: Int
)

data class Line(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int
) {

    fun pointsOnLine(): List<Point> {
        // these two lines compute whether we need to add or subtract or do nothing
        // to move one point along x or y axis in the direction from x1 to x2 | y1 to y2
        val xDelta = (x2 - x1).sign
        val yDelta = (y2 - y1).sign

        // since we know that lines are either vertical, or horizontal, or diagonal at a degree of
        // 45%, we can safely compute the number of steps we need to create a line with points
        // by computing the max difference between x1, x2 or y1, y2
        val steps = maxOf((x1 - x2).absoluteValue, (y1 - y2).absoluteValue)
        // Here we use:
        // public inline fun <T, R> Iterable<T>.scan(initial: R, operation: (acc: R, T) -> R): List<R> {
        //    return runningFold(initial, operation)
        //}
        // because we want to save intermediate results in the list as well, and not just reduce
        // the line to some final result
        // We start from x1, y1 and move one step at a time by adding xDelta and yDelta
        // thus creating a line with Points
        return (1..steps).scan(Point(x1, y1)) { next, _ ->
            Point(next.x + xDelta, next.y + yDelta)
        }
    }

    companion object {
        fun fromString(line: String): Line {
            val (start, end) = line.split(" -> ")
            val (x1, y1) = start.split(",").map { it.toInt() }
            val (x2, y2) = end.split(",").map { it.toInt() }
            return Line(x1, y1, x2, y2)
        }
    }
}