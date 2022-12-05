package advent_of_code.year_2022.day_05

import advent_of_code.readLinesFromFile
import java.lang.StringBuilder
import java.util.LinkedList

fun main() {
    val lines = readLinesFromFile("year_2022/day_05", )
    var idx = 0
    for (l in lines) {
        if (l.isBlank()) {
            break
        }
        ++idx
    }
    val stackLines = lines.subList(0, idx)
    val opLines = lines.subList(idx + 1, lines.size)
    val stacks = createStacks(stackLines)
    val operations = opLines.map(MoveOperation::fromString)

    operations
        .forEach { op ->
            op.performMovePartTwo(stacks)
        }

    val builder = StringBuilder()
    for (s in stacks) {
        builder.append(s.peekLast())
    }
    val res = builder.toString()
    println(res)
}

private fun createStacks(stackLines: List<String>): List<LinkedList<Char>> {
    val charCodeRange = 'A'.code..'Z'.code
    val numStacks = stackLines.last().split(" ").last().toInt()
    val result = mutableListOf<LinkedList<Char>>()
    for (i in 0 until numStacks) {
        result.add(LinkedList())
    }
    stackLines.dropLast(1).forEach { line ->
        var curIdx = 0
        var stackIdx = 0
        val chars = line.toCharArray()
        for (ch in chars) {
            ++curIdx
            if (ch == ' ') {
                if (curIdx % 4 == 0) {
                    stackIdx++
                }
            } else if (ch.code in charCodeRange) {
                result[stackIdx].addFirst(ch)
            }
        }
    }
    return result
}

data class MoveOperation(
    val fromStack: Int,
    val toStack: Int,
    val amount: Int
) {

    fun performMovePartTwo(stacks: List<LinkedList<Char>>) {
        val from = stacks[fromStack]
        val to = stacks[toStack]
        val tmp = LinkedList<Char>()
        for (i in 0 until amount) {
            tmp.addLast(from.removeLast())
        }
        for (i in 0 until amount) {
            to.addLast(tmp.removeLast())
        }
    }

    fun performMove(stacks: List<LinkedList<Char>>) {
        val from = stacks[fromStack]
        val to = stacks[toStack]
        for (i in 0 until amount) {
            to.addLast(from.removeLast())
        }
    }

    companion object {
        fun fromString(line: String): MoveOperation {
            val words = line.split(" ")
            val amount = words[1].toInt()
            val from = words[3].toInt() - 1
            val to = words[5].toInt() - 1
            return MoveOperation(fromStack = from, toStack = to, amount = amount)
        }
    }
}