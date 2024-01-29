package leetcode.top_interview_150.matrix.rotate_image

class RotateImageSolution {

    fun rotate(matrix: Array<IntArray>): Unit {
        for (row in matrix.indices) {
            for (col in row + 1 until matrix.size) {
                val tmp = matrix[row][col]
                matrix[row][col] = matrix[col][row]
                matrix[col][row] = tmp
            }
        }

        for (arr in matrix) {
            arr.reverse()
        }
    }

}