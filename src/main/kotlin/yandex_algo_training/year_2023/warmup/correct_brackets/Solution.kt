package yandex_algo_training.year_2023.warmup.correct_brackets

import java.util.LinkedList

fun main() {
    val brackets = readln().toCharArray()
    solve(brackets)
}

private fun solve(brackets: CharArray) {
    val stack = LinkedList<Char>()
    var correct = true
    for (ch in brackets) {
        if (ch == '[' || ch == '(' || ch == '{') {
            stack.addLast(ch)
        } else {
            if (stack.isEmpty()) {
                correct = false
                break
            }
            val last = stack.removeLast()

            if (last == '(' && ch != ')') {
                correct = false
                break
            }
            if (last == '[' && ch != ']') {
                correct = false
                break
            }
            if (last == '{' && ch != '}') {
                correct = false
                break
            }
        }
    }
    if (correct && stack.isEmpty()) {
        println("yes")
    } else {
        println("no")
    }
}