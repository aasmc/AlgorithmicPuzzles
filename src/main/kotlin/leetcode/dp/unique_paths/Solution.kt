package leetcode.dp.unique_paths

fun uniquePaths(m: Int, n: Int): Int {
    // dp[i][j] will store the number of unique paths to get to that point.
    // explicitly make the array one row and one col larger than the original grid,
    // to avoid checking for out of bounds
    val dp = Array<IntArray>(m + 1) {
        IntArray(n + 1) { 0 }
    }
    // we know for sure that moving down the first column is the only way, so
    // this is our base
    for (i in 1..m) {
        dp[i][1] = 1
    }
    // we also know that moving right the first row is the only path, so
    // this is also our base
    for (i in 1..n) {
        dp[1][i] = 1
    }
    // starting from the second row
    for (i in 2..m) {
        for (j in 2..n) { // and second column
            // we can calculate the number of paths to get to dp[i][j]
            // as the sum of paths from "upper cell" and "left cell"
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
        }
    }
    return dp[m][n]
}