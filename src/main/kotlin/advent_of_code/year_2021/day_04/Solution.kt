package advent_of_code.year_2021.day_04

import advent_of_code.readLinesFromFile

fun main() {
    val input =
        readLinesFromFile("year_2021/day_04", "input.txt")

    val numbers = input.first().split(",").map { it.toInt() }
    val boards = input.drop(1).chunked(6).map { board ->
        board.filter { line -> line.isNotBlank() }
    }

    val boardInts: List<List<List<Int>>> = boards.map { board ->
        board.map { line ->
            line.trim()
                .split("\\W+".toRegex())
                .map { it.toInt() }
        }
    }

    var bingoBoards: List<BingoBoard> = boardInts.map { BingoBoard.fromCollection(it) }

    for (draw in numbers) {
        for (board in bingoBoards) {
            board.mark(draw)
            if (board.isComplete()) {
                val sumOfUnmarkedFields = board.unmarked().sum()
                println(sumOfUnmarkedFields * draw)
                bingoBoards = bingoBoards - board
            }
        }
    }
}

data class Field(val value: Int, var marked: Boolean = false) {
    fun mark() {
        marked = true
    }
}


data class BingoBoard(val fields: List<List<Field>>) {
    private val widthIndices = fields[0].indices
    private val heightIndices = fields.indices

    companion object {
        fun fromCollection(coll: List<List<Int>>): BingoBoard {
            return BingoBoard(coll.map { row -> row.map { field -> Field(field) }.toMutableList() })
        }
    }
    fun isComplete() = checkRow() || checkColumn()
    private fun checkRow() = fields.any { row -> row.all { it.marked } }

    private fun checkColumn(): Boolean {
        for (column in widthIndices) {
            var columnOk = true
            for (row in heightIndices) {
                if (!fields[row][column].marked) {
                    columnOk = false
                    continue
                }
            }
            if (columnOk) return true
        }
        return false
    }

    fun mark(num: Int) {
        for (row in this.fields) {
            row.map {
                if (it.value == num) it.mark()
            }
        }
    }

    fun unmarked() = fields.flatten().filter { !it.marked }.map { it.value }
}
