package leetcode.ace_coding.dynamic_programming.best_time_stocks

class BuySellStocksSolution {

    fun maxProfit(prices: IntArray, fee: Int): Int {
        // Максимальное значение акции, которую мы можем купить в i-й день
        val acquisition = IntArray(prices.size + 1)
        acquisition[0] = Int.MIN_VALUE
        // Хранит максимальную прибыль, которую мы можем приобрести, продав акцию в i-й день
        val profit = IntArray(prices.size + 1)
        for (i in 1..prices.size) {
            // мы либо оставляем предыдущую покупку, либо совершаем новую, заплатив за нее
            // из тех денег, которые мы заработали роанее (изначально эта сумма = 0)
            acquisition[i] = maxOf(acquisition[i - 1], profit[i - 1] - prices[i - 1])
            // Мы выбираем, что можно лучше продать: либо то, что было раньше, либо то, что
            // куплено сейчас за вычетом налога. (тут prices[i - 1] так как мы в качестве
            // 0-го элемента во вспомогательных массивах добавили нужные значения, а это основной массив
            // и там мы должны итерироваться с 0 до конца)
            profit[i] = maxOf(profit[i - 1], acquisition[i] + prices[i - 1] - fee)
        }
        return profit[prices.size]
    }

}