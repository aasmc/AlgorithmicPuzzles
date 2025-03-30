package advent_of_code.year_2023.day2

import advent_of_code.readLinesFromFile

fun main() {
    val games = readLinesFromFile("year_2023/day2", "input.txt")
        .map { Game.fromString(it) }
    println(solvePartTwo(games))
}

private fun solvePartTwo(games: List<Game>): Int {
    return games.map { game ->
        var minBlue = Int.MIN_VALUE
        var minRed = Int.MIN_VALUE
        var minGreen = Int.MIN_VALUE
        game.cubeSets.forEach { set ->
            set.cubes.forEach { cube ->
                when(cube.color) {
                    CubeColor.RED -> minRed = maxOf(minRed, cube.count)
                    CubeColor.GREEN -> minGreen = maxOf(minGreen, cube.count)
                    CubeColor.BLUE -> minBlue = maxOf(minBlue, cube.count)
                }
            }
        }
        minBlue * minRed * minGreen
    }.sumOf { it }
}

private fun solvePartOne(games: List<Game>): Int {
    val redThreshold = 12
    val greenThreshold = 13
    val blueThreshold = 14
    return games.filter { game ->
        game.cubeSets.all { set ->
            set.cubes.all { cube ->
                when (cube.color) {
                    CubeColor.RED -> cube.count <= redThreshold
                    CubeColor.GREEN -> cube.count <= greenThreshold
                    CubeColor.BLUE -> cube.count <= blueThreshold
                }
            }
        }
    }.sumOf { game -> game.id }
}

data class Game(
    val id: Int,
    val cubeSets: List<CubeSet>
) {

    companion object {
        fun fromString(str: String): Game {
            val (gameIdStr, cubeSetsStr) = str.trim().split(":\\s+".toRegex())
            val gameId = gameIdStr.split("\\s+".toRegex())[1].toInt()
            val sets = cubeSetsStr.trim().split(";").map { cubeStr ->
                CubeSet.fromString(cubeStr)
            }
            return Game(gameId, sets)
        }
    }

}

data class CubeSet(
    val cubes: List<Cube>
) {
    companion object {
        fun fromString(str: String): CubeSet {
            val cubes = str.trim().split(",\\s+".toRegex()).map { cubeStr ->
                Cube.fromString(cubeStr)
            }
            return CubeSet(cubes)
        }
    }
}

data class Cube(
    val count: Int,
    val color: CubeColor
) {
    companion object {
        fun fromString(str: String): Cube {
            val (countStr, colorStr) = str.trim().split("\\s+".toRegex())
            return Cube(countStr.toInt(), CubeColor.fromString(colorStr))
        }
    }
}

enum class CubeColor {
    RED, GREEN, BLUE;

    companion object {
        fun fromString(str: String) : CubeColor {
            return CubeColor.valueOf(str.trim().uppercase())
        }
    }
}