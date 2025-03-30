package leetcode.dp.unique_paths2

fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
    val dp = Array<IntArray>(obstacleGrid.size + 1) {
        IntArray(obstacleGrid[0].size + 1) { 0 }
    }
    // fill all columns in top row with 1 while not encounter an obstacle
    var col = 1
    while (col < dp[0].size && obstacleGrid[0][col - 1] != 1) {
        dp[1][col++] = 1
    }
    // fill left row with 1 while not encounter an obstacle
    var row = 1
    while (row < dp.size && obstacleGrid[row - 1][0] != 1) {
        dp[row++][1] = 1
    }

    for (i in 2 until dp.size) {
        for (j in 2 until dp[0].size)  {
            if (obstacleGrid[i - 1][j - 1] == 1) {
                dp[i][j] = 0
            } else {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            }
        }
    }
    return dp[obstacleGrid.size][obstacleGrid[0].size]
}