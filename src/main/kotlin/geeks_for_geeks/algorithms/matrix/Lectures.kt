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

fun boundaryMatrixTraversal(matrix: Array<IntArray>): List<Int> {
    val result = mutableListOf<Int>()
    for (i in matrix[0].indices) {
        result.add(matrix[0][i])
    }
    if (isSingleColumnMatrix(matrix) && matrix.size > 1) {
        for (i in 1 until matrix.size) {
            result.add(matrix[i][0])
        }
    } else if (matrix.size > 1) {
        for (i in 1 until matrix.size) {
            result.add(matrix[i][matrix[i].lastIndex])
        }

        for (i in matrix[matrix.lastIndex].lastIndex - 1 downTo 0) {
            result.add(matrix[matrix.lastIndex][i])
        }

        for (i in matrix.lastIndex - 1 downTo 1) {
            result.add(matrix[i][0])
        }
    }
    return result
}

private fun isSingleColumnMatrix(matrix: Array<IntArray>): Boolean {
    var singleColumn = true
    for (i in matrix.indices) {
        if (matrix[i].size > 1) {
            singleColumn = false
            break
        }
    }
    return singleColumn
}

fun transposeMatrix(matrix: Array<IntArray>) {
    for (i in matrix.indices) {
        for (j in i + 1..matrix[0].lastIndex) {
            val tmp = matrix[i][j]
            matrix[i][j] = matrix[j][i]
            matrix[j][i] = tmp
        }
    }
}

fun rotateMatrixToLeft(matrix: Array<IntArray>) {
    // transpose the matrix
    for (i in matrix.indices) {
        for (j in i + 1..matrix[0].lastIndex) {
            val tmp = matrix[i][j]
            matrix[i][j] = matrix[j][i]
            matrix[j][i] = tmp
        }
    }
    // swap the rows
    for (i in 0 until matrix.size / 2) {
        val tmp = matrix[matrix.lastIndex - i]
        matrix[matrix.lastIndex - i] = matrix[i]
        matrix[i] = tmp
    }
}

/**
 * Given a matrix of n rows and m columns, return the list of
 * matrix elements in spiral form.
 */
fun spiralTraversalOfMatrix(matrix: Array<IntArray>): List<Int> {
    val result = mutableListOf<Int>()
    var top = 0
    var bottom = matrix.lastIndex
    var left = 0
    var right = matrix[0].lastIndex
    while (top <= bottom && left <= right) {
        // traverse topmost row
        for (i in left..right) {
            result.add(matrix[top][i])
        }
        ++top
        // traverse rightmost column
        for (i in top..bottom) {
            result.add(matrix[i][right])
        }
        --right
        // traverse bottommost row if any
        if (top <= bottom) {
            for (i in right downTo left) {
                result.add(matrix[bottom][i])
            }
            --bottom
        }
        // traverse leftmost column if any
        if (left <= right) {
            for (i in bottom downTo top) {
                result.add(matrix[i][left])
            }
            ++left
        }
    }
    return result
}























