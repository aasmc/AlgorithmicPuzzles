package leetcode.top_interview_150.stack.valid_parentheses

class ValidParenthesesSolution {


    fun isValid(s: String): Boolean {
        val opening = hashSetOf('(', '{', '[')
        val stack = ArrayDeque<Char>()
        for (ch in s) {
            if (ch in opening) {
                stack.addLast(ch)
            } else {
                if (stack.isEmpty()) return false
                val top = stack.removeLast()
                when(ch) {
                    ')' -> {
                        if (top != '(') return false
                    }
                    '}' -> {
                        if (top != '{') return false
                    }
                    ']' -> {
                        if (top != '[') return false
                    }
                }
            }
        }
        return stack.isEmpty()
    }

}