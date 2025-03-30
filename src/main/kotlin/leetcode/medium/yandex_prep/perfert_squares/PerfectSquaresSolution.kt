package leetcode.medium.yandex_prep.perfert_squares

class PerfectSquaresSolution {

    fun numSquares(n: Int): Int {
        val dp = IntArray(n + 1) { n }
        dp[0] = 0

        for(target in 1..n) {
            inner@ for (s in 1..target) {
                val square = s * s
                if (target - square >= 0) {
                    dp[target] = minOf(dp[target], dp[target - square] + 1)
                } else {
                    break@inner
                }
            }
        }
        return dp[n]
    }

}