package advent_of_code.year_2021.day_05

import advent_of_code.readLinesFromFile

fun main() {
    val lines =
        readLinesFromFile("year_2021/day_05", "input.txt")
            .map { Line.fromString(it) }

    println(solvePartOne(lines))

}

fun solvePartOne(lines: List<Line>): Int {
    val lineFilter: (line: Line) -> Boolean = { line ->
        line.x1 == line.x2 || line.y1 == line.y2
    }

    val filtered = lines.filter(lineFilter)

    fun points(): List<Point> {
        val points = mutableListOf<Point>()
        filtered.forEach { line ->
            val (x1, y1, x2, y2) = line
            val vertical = x1 == x2
            val horizontal = y1 == y2
            when {
                horizontal -> {
                    val min = minOf(x1, x2)
                    val max = maxOf(x1, x2)
                    for(i in min..max) {
                        points.add(Point(i, y1))
                    }
                }
                vertical -> {
                    val min = minOf(y1, y2)
                    val max = maxOf(y1, y2)
                    for (i in min..max) {
                        points.add(Point(x1, i))
                    }
                }
            }
        }
        return points
    }

    val points = points()
    return points.groupBy { it }.count {
        it.value.size >= 2
    }
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
    companion object {
        fun fromString(line: String): Line {
            val (start, end) = line.split(" -> ")
            val (x1, y1) = start.split(",").map { it.toInt() }
            val (x2, y2) = end.split(",").map { it.toInt() }
            return Line(x1, y1, x2, y2)
        }
    }
}