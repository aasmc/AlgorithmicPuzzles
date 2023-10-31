package yandex_algo_training.year_2022.contest03.first

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque

fun main() {
    val queue = ArrayDeque<Int>()
    val br = BufferedReader(InputStreamReader(System.`in`))
    var line = br.readLine()
    while ("exit" != line) {
        val commands = line.split(" ")
        if (commands.size == 2) {
            queue.addLast(commands.last().toInt())
            println("ok")
        } else {
            when (commands[0]) {
                "pop" -> {
                    if (queue.isEmpty()) {
                        println("error")
                    } else {
                        println(queue.removeFirst())
                    }
                }
                "front" -> {
                    if (queue.isEmpty()) {
                        println("error")
                    } else {
                        println(queue.first())
                    }
                }
                "size" -> {
                    println(queue.size)
                }
                "clear" -> {
                    queue.clear()
                    println("ok")
                }
            }
        }
        line = br.readLine()
    }
    println("bye")
    br.close()
}