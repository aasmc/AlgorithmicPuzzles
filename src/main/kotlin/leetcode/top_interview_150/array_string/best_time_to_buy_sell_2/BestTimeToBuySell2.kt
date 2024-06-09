package leetcode.top_interview_150.array_string.best_time_to_buy_sell_2

class BestTimeToBuySell2 {

    fun maxProfit(prices: IntArray): Int {
        var profit = 0
        for (i in 1 until prices.size) {
            if (prices[i] > prices[i - 1]) {
                profit += (prices[i] - prices[i - 1])
            }
        }
        return profit
    }

}