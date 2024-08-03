package leetcode.medium.yandex_prep.simplify_path

import java.util.Stack

class SimplifyPathSolution {

    fun simplifyPath(path: String): String {
        val stack = Stack<String>()
        val sb = StringBuilder()
        var i = 0
        while (i < path.length) {
            if (sb.isNotEmpty() && path[i] == '/') {
                pushToStack(sb, stack)
            }
            while (i < path.length && path[i] == '/') {
                ++i
            }
            if (i < path.length) {
                sb.append(path[i])
            }
            ++i
        }
        if (sb.isNotEmpty()) {
            pushToStack(sb, stack)
        }
        return stack.joinToString(separator = "/", prefix = "/")
    }

    private fun pushToStack(sb: StringBuilder, stack: Stack<String>) {
        val current = sb.toString()
        sb.clear()
        if (current == "..") {
            if (stack.isNotEmpty()) {
                stack.pop()
            }
        } else if (current != ".") {
            stack.push(current)
        }
    }

}