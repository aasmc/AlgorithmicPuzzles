package leetcode.ace_coding.monotonic_stack.online_stock

import java.util.LinkedList

class StockSpanner() {

    val stack = LinkedList<Pair<Int, Int>>()
    fun next(price: Int): Int {
        var res = 1
        while (stack.isNotEmpty() && stack.peekLast().second <= price) {
            res += stack.removeLast().first
        }
        stack.addLast(res to price)
        return res
    }

}