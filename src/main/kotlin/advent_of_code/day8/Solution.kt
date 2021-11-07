package advent_of_code.day8

import advent_of_code.readLinesFromFile
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

data class MachineState(val ip: Int, val acc: Int)

sealed class Instruction(val action: (MachineState) -> MachineState)

class Nop(val value: Int) : Instruction({ state ->
    MachineState(state.ip + 1, state.acc)
})

class Jmp(val value: Int) : Instruction({ state ->
    MachineState(state.ip + value, state.acc)
})

class Acc(val value: Int) : Instruction({ state ->
    MachineState(state.ip + 1, state.acc + value)
})

fun execute(instructions: List<Instruction>): MachineState {
    var state = MachineState(0, 0)
    val encounteredInstructions = mutableSetOf<Int>()
    while (state.ip in instructions.indices) {
        val nextInstruction = instructions[state.ip]
        state = nextInstruction.action(state)
        if (state.ip in encounteredInstructions) return state
        encounteredInstructions += state.ip
    }
    println(" No loop found!")
    return state
}

fun executeMutable(instructions: List<Instruction>): MachineState {
    var ip: Int = 0
    var acc: Int = 0
    val encounteredIndices = mutableSetOf<Int>()
    while (ip in instructions.indices) {
        when (val newInstr = instructions[ip]) {
            is Acc -> {
                ip++
                acc += newInstr.value
            }
            is Jmp -> ip += newInstr.value
            is Nop -> ip++
        }
        if (ip in encounteredIndices) return MachineState(ip, acc)
        encounteredIndices += ip
    }
    return MachineState(ip, acc)
}

fun Instruction(s: String): Instruction {
    val (instr, immediate) = s.split(" ")
    val value = immediate.toInt()
    return when (instr) {
        "nop" -> Nop(value)
        "acc" -> Acc(value)
        "jmp" -> Jmp(value)
        else -> error("Invalid op code!")
    }
}

fun generateAllMutations(instructions: List<Instruction>): Sequence<List<Instruction>> =
    sequence {
        for ((index, instruction) in instructions.withIndex()) {
            val newProgram = instructions.toMutableList()
            newProgram[index] = when (instruction) {
                is Acc -> continue
                is Jmp -> Nop(instruction.value)
                is Nop -> Jmp(instruction.value)
            }
            yield(newProgram)
        }
    }


@OptIn(ExperimentalTime::class)
fun main() {
    val instructions = readLinesFromFile("day8")
        .map { Instruction(it) }

    println(
        measureTimedValue {
            execute(instructions)
        }
    )

    println(
        measureTimedValue {
            val result = generateAllMutations(instructions)
                .map { modified -> execute(modified) }
                .first { state ->
                    state.ip !in instructions.indices
                }

            println(result.acc)
        }
    )

    println(
        measureTimedValue {
            executeMutable(instructions)
        }
    )

    println(
        measureTimedValue {
            val result = generateAllMutations(instructions)
                .map { modified -> executeMutable(modified) }
                .first { state ->
                    state.ip !in instructions.indices
                }

            println(result.acc)
        }
    )
}



















