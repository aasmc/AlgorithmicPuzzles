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
    val lineFilter: (line: Line) -> Boolean = { line ->
        line.x1 == line.x2 || line.y1 == line.y2
    }

    val filtered = lines.filter(lineFilter)

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
        val xDelta = (x2 - x1).sign
        val yDelta = (y2 - y1).sign

        val steps = maxOf((x1 - x2).absoluteValue, (y1 - y2).absoluteValue)
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