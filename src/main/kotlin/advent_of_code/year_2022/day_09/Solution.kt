package advent_of_code.year_2022.day_09

import advent_of_code.readLinesFromFile
import kotlin.math.abs

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

data class Pos(
    val xPos: Int,
    val yPos: Int
)

data class Rope(
    val head: Head,
    val tail: Tail
) {

    private var tailMoves = 1
    private val visitedPositions = hashSetOf<Pos>()

    init {
        addVisitedPosOfTail()
    }

    private fun addVisitedPosOfTail() {
        visitedPositions.add(Pos(tail.xPos, tail.yPos))
    }

    fun countUniqueTailMoves(): Int {
        return visitedPositions.size
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
        val yDiff = abs(tail.yPos - head.yPos)

        if (tail.xPos == head.xPos) {
            if (tail.yPos > head.yPos) {
                if (yDiff > 1) moveTailDown()
            } else {
                if (yDiff > 1) moveTailUp()
            }
        } else if (tail.xPos > head.xPos) {
            if (tail.yPos > head.yPos) {
                if (yDiff > 1) moveTailDownLeft()
            } else {
                if (yDiff > 1) moveTailUpLeft()
            }
        } else {
            if (tail.yPos > head.yPos) {
                if (yDiff > 1) moveTailDownRight()
            } else {
                if (yDiff > 1) moveTailUpRight()
            }
        }
    }

    private fun ensureTailStepUp() {
        val yDiff = abs(head.yPos - tail.yPos)
        if (tail.xPos == head.xPos) {
            if (head.yPos > tail.yPos) {
                if (yDiff > 1) moveTailUp()
            } else {
                if (yDiff > 1) moveTailDown()
            }
        } else if (tail.xPos < head.xPos) {
            if (head.yPos > tail.yPos) {
                if (yDiff > 1) moveTailUpRight()
            } else {
                if (yDiff > 1) moveTailDownRight()
            }
        } else {
            if (head.yPos > tail.yPos) {
                if (yDiff > 1) moveTailUpLeft()
            } else {
                if (yDiff > 1) moveTailDownLeft()
            }
        }
    }

    private fun ensureTailStepLeft() {
        val xDiff = abs(tail.xPos - head.xPos)
        if (tail.yPos == head.yPos) {
            if (tail.xPos > head.xPos) {
                if (xDiff > 1) moveTailLeft()
            } else {
                if (xDiff > 1) moveTailRight()
            }
        } else if (head.yPos > tail.yPos) {
            if (tail.xPos > head.xPos) {
                if (xDiff > 1) moveTailUpLeft()
            } else {
                if (xDiff > 1) moveTailDownLeft()
            }
        } else {
            if (tail.xPos > head.xPos) {
                if (xDiff > 1) moveTailDownLeft()
            } else {
                if (xDiff > 1) moveTailUpLeft()
            }
        }
    }

    private fun ensureTailStepRight() {
        val xDiff = abs(head.xPos - tail.xPos)
        if (tail.yPos == head.yPos) {
            if (head.xPos > tail.xPos) {
                if (xDiff > 1) moveTailRight()
            } else {
                if (xDiff > 1) moveTailLeft()
            }
        } else if (head.yPos > tail.yPos) {
            if (head.xPos > tail.xPos) {
                if (xDiff > 1) moveTailUpRight()
            } else {
                if (xDiff > 1) moveTailDownRight()
            }

        } else {
            if (head.xPos > tail.xPos) {
                if (xDiff > 1) moveTailDownRight()
            } else {
                if (xDiff > 1) moveTailUpRight()
            }
        }
    }

    private fun moveTailLeft() {
        tail.xPos--
        ++tailMoves
        addVisitedPosOfTail()
    }

    private fun moveTailRight() {
        tail.xPos++
        ++tailMoves
        addVisitedPosOfTail()
    }

    private fun moveTailUp(diagonal: Boolean = false) {
        tail.yPos++
        if (!diagonal) {
            ++tailMoves
            addVisitedPosOfTail()
        }
    }

    private fun moveTailDown(diagonal: Boolean = false) {
        tail.yPos--
        if (!diagonal) {
            ++tailMoves
            addVisitedPosOfTail()
        }
    }

    private fun moveTailUpLeft() {
        moveTailUp(true)
        moveTailLeft()
        tailMoves++
    }

    private fun moveTailUpRight() {
        moveTailUp(true)
        moveTailRight()
        tailMoves++
    }

    private fun moveTailDownLeft() {
        moveTailDown(true)
        moveTailLeft()
        tailMoves++
    }

    private fun moveTailDownRight() {
        moveTailDown(true)
        moveTailRight()
        tailMoves++
    }
}

