package geeks_for_geeks.algorithms.matrix

fun convertMatrixInSnakeForm(matrix: Array<IntArray>): List<Int> {
    val result = mutableListOf<Int>()
    for (i in matrix.indices) {
        if (i % 2 == 0) {
            for (j in matrix[i].indices) {
                result.add(matrix[i][j])
            }
        } else {
            for (j in matrix[i].lastIndex downTo 0) {
                result.add(matrix[i][j])
            }
        }
    }
    return result
}