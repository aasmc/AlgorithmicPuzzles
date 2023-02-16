package yandex_algo_training.contest02.correctbrackets12

fun main() {
    val line = readLine()!!
    println(checkBrackets(line))
}

fun checkBrackets(line: String): String {
    if (line.isBlank()) return "yes"
    val stack = ArrayDeque<Char>()
    for (ch in line) {
        if (ch == '(' || ch == '{' || ch == '[') {
            stack.addLast(ch)
        } else {
            when(ch) {
                ')' -> {
                    if (stack.isEmpty() || stack.last() != '(') {
                        return "no"
                    }
                    stack.removeLast()
                }
                '}' -> {
                    if (stack.isEmpty() || stack.last() != '{') {
                        return "no"
                    }
                    stack.removeLast()
                }
                ']' -> {
                    if (stack.isEmpty() || stack.last() != '[') {
                        return "no"
                    }
                    stack.removeLast()
                }
            }
        }
    }
    if (stack.isNotEmpty()) return "no"
    return "yes"
}