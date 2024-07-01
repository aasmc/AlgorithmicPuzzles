package leetcode.top_interview_150.stack.min_stack

class MinStack {

    private val values = ArrayDeque<Int>()
    private val mins = ArrayDeque<Int>()

    fun push(`val`: Int) {
        values.add(`val`)
        if (mins.isEmpty()) {
            mins.add(`val`)
        } else {
            mins.add(minOf(mins.last(), `val`))
        }
    }

    fun pop() {
        mins.removeLast()
        values.removeLast()
    }

    fun top(): Int {
        return values.last()
    }

    fun getMin(): Int {
        return mins.last()
    }


}