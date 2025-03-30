package leetcode.ace_coding.monotonic_stack.online_stock

import java.util.LinkedList

class StockSpanner() {

    /**
     * Stores span of a stock to stock price.
     * Prices are stored in monotonically decreasing order, i.e.
     * if we pop all elements from the stack, they will be in increasing
     * order.
     */
    val stack = LinkedList<Pair<Int, Int>>()
    fun next(price: Int): Int {
        // counts the number of days of the span
        var res = 1
        // go down the stack until it is empty or you meet a price
        // that is smaller than current price, and update the span
        while (stack.isNotEmpty() && stack.peekLast().second <= price) {
            res += stack.removeLast().first
        }
        // add current span to price to the stack.
        stack.addLast(res to price)
        return res
    }

}