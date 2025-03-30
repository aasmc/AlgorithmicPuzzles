package yandex_algo_training.year_2023.day_four.brackets

import java.util.LinkedList

fun main() {
    val n = readln().toInt()
    val res: List<String> = solve(n)
    res.forEach { println(it) }
}

private fun solve(n: Int): List<String> {
    val result = mutableListOf<String>()
    return result
}

private fun correct(brackets: CharArray): Boolean {
    val stack = LinkedList<Char>()
    for (ch in brackets) {
        if (ch == '[' || ch == '(') {
            stack.addLast(ch)
        } else {
            while (stack.isNotEmpty()) {
                val cur = stack.removeLast()
                if (ch == ')' && cur != '(') return false
                if (ch == ']' && cur != '[') return false
            }
        }
    }
    return true
}