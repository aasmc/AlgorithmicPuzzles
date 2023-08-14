package leetcode.dp.minimum_path_sum

fun minPathSum(grid: Array<IntArray>): Int {
    val dp = Array<IntArray>(grid.size + 1) {
        IntArray(grid[0].size + 1) { 0 }
    }
    dp[1][1] = grid[0][0]

    for (i in 2..grid.size) {
        dp[i][1] = grid[i - 1][0] + dp[i - 1][1]
    }
    for (i in 2..grid[0].size) {
        dp[1][i] = grid[0][i - 1] + dp[1][i - 1]
    }
    for (row in 2..grid.size) {
        for (col in 2 .. grid[0].size) {
            dp[row][col] = minOf(dp[row - 1][col], dp[row][col - 1]) + grid[row - 1][col - 1]
        }
    }
    return dp[grid.size][grid[0].size]
}