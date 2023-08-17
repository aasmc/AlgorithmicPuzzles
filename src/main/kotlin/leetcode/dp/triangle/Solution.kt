package leetcode.dp.triangle

fun minimumTotal(triangle: List<List<Int>>): Int {
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

