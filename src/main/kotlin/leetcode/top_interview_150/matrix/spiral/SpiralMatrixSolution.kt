package leetcode.top_interview_150.matrix.spiral

class SpiralMatrixSolution {

    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        var left = 0
        var right = matrix[0].lastIndex
        var top = 0
        var bottom = matrix.lastIndex
        val result = mutableListOf<Int>()

        while (left <= right && top <= bottom) {
            for (i in left..right) {
                result.add(matrix[top][i])
            }
            ++top
            for (i in top..bottom) {
                result.add(matrix[i][right])
            }
            --right
            if (top <= bottom) {
                for (i in right downTo left) {
                    result.add(matrix[bottom][i])
                }
                --bottom
            }
            if (left <= right) {
                for (i in bottom downTo top) {
                    result.add(matrix[i][left])
                }
                ++left
            }
        }
        return result
    }

}