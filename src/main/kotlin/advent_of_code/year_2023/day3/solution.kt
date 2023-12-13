package advent_of_code.year_2023.day3

import advent_of_code.readLinesFromFile

fun main() {
    val matrix = Matrix.readMatrix("input.txt")
    val res = calculateGearRatios(matrix)
    println(res)
}

private fun calculateGearRatios(matrix: Matrix): Long {
    var sum = 0L
    matrix.rows.forEachIndexed { yIdx, row ->
        for ((xIdx, ch) in row.withIndex()) {
            if (ch == '*') {
                val neighbours = getNeighboringDigits(matrix, xIdx, yIdx)
                if (neighbours.size == 2) {
                    val product = neighbours[0] * neighbours[1]
                    sum += product.toLong()
                }
            }
        }
    }
    return sum
}

private fun getNeighboringDigits(matrix: Matrix, currentX: Int, currentY: Int): List<Int> {
    return checkRowAbove(matrix, currentX, currentY) +
            checkRowBelow(matrix, currentX, currentY) +
            checkLeft(matrix, currentX, currentY) +
            checkRight(matrix, currentX, currentY)
}

private fun checkRowAbove(matrix: Matrix, currentX: Int, currentY: Int): List<Int> {
    return checkRow(matrix, currentY - 1, currentX)
}

private fun checkRowBelow(matrix: Matrix, currentX: Int, currentY: Int): List<Int> {
    return checkRow(matrix, currentY + 1, currentX)
}

private fun checkRow(matrix: Matrix, y: Int, currentX: Int): List<Int> {
    val result = mutableListOf<Int>()
    if (directionWithinBounds(currentX, y, matrix)
        && matrix.rows[y][currentX].isDigit()
    ) {
        var digitStart = currentX - 1
        while (digitStart >= 0 && matrix.rows[y][digitStart].isDigit()) {
            --digitStart
        }
        var digitEnd = currentX + 1
        while (digitEnd < matrix.rows[y].size && matrix.rows[y][digitEnd].isDigit()) {
            ++digitEnd
        }
        result.add(buildDigit(digitStart + 1, digitEnd, matrix, y))
    } else {
        if (directionWithinBounds(currentX - 1, y, matrix) && matrix.rows[y][currentX - 1].isDigit()) {
            var digitStart = currentX - 2
            while (digitStart >= 0 && matrix.rows[y][digitStart].isDigit()) {
                --digitStart
            }
            result.add(buildDigit(digitStart + 1, currentX, matrix, y))
        }
        if (directionWithinBounds(currentX + 1, y, matrix) && matrix.rows[y][currentX + 1].isDigit()) {
            val digitStart = currentX + 1
            var digitEnd = currentX + 2
            while (digitEnd < matrix.rows[y].size && matrix.rows[y][digitEnd].isDigit()) {
                digitEnd++
            }
            result.add(buildDigit(digitStart, digitEnd, matrix, y))
        }
    }
    return result
}

private fun buildDigit(from: Int, to: Int, matrix: Matrix, y: Int): Int {
    var digit = ""
    for (x in from until to) {
        digit += matrix.rows[y][x]
    }
    return digit.toInt()
}

private fun checkLeft(matrix: Matrix, currentX: Int, currentY: Int): List<Int> {
    return checkSide(matrix, currentX - 1, currentY, true)
}

private fun checkRight(matrix: Matrix, currentX: Int, currentY: Int): List<Int> {
    return checkSide(matrix, currentX + 1, currentY, false)
}

private fun checkSide(matrix: Matrix, x: Int, y: Int, goLeft: Boolean): List<Int> {
    if (matrix.rows[y][x].isDigit()) {
        return if (goLeft) {
            var digitStart = x
            while (digitStart >= 0 && matrix.rows[y][digitStart].isDigit()) {
                digitStart--
            }
            listOf(buildDigit(digitStart + 1, x + 1, matrix, y))
        } else {
            var digitEnd = x + 1
            while (digitEnd < matrix.rows[y].size && matrix.rows[y][digitEnd].isDigit()) {
                ++digitEnd
            }
            listOf(buildDigit(x, digitEnd, matrix, y))
        }
    }
    return emptyList()
}

private fun calculateEngineParts(matrix: Matrix): Int {
    var sum = 0
    matrix.rows.forEachIndexed { yIdx, row ->
        var i = 0
        var currentDigit = ""
        while (i < row.size) {
            if (row[i].isDigit()) {
                val start = i
                while (i < row.size && row[i].isDigit()) {
                    currentDigit += row[i++]
                }
                val end = i - 1
                for (xIdx in start..end) {
                    if (hasNeighboringSymbols(matrix, xIdx, yIdx)) {
                        sum += currentDigit.toInt()
                        break
                    }
                }
                currentDigit = ""
            } else {
                ++i
            }
        }
    }
    return sum
}


private fun hasNeighboringSymbols(matrix: Matrix, currentX: Int, currentY: Int): Boolean {
    val xDirs = intArrayOf(1, -1, 0, 0, -1, -1, 1, 1)
    val yDirs = intArrayOf(0, 0, -1, 1, -1, 1, -1, 1)
    for (i in xDirs.indices) {
        val newX = currentX + xDirs[i]
        val newY = currentY + yDirs[i]
        if (directionWithinBounds(newX, newY, matrix)) {
            val newChar = matrix.rows[newY][newX]
            if (newChar != '.' && !newChar.isDigit()) {
                return true
            }
        }
    }
    return false
}

private fun directionWithinBounds(x: Int, y: Int, matrix: Matrix): Boolean {
    return x >= 0 && x < matrix.rows.size && y >= 0 && y < matrix.rows[0].size
}

data class Matrix(
    val rows: List<CharArray>
) {
    companion object {
        fun readMatrix(fileName: String): Matrix {
            return Matrix(
                readLinesFromFile("year_2023/day3", fileName)
                    .map { it.toCharArray() }
            )
        }
    }
}
