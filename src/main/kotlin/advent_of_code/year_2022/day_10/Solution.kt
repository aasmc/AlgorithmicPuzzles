package advent_of_code.year_2022.day_10

import advent_of_code.readLinesFromFile
import kotlin.properties.Delegates

fun main() {
    val lines = readLinesFromFile("year_2022/day_10", ).map { line ->
        val split = line.split(" ")
        if (split.size == 1) {
            Operation.Noop()
        } else {
            Operation.Add(split[1].toInt())
        }
    }

    val cpu = CPU(lines)
    cpu.executeAllInstructions()
    val sum = cpu.getSumOfSignalStrengths()
    println(sum)
    val crt = CRT(lines)
    crt.executeAllInstructions()
    crt.drawScreen()
}

data class Sprite(
    val pixels: IntRange
)

class CRT(
    private val ops: List<Operation>
) {
    private val screen: MutableList<CharArray> = MutableList(6) {
        CharArray(40) { '.' }
    }
    private var currentSprite: Sprite = Sprite(0..2)
    private var rowPosition = 0
    private var cycleCount: Int by Delegates.observable(0) { _, _, new ->
        if (new % 40 == 0) {
            rowPosition++
        }
        if (new == 240){
            cycleComplete = true
        }
        if (checkColIntersectsWithSprite()) {
            screen[rowPosition][colPosition] = '#'
        }
        incrementAndGetColPosition()
    }
    private var cycleComplete = false
    private var registerState: Int by Delegates.observable(1) { _, _, new ->
        currentSprite = Sprite((new - 1)..(new + 1))
    }
    private var instructionPointer = 0
    private var colPosition = 0

    private fun incrementAndGetColPosition(): Int {
        colPosition++
        if (colPosition % 40 == 0) {
            colPosition = 0
        }
        return colPosition
    }

    private fun checkColIntersectsWithSprite(): Boolean {
        return colPosition in currentSprite.pixels
    }

    fun executeInstruction() {
        val op = ops[instructionPointer++]
        when(op) {
            is Operation.Add -> {
                ++cycleCount
                ++cycleCount
                registerState += op.value
            }
            is Operation.Noop -> {
                ++cycleCount
            }
        }
    }

    fun executeAllInstructions() {
        while (!cycleComplete) {
            executeInstruction()
        }
    }

    fun drawScreen() {
        for (line in screen) {
            for (ch in line) {
                print(ch)
            }
            println()
        }
    }
}

class CPU(
    private val ops: List<Operation>
) {
    private var cycleCount: Int by Delegates.observable(0) { prop, old, new ->
        if (new == 20 || new == 60 || new == 100
            || new == 140 || new == 180 || new == 220
        ) {
            signalsHolder.add(new * registerState)
        }
    }

    private val signalsHolder: MutableList<Long> = mutableListOf()
    private var registerState = 1L
    private var instructionPointer = 0

    fun executeNextInstruction() {
        val op = ops[instructionPointer++]
        when (op) {
            is Operation.Add -> {
                ++cycleCount
                ++cycleCount
                registerState += op.value
            }

            is Operation.Noop -> {
                ++cycleCount
            }
        }
    }

    fun executeAllInstructions() {
        for (i in ops.indices) {
            executeNextInstruction()
        }
    }

    fun getSumOfSignalStrengths(): Long {
        return signalsHolder.sumOf { it }
    }

}

sealed class Operation(val cycleCount: Int) {
    data class Add(val value: Int) : Operation(2)
    class Noop : Operation(1)
}