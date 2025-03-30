package leetcode.dp.minimum_failing_path

fun minFallingPathSum(matrix: Array<IntArray>): Int {
    var dp = IntArray(matrix[0].size + 2)
    dp[0] = Int.MAX_VALUE
    dp[dp.lastIndex] = Int.MAX_VALUE
    for (i in matrix[0].indices) {
        dp[i + 1] = matrix[0][i]
    }

    var dp2 = IntArray(dp.size)
    dp2[0] = Int.MAX_VALUE
    dp2[dp.lastIndex] = Int.MAX_VALUE

    for (row in 1 until matrix.size) {
        for (col in matrix[row].indices) {
            val dpIdx = col + 1
            dp2[dpIdx] = matrix[row][col] + minOf(dp[dpIdx - 1], dp[dpIdx], dp[dpIdx + 1])
        }
        dp = dp2
        dp2 = IntArray(dp.size)
        dp2[0] = Int.MAX_VALUE
        dp2[dp.lastIndex] = Int.MAX_VALUE
    }
    return dp.minBy { it }
}