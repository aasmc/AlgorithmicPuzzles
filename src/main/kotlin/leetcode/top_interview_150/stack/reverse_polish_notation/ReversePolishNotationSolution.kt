package leetcode.top_interview_150.stack.reverse_polish_notation

class ReversePolishNotationSolution {

    fun evalRPN(tokens: Array<String>): Int {
        val stack = ArrayDeque<Int>()
        tokens.forEach { token ->
            when(token) {
                "+" -> {
                    val right = stack.removeLast()
                    val left = stack.removeLast()
                    stack.addLast(left + right)
                }
                "-" -> {
                    val right = stack.removeLast()
                    val left = stack.removeLast()
                    stack.addLast(left - right)
                }
                "*" -> {
                    val right = stack.removeLast()
                    val left = stack.removeLast()
                    stack.addLast(left * right)
                }
                "/" -> {
                    val right = stack.removeLast()
                    val left = stack.removeLast()
                    stack.addLast(left / right)
                }
                else -> {
                    stack.addLast(token.toInt())
                }
            }
        }
        return stack.last()
    }

}