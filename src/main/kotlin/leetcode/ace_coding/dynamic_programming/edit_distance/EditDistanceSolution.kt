package leetcode.ace_coding.dynamic_programming.edit_distance

class EditDistanceSolution {

    fun minDistance(word1: String, word2: String): Int {
        val dp = Array<IntArray>(word1.length + 1) {
            IntArray(word2.length + 1)
        }
        for (i in 1..word1.length) {
            dp[i][0] = i
        }
        for (i in 1..word2.length) {
            dp[0][i] = i
        }
        for (i in 1 until dp.size) {
            for (j in 1 until dp[0].size) {
                if (word1[i - 1] == word2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    dp[i][j] = minOf(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
                }
            }
        }
        return dp[word1.length][word2.length]
    }

}