package advent_of_code.year_2021.day_02

import advent_of_code.readLinesFromFile

fun main() {
    val commands = readLinesFromFile("year_2021/day_02")
        .map { line ->
            val (dir, dst) = line.split(" ")
            val direction = when (dir) {
                "forward" -> Direction.FORWARD
                "up" -> Direction.UP
                "down" -> Direction.DOWN
                else -> throw IllegalStateException("Unknown Direction $dir")
            }
            Command(direction, dst.toInt())
        }

    partOne(commands)
    partTwo(commands)
}

fun partTwo(commands: List<Command>) {
    var aim = 0
    var horizontalPosition = 0
    var depth = 0
    commands.forEach { command ->
        when(command.direction) {
            Direction.FORWARD -> {
                horizontalPosition += command.distance
                depth += aim * command.distance
            }
            Direction.UP -> aim -= command.distance
            Direction.DOWN -> aim += command.distance
        }
    }
    println(depth * horizontalPosition)
}

data class Command(
    val direction: Direction,
    val distance: Int
)

enum class Direction {
    FORWARD,
    UP,
    DOWN
}

fun partOne(commands: List<Command>) {
    var horizontalPosition = 0
    var depth = 0
    commands.forEach { command ->
        when(command.direction) {
            Direction.FORWARD -> horizontalPosition += command.distance
            Direction.UP -> depth -= command.distance
            Direction.DOWN -> depth += command.distance
        }
    }
    println(horizontalPosition * depth)
}