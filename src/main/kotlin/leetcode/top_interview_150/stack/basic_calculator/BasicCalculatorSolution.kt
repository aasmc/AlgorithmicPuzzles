package leetcode.top_interview_150.stack.basic_calculator

class BasicCalculatorSolution {

    fun calculate(s: String): Int {
        var sign = 1
        var sum = 0
        val stack = ArrayDeque<Int>()
        var i = 0
        while (i < s.length) {
            val ch = s[i]
            when (ch) {
                ' ' -> {
                    // do nothing
                }

                '+' -> {
                    sign = 1
                }

                '-' -> {
                    sign = -1
                }

                '(' -> {
                    // when we encounter (, this means we can push current sum onto the stack
                    // and also push the sign onto the stack, after that we can reset sum and sign
                    stack.addLast(sum)
                    stack.addLast(sign)
                    sum = 0
                    sign = 1
                }

                ')' -> {
                    sum *= stack.removeLast() // first apply the sign to the sum
                    sum += stack.removeLast() // then add the previous sum to the current sum
                }
                else -> {
                    // while current char is a digit, we calculate the entire digit
                    var num = 0
                    while (i < s.length && s[i].isDigit()) {
                        num = num * 10 + s[i].digitToInt()
                        ++i
                    }
                    sum += num * sign
                    // after we get out of the cycle we need to decrement the i pointer,
                    // because it now points to the character after the digit, but we
                    // will process it in the next iteration of the outer cycle
                    --i
                }
            }
            ++i
        }
        return sum
    }

}