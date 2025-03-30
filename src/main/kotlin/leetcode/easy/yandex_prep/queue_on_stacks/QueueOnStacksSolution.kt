package leetcode.easy.yandex_prep.queue_on_stacks

import java.util.Stack

class QueueOnStacksSolution {
    class MyQueue() {

        private val stackOne = Stack<Int>()
        private val queue = Stack<Int>()

        fun push(x: Int) {
            while (queue.isNotEmpty()) {
                stackOne.push(queue.pop())
            }
            stackOne.push(x)
            while (stackOne.isNotEmpty()) {
                queue.push(stackOne.pop())
            }
        }

        fun pop(): Int {
            return queue.pop()
        }

        fun peek(): Int {
            return queue.peek()
        }

        fun empty(): Boolean {
            return queue.isEmpty()
        }

    }

}
