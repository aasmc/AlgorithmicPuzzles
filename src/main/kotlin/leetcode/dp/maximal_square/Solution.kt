package leetcode.dp.maximal_square

fun maximalSquare(matrix: Array<CharArray>): Int {
    val dp = Array<IntArray>(matrix.size) {
        IntArray(matrix[0].size) { 0 }
    }
    fillLastRow(dp, matrix)
    fillLastColumn(matrix, dp)
    val row = matrix.lastIndex - 1
    val col = matrix[0].lastIndex - 1
    for (i in row downTo 0) {
        for (j in col downTo 0) {
            if (matrix[i][j] == '0') {
                dp[i][j] = 0
            } else {
                dp[i][j] = minOf(dp[i][j + 1], dp[i + 1][j], dp[i + 1][j + 1]) + getDigitAt(j, matrix[i])
            }
        }
    }
    val max = getMax(dp)
    return max * max
}

private fun getMax(dp: Array<IntArray>): Int {
    var max = Int.MIN_VALUE
    for (arr in dp) {
        for (elem in arr) {
            if (max < elem) {
                max = elem
            }
        }
    }
    return max
}

private fun fillLastColumn(matrix: Array<CharArray>, dp: Array<IntArray>) {
    for (i in 0 until matrix.lastIndex) {
        dp[i][dp[0].lastIndex] = getDigitAt(matrix[0].lastIndex, matrix[i])
    }
}

private fun fillLastRow(dp: Array<IntArray>, matrix: Array<CharArray>) {
    dp[dp.lastIndex] = convertToDigits(matrix[matrix.lastIndex])
}

private fun convertToDigits(array: CharArray): IntArray {
    return array.map { Character.getNumericValue(it) }.toIntArray()
}

private fun getDigitAt(idx: Int, array: CharArray): Int {
    return Character.getNumericValue(array[idx])
}