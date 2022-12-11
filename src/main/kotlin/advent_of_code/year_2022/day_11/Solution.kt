package advent_of_code.year_2022.day_11

import advent_of_code.readTextFromFile
import java.util.LinkedList

fun main() {
    val lines = readTextFromFile("year_2022/day_11", "test.txt")
        .split("\n\n")

    val monkeys = createMonkeys(lines)
    for (i in 0 until 20) {
        monkeys.forEach { monkey ->
            monkeyPlayRound(monkey, monkeys)
        }
    }
    val (first, second) = monkeys.map { it.itemsInspected }.sortedByDescending { it }.take(2)
    println(first * second)
}

fun monkeyPlayRound(monkey: Monkey, otherMonkeys: List<Monkey>) {
    if (monkey.items.isEmpty()) return
    monkey.itemsInspected += monkey.items.size
    while (monkey.items.isNotEmpty()) {
        var item = monkey.items.removeFirst()
        val operand = monkey.operationHolder.operand
        when (monkey.operationHolder.operation) {
            Operation.PLUS -> {
                when (operand) {
                    is Operand.Number -> item += operand.value
                    Operand.Old -> item += item
                }
            }

            Operation.MINUS -> {
                when (operand) {
                    is Operand.Number -> item -= operand.value
                    is Operand.Old -> item = 0
                }
            }

            Operation.DIVIDE -> {
                when (operand) {
                    is Operand.Number -> item /= operand.value
                    is Operand.Old -> item = 1
                }
            }

            Operation.MULTIPLY -> {
                when (operand) {
                    is Operand.Number -> item *= operand.value
                    is Operand.Old -> item *= item
                }
            }
        }
        item /= 3
        val toMonkey = if (item % monkey.test == 0L) {
            monkey.ifTestPasses
        } else {
            monkey.ifTestFails
        }
        otherMonkeys[toMonkey].items.addLast(item)
    }
}

enum class Operation {
    PLUS, MINUS, DIVIDE, MULTIPLY
}

data class OperationHolder(
    val operation: Operation,
    val operand: Operand
)

sealed class Operand {
    object Old : Operand()
    data class Number(val value: Int) : Operand()
}

data class Monkey(
    val id: Int,
    val items: LinkedList<Long>,
    val operationHolder: OperationHolder,
    val test: Int,
    val ifTestPasses: Int,
    val ifTestFails: Int,
    var itemsInspected: Int = 0
)

fun createMonkeys(lines: List<String>): List<Monkey> {
    val monkeys = mutableListOf<Monkey>()
    for (text in lines) {
        val monkeyStrings = text.split("\n")
        val monkey = createSingleMonkey(monkeyStrings)
        monkeys.add(monkey)
    }
    return monkeys
}

fun createSingleMonkey(monkeyStrings: List<String>): Monkey {
    var id: Int = -1
    val items = LinkedList<Long>()
    var operation = Operation.PLUS
    var operand: Operand = Operand.Old
    var test: Int = -1
    var ifTrue: Int = -1
    var ifFalse: Int = -1

    for ((idx, line) in monkeyStrings.withIndex()) {

        when (idx) {
            0 -> {
                val start = line.indexOf(' ') + 1
                val end = line.indexOf(':')
                id = line.substring(start, end).toInt()
            }

            1 -> {
                val start = line.indexOf(':') + 2
                val numbers = line.substring(start).split(", ")
                    .map(String::toInt)
                numbers.forEach {
                    items.add(it.toLong())
                }
            }

            2 -> {
                val i = line.indexOf("=") + 6
                val nextLine = line.substring(i)
                val (op, candidateOperand) = nextLine.split(" ")
                operation = when (op) {
                    "+" -> Operation.PLUS
                    "-" -> Operation.MINUS
                    "*" -> Operation.MULTIPLY
                    "/" -> Operation.DIVIDE
                    else -> throw IllegalArgumentException("No such operation is supported $op")
                }
                operand = if (candidateOperand == "old") {
                    Operand.Old
                } else {
                    Operand.Number(candidateOperand.toInt())
                }
            }

            3 -> {
                test = line.split(" divisible by ").last().toInt()
            }

            4 -> {
                ifTrue = line.split(" throw to monkey ").last().toInt()
            }

            5 -> {
                ifFalse = line.split(" throw to monkey ").last().toInt()
            }
        }
    }
    return Monkey(
        id = id,
        items = items,
        operationHolder = OperationHolder(operation, operand),
        test = test,
        ifTestPasses = ifTrue,
        ifTestFails = ifFalse
    )
}

























