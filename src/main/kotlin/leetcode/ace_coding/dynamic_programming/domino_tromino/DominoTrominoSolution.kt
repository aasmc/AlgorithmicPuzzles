package leetcode.ace_coding.dynamic_programming.domino_tromino

class DominoTrominoSolution {

    fun numTilings(n: Int): Int {
        val mod = 1000000007
        val dp = LongArray(1001)
        dp[1] = 1
        dp[2] = 2
        dp[3] = 5
        if (n <= 3) {
            return dp[n].toInt()
        }
        for (i in 4..n) {
            val res = (2 * dp[i - 1] + dp[i - 3]) % mod
            dp[i] = res
        }
        return dp[n].toInt()
    }

}