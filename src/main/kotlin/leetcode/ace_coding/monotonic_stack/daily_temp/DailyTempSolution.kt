package leetcode.ace_coding.monotonic_stack.daily_temp

import java.util.LinkedList


class DailyTempSolution {

    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val res = IntArray(temperatures.size)
        val stack = LinkedList<IntArray>()
        for ((curIdx, t) in temperatures.withIndex()) {
            while (stack.isNotEmpty() && stack.peekLast()[1] < t) {
                val (stackIndex, _) = stack.removeLast()
                res[stackIndex] = curIdx - stackIndex
            }
            stack.addLast(intArrayOf(curIdx, t))
        }
        return res
    }


}