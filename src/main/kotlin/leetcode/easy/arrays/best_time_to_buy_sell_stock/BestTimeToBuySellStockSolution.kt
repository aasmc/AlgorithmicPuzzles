package leetcode.easy.arrays.best_time_to_buy_sell_stock

class BestTimeToBuySellStockSolution {

    fun maxProfit(prices: IntArray): Int {
        var min = Int.MAX_VALUE
        var profit = 0
        prices.forEach { price ->
            min = minOf(min, price)
            profit = maxOf(profit, price - min)
        }
        return profit
    }

}