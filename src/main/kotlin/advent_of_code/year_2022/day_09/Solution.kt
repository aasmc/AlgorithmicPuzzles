package advent_of_code.year_2022.day_09

import advent_of_code.readLinesFromFile
import kotlin.math.abs

fun main() {
    val lines = readLinesFromFile("year_2022/day_09", )
    val tail = Knot()
    val head = Knot()
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
    val ropes: Ropes = buildRopes()
    lines.forEach { line ->
        val (dir, countStr) = line.split(" ")
        val count = countStr.toInt()
        when {
            dir == "R" -> {
                for (i in 0 until count) {
                    ropes.moveHeadRight()
                }
            }

            dir == "U" -> {
                for (i in 0 until count) {
                    ropes.moveHeadUp()
                }
            }

            dir == "L" -> {
                for (i in 0 until count) {
                    ropes.moveHeadLeft()
                }
            }

            dir == "D" -> {
                for (i in 0 until count) {
                    ropes.moveHeadDown()
                }
            }
        }
    }
    val res2 = ropes.countUniqueTailMoves()
    println(res2)

}

fun buildRopes(): Ropes {
    return Ropes(
        knots = listOf(
            Knot(0, 0),
            Knot(0, 0),
            Knot(0, 0),
            Knot(0, 0),
            Knot(0, 0),
            Knot(0, 0),
            Knot(0, 0),
            Knot(0, 0),
            Knot(0, 0),
            Knot(0, 0),
        )
    )
}


data class Knot(
    var xPos: Int = 0,
    var yPos: Int = 0
)

data class Pos(
    val xPos: Int,
    val yPos: Int
)

data class Ropes(
    val knots: List<Knot>
) {
    private val head = knots.first()
    private val visitedPositions = hashSetOf<Pos>()

    init {
        visitedPositions.add(Pos(0, 0))
    }

    private fun addVisitedPosOfTail() {
        visitedPositions.add(Pos(knots.last().xPos, knots.last().yPos))
    }

    fun countUniqueTailMoves(): Int {
        return visitedPositions.size
    }

    fun moveHeadRight() {
        head.xPos++
        ensureKnots()
    }

    fun moveHeadLeft() {
        head.xPos--
        ensureKnots()
    }

    fun moveHeadUp() {
        head.yPos++
        ensureKnots()
    }

    fun moveHeadDown() {
        head.yPos--
        ensureKnots()
    }

    private fun ensureKnots() {
        for (i in 1 until knots.size) {
            val head = knots[i - 1]
            val tail = knots[i]
            ensureStepCorrect(head, tail)
        }
    }

    private fun ensureStepCorrect(head: Knot, tail: Knot) {
        val yDiff = abs(head.yPos - tail.yPos)
        val xDiff = abs(head.xPos - tail.xPos)

        if (head.xPos == tail.xPos) {
            if (head.yPos > tail.yPos) {
                if (yDiff > 1) {
                    moveTailUp(tail = tail)
                }
            } else {
                if (yDiff > 1) {
                    moveTailDown(tail = tail)
                }
            }
        } else if (head.yPos == tail.yPos) {
            if (head.xPos > tail.xPos) {
                if (xDiff > 1) {
                    moveTailRight(tail)
                }
            } else {
                if (xDiff > 1) {
                    moveTailLeft(tail)
                }
            }
        } else if (head.yPos > tail.yPos && head.xPos > tail.xPos) {
            if (xDiff > 1 || yDiff > 1) {
                moveTailUpRight(tail)
            }
        } else if (head.yPos > tail.yPos) {
            if (xDiff > 1 || yDiff > 1) {
                moveTailUpLeft(tail)
            }
        } else if (head.xPos > tail.xPos) {
            if (xDiff > 1 || yDiff > 1) {
                moveTailDownRight(tail)
            }
        } else  {
            if (xDiff > 1 || yDiff > 1) {
                moveTailDownLeft(tail)
            }
        }
    }
    private fun moveTailLeft(tail: Knot) {
        tail.xPos--
        addVisitedPosOfTail()
    }

    private fun moveTailRight(tail: Knot) {
        tail.xPos++
        addVisitedPosOfTail()
    }

    private fun moveTailUp(diagonal: Boolean = false, tail: Knot) {
        tail.yPos++
        if (!diagonal) {
            addVisitedPosOfTail()
        }
    }

    private fun moveTailDown(diagonal: Boolean = false, tail: Knot) {
        tail.yPos--
        if (!diagonal) {
            addVisitedPosOfTail()
        }
    }

    private fun moveTailUpLeft(tail: Knot) {
        moveTailUp(true, tail)
        moveTailLeft(tail)
    }

    private fun moveTailUpRight(tail: Knot) {
        moveTailUp(true, tail)
        moveTailRight(tail)
    }

    private fun moveTailDownLeft(tail: Knot) {
        moveTailDown(true, tail)
        moveTailLeft(tail)
    }

    private fun moveTailDownRight(tail: Knot) {
        moveTailDown(true, tail)
        moveTailRight(tail)
    }
}

data class Rope(
    val head: Knot,
    val tail: Knot,
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

