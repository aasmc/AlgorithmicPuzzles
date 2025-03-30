package leetcode.top_interview_150.array_string.best_time_buy_sell

class StockBuySellSolution {

    fun maxProfit(prices: IntArray): Int {
        var min = Int.MAX_VALUE
        var profit = 0
        prices.forEach { price ->
            min = minOf(min, price)
            val currentProfit = price - min
            profit = maxOf(profit, currentProfit)
        }
        return profit
    }

}