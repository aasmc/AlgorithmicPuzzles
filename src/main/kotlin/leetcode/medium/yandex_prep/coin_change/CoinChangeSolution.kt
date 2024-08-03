package leetcode.medium.yandex_prep.coin_change

class CoinChangeSolution {

    fun coinChange(coins: IntArray, amount: Int): Int {
        val default = amount + 1
        // dp[i] will store the min number of coins we need to get the amount i.
        // e.g. we have coins=[1,2,5] to get amount 0 we need 0 coins
        // to get amount 1 we need 1 coin
        // to get amount 2 we need 2 coins
        // to get amount 3 we need 2 coins
        // to get amount 4 we need 2 coins
        // to get amount 5 we need 1 coin
        // to get amount 6 we need 2 coins
        // to get amount 7 we need 2 coins etc.
        val dp = IntArray(amount + 1) { default }
        dp[0] = 0
        // to compute the dp array we go from every possible amount starting
        // from 1 until the provided amount and try the following logic
        // for every coin in the coins array, we:
        // 1. check if we can get the amount at all, to do this we subtract the coin value
        //    from the amount. And if the result is >= 0 than we can try to get the amount
        // 2. then we get what we have in dp[currentAmount - coin] + 1, i.e. we get the
        //    number of coins we need to get amount = currentAmount - coin plus 1 coin,
        //    because we need to consider current coin as well.
        // 3. the result is the minimum value that we have in dp for current amount,
        //    and the computed value for dp[currentAmount - coin] + 1
        // e.g. coins [1,2,5], amount = 7
        // coin = 1
        // amount = 1, dp[1] = min(dp[1], 1 + dp[1- 1]) = min(8, 1 + 0) = 8
        // amount = 2, dp[2] = min(dp[2], 1 + dp[2-1]) = min(8, 1 + 1) = 2
        // amount = 3, dp[3] = min(dp[3], 1 + dp[3-1]) = min(8, 3) = 3
        // amount = 4, dp[4] = min(dp[4], 1 + dp[4-1]) = min(8, 4) = 4
        // ..
        // amount = 7, dp[7] = min(dp[7], 1 + dp[7-1]) = min(8, 7) = 7
        // coin = 2
        // amount = 1 pass
        // amount = 2, dp[2] = min(dp[2], 1 + dp[2-2]) = min(2, 1) = 1
        // etc.
        for(amnt in 1 .. amount) {
            for (coin in coins) {
                if (amnt - coin >= 0) {
                    dp[amnt] = minOf(dp[amnt], 1 + dp[amnt - coin])
                }
            }
        }
        // we also need to take into account the fact that we could fail to compute
        // the amount with those coins, in this case we return -1
        return if (dp[amount] == default) -1 else dp[amount]
    }

}