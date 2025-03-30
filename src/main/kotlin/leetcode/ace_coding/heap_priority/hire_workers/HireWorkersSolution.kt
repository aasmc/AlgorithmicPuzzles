package leetcode.ace_coding.heap_priority.hire_workers

import java.util.PriorityQueue

class HireWorkersSolution {

    fun totalCost(costs: IntArray, k: Int, candidates: Int): Long {
        val head = PriorityQueue<Int>()
        val tail = PriorityQueue<Int>()

        var nextHead = 0
        while (nextHead < candidates) {
            head.add(costs[nextHead++])
        }
        val tailStart = maxOf(candidates, costs.size - candidates)
        for (i in tailStart until costs.size) {
            tail.offer(costs[i])
        }
        var nextTail = costs.size - candidates - 1
        var result = 0L
        repeat(k) {
            if (tail.isEmpty() || head.isNotEmpty() && head.peek() <= tail.peek()) {
                result += head.poll()
                if (nextHead <= nextTail) {
                    head.add(costs[nextHead++])
                }
            } else {
                result += tail.poll()
                if (nextHead <= nextTail) {
                    tail.add(costs[nextTail--])
                }
            }
        }
        return result
    }

}