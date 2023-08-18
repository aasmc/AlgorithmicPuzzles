package leetcode.dp.triangle


fun minimumTotal(triangle: List<List<Int>>): Int {
    val dp = IntArray(triangle[triangle.lastIndex].size + 1) { 0 }
    for (i in triangle.lastIndex downTo 0) {
        val current = triangle[i]
        for ((idx, n) in current.withIndex()) {
            dp[idx] = n + minOf(dp[idx], dp[idx + 1])
        }
    }
    return dp[0]
}


fun minimumTotalRec(triangle: List<List<Int>>): Int {
    return findMin(0, triangle, 0)
}

private fun findMin(currentRow: Int, triangle: List<List<Int>>, idx: Int): Int {
    if (currentRow == triangle.lastIndex) {
        return triangle[currentRow][idx]
    }
    var result = triangle[currentRow][idx]
    val leftMin = findMin(currentRow + 1, triangle, idx)
    val rightMin = findMin(currentRow + 1, triangle, idx + 1)
    result += minOf(leftMin, rightMin)
    return result
}

