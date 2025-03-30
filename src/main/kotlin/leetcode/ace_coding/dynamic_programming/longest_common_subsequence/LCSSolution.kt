package leetcode.ace_coding.dynamic_programming.longest_common_subsequence

class LCSSolution {

    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val dp = Array<IntArray>(text1.length + 1) {
            IntArray(text2.length + 1) { 0 }
        }

        for (row in 1 .. text1.length) {
            for (col in 1 .. text2.length) {
                dp[row][col] = if (text1[row - 1] == text2[col - 1]) {
                    dp[row - 1][col - 1] + 1
                } else {
                    maxOf(dp[row - 1][col], dp[row][col - 1])
                }
            }
        }
        return dp[text1.length][text2.length]
    }

}