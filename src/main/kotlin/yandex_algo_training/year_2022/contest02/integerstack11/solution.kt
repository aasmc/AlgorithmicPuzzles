package yandex_algo_training.year_2022.contest02.integerstack11

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

fun main() {
    val stack = ArrayDeque<Int>()
    var exited = false
    val lines = Files.lines(Path.of("input.txt")).collect(Collectors.toList())

    for (line in lines) {
        if (exited) break
        val commands = line.split(" ")
        if (commands.size == 2) {
            stack.addLast(commands.last().toInt())
            println("ok")
        } else {
            when (commands[0]) {
                "pop" -> {
                    if (stack.isEmpty()) {
                        println("error")
                    } else {
                        println(stack.removeLast())
                    }
                }
                "back" -> {
                    if (stack.isEmpty()) {
                        println("error")
                    } else {
                        println(stack.last())
                    }
                }
                "size" -> {
                    println(stack.size)
                }
                "clear" -> {
                    stack.clear()
                    println("ok")
                }
                "exit" -> {
                    exited = true
                    println("bye")
                }
            }
        }
    }
}