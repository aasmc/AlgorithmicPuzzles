package advent_of_code.year_2022.day_09

import advent_of_code.readLinesFromFile

fun main() {
    val lines = readLinesFromFile("year_2022/day_09", )
    val tail = Tail()
    val head = Head()
    val rope = Rope(head, tail)
    lines.forEach { line ->
        val (dir, countStr) = line.split(" ")
        val count = countStr.toInt()
        when {
            dir == "R" -> {
                for (i in 0 until count) {
                    rope.moveHeadRight()
                }
            }

            dir == "U" -> {
                for (i in 0 until count) {
                    rope.moveHeadUp()
                }
            }

            dir == "L" -> {
                for (i in 0 until count) {
                    rope.moveHeadLeft()
                }
            }

            dir == "D" -> {
                for (i in 0 until count) {
                    rope.moveHeadDown()
                }
            }
        }
    }
    val resultOne = rope.countUniqueTailMoves()
    println(resultOne)
}

data class Tail(
    var xPos: Int = 0,
    var yPos: Int = 0
)

data class Head(
    var xPos: Int = 0,
    var yPos: Int = 0
)

data class Rope(
    val head: Head,
    val tail: Tail
) {
    private val visitedMatrix = MutableList<MutableList<Boolean>>(1) {
        mutableListOf()
    }

    init {
        visitedMatrix[0].add(true)
    }

    private var tailMoves = 1

    fun countUniqueTailMoves(): Int {
        var count = 0
        for (row in visitedMatrix) {
            val rowCount = row.count { it }
            count += rowCount
        }
        return count
    }

    fun moveHeadRight() {
        head.xPos++
        ensureTailStepRight()
    }

    fun moveHeadLeft() {
        head.xPos--
        ensureTailStepLeft()
    }

    fun moveHeadUp() {
        head.yPos++
        ensureTailStepUp()
    }

    fun moveHeadDown() {
        head.yPos--
        ensureTailStepDown()
    }

    private fun ensureTailStepDown() {
        val yDiff = tail.yPos - head.yPos
        if (tail.xPos == head.yPos) {
            if (yDiff > 1) moveTailDown()
        } else if (tail.xPos > head.xPos) {
            if (yDiff > 1) moveTailDownLeft()
        } else {
            if (yDiff > 1) moveTailDownRight()
        }
    }

    private fun ensureTailStepUp() {
        val yDiff = head.yPos - tail.yPos
        if (tail.xPos == head.xPos) {
            if (yDiff > 1) moveTailUp()
        } else if (tail.xPos < head.xPos) {
            if (yDiff > 1) moveTailUpRight()
        } else {
            if (yDiff > 1) moveTailUpLeft()
        }
    }

    private fun ensureTailStepLeft() {
        val xDiff = tail.xPos - head.xPos
        if (tail.yPos == head.yPos) {
            if (xDiff > 1) moveTailLeft()
        } else if (head.yPos > tail.yPos) {
            if (xDiff > 1) {
                moveTailUpLeft()
            }
        } else {
            if (xDiff > 1) {
                moveTailDownLeft()
            }
        }
    }


    private fun ensureTailStepRight() {
        val xDiff = head.xPos - tail.xPos
        if (tail.yPos == head.yPos) {
            if (xDiff > 1) moveTailRight()
        } else if (head.yPos > tail.yPos) {
            if (xDiff > 1) {
                moveTailUpRight()
            }
        } else {
            if (xDiff > 1) {
                moveTailDownRight()
            }
        }
    }

    private fun moveTailLeft(diagonal: Boolean = false) {
        tail.xPos--
        ++tailMoves
        visitedMatrix[tail.yPos][tail.xPos] = true
    }

    private fun moveTailRight(diagonal: Boolean = false) {
        tail.xPos++
        ++tailMoves
        if (diagonal) {
            if (tail.yPos == visitedMatrix.size) {
                val toAdd = mutableListOf<Boolean>()
                for (i in 0 until tail.xPos) {
                    toAdd.add(false)
                }
                visitedMatrix.add(toAdd)
            }
        }
        if (tail.xPos == visitedMatrix[tail.yPos].size) {
            visitedMatrix[tail.yPos].add(true)
        } else {
            visitedMatrix[tail.yPos][tail.xPos] = true
        }

    }

    private fun moveTailUp(diagonal: Boolean = false) {
        tail.yPos++
        ++tailMoves
        if (tail.yPos == visitedMatrix.size) {
            val toAdd = mutableListOf<Boolean>()
            for (i in 0 until tail.xPos) {
                toAdd.add(false)
            }
            if (!diagonal) {
                toAdd.add(true)
            } else {
                toAdd.add(false)
            }
            visitedMatrix.add(toAdd)
        } else {
            if (!diagonal) {
                visitedMatrix[tail.yPos][tail.xPos] = true
            }
        }
    }

    private fun moveTailDown(diagonal: Boolean = false) {
        tail.yPos--
        ++tailMoves
        if (!diagonal) {
            visitedMatrix[tail.yPos][tail.xPos] = true
        }
    }

    private fun moveTailUpLeft() {
        moveTailUp(true)
        moveTailLeft()
        tailMoves += 2
    }

    private fun moveTailUpRight() {
        moveTailUp(true)
        moveTailRight(true)
        tailMoves += 2
    }

    private fun moveTailDownLeft() {
        moveTailDown(true)
        moveTailLeft()
        tailMoves += 2
    }

    private fun moveTailDownRight() {
        moveTailDown(true)
        moveTailRight()
        tailMoves += 2
    }
}

