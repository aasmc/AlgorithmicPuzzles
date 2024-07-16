package leetcode.medium.prefix_sum.range_sum_query_2d

class NumMatrix(matrix: Array<IntArray>) {

    private val prefixMatrix = Array<IntArray>(matrix.size + 1) {
        IntArray(matrix[0].size + 1)
    }

    init {
        for (row in 1 until prefixMatrix.size) {
            for (col in 1 until prefixMatrix[0].size) {
                if (col == 1) {
                    // leftmost col prefix sum: previous prefix sum + current value
                    prefixMatrix[row][col] = prefixMatrix[row - 1][col] + matrix[row - 1][0]
                } else {
                    // prefix sum for other columns:
                    // prefix sum from the column to the left + current value
                    // + prefix sum of the current column up to the current value not including
                    // to calculate prefix sum of the current column we need to subtract
                    // prefix sum of the previous row and col from previous prefix sum for
                    // the current column
                    prefixMatrix[row][col] = prefixMatrix[row][col - 1] +
                            matrix[row - 1][col - 1] +
                            (prefixMatrix[row - 1][col] - prefixMatrix[row - 1][col - 1])
                }
            }
        }
    }

    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        return prefixMatrix[row2 + 1][col2 + 1] - prefixMatrix[row1][col2 + 1] -
                (prefixMatrix[row2 + 1][col1] - prefixMatrix[row1][col1])
    }

}