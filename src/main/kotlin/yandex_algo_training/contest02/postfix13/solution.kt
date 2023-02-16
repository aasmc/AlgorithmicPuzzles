package yandex_algo_training.contest02.postfix13

fun main() {
    val line = readLine()!!.trim().split("\\s+".toRegex())
    println(solvePostfix(line))
}

fun solvePostfix(line: List<String>): Long {
    val stack = ArrayDeque<Long>()
    for (ch in line) {
        if (ch != "+" && ch != "-" && ch != "*") {
            stack.addLast(ch.toLong())
        } else {
            val right = stack.removeLast()
            val left = stack.removeLast()
            when (ch) {
                "+" -> {
                    stack.addLast(left + right)
                }
                "-" -> {
                    stack.addLast(left - right)
                }
                "*" -> {
                    stack.addLast(left * right)
                }
            }
        }
    }
    return stack.removeLast()
}