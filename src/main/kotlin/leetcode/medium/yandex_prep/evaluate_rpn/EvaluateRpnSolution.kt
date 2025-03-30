package leetcode.medium.yandex_prep.evaluate_rpn

import java.util.Stack

class EvaluateRpnSolution {

    fun evalRPN(tokens: Array<String>): Int {

        val stack = Stack<Int>()
        for (token in tokens) {
            when(token) {
                "+" -> {
                    val right = stack.pop()
                    val left = stack.pop()
                    stack.push(left + right)
                }
                "-" -> {
                    val right = stack.pop()
                    val left = stack.pop()
                    stack.push(left - right)
                }
                "*" -> {
                    val right = stack.pop()
                    val left = stack.pop()
                    stack.push(left * right)
                }
                "/" -> {
                    val right = stack.pop()
                    val left = stack.pop()
                    stack.push(left / right)
                }
                else -> {
                    stack.push(token.toInt())
                }
            }
        }
        return stack.pop()
    }

}