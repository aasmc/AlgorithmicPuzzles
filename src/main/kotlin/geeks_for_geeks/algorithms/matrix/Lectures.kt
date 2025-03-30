package geeks_for_geeks.algorithms.matrix

import java.util.*
import kotlin.math.abs

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

/**
 * Given a matrix with rows sorted in ascending order and
 * columns sorted in ascending order, search for a position of [target] value
 * in the matrix.
 *
 * Time Complexity O(n + m) where n is the number of rows, and m is the number of columns.
 *
 * Solution takes into account the fact that rows and columns are sorted.
 * We start from top right corner and check if the element is equal to the target element,
 * if so, return its position.
 * If the element is larger than the target, then we know that all elements in that
 * column will be larger than the target, so we simply move one column to the left,
 * basically skipping the entire column.
 * If the element is smaller than the target, then we know that all elements to the left of
 * the current element in that row will be smaller than the target as well, so we skip the
 * row as well and move down one row.
 *
 * @return Pair of row, col position of the [target] value or Pair(-1, -1) if no
 *          such value is present in the matrix.
 */
fun searchInMatrixWithColumnsAndRowsSorted(
    matrix: Array<IntArray>,
    target: Int
): Pair<Int, Int> {
    var row = 0
    var col = matrix[0].lastIndex
    if (target < matrix[0][0]) {
        return Pair(-1, -1)
    }
    if (target > matrix[matrix.lastIndex][col]) {
        return Pair(-1, -1)
    }
    while (row < matrix.size && col >= 0) {
        if (matrix[row][col] == target) {
            return Pair(row, col)
            // if element at this row col position is smaller, then skip the column
        } else if (matrix[row][col] > target) {
            --col
            // if element at this row col position is greater than target, then skip the row
        } else {
            ++row
        }
    }
    return Pair(-1, -1)
}


/**
 * Given a matrix with odd total number of elements,
 * where all elements in the rows are sorted in ascending order,
 * find the median elements of the matrix.
 *
 * Time Complexity(R * log(max - min) * logC) where R - number of rows,
 * C - number of columns, max - maximum element in the matrix, min - minimum element in
 * the matrix.
 *
 * The solution is based on the idea of binary search.
 */
fun matrixMedian(matrix: Array<IntArray>): Int {
    val rows = matrix.size
    val cols = matrix[0].size
    // minimum elements in the matrix will be in the first column
    var min = matrix[0][0]
    // maximum elements in the matrix will be in the last column
    var max = matrix[0][cols - 1]
    // traverse every row to find min and max elements in the matrix
    // time complexity O(R)
    for (i in 1 until rows) {
        if (matrix[i][0] < min) min = matrix[i][0]
        if (matrix[i][cols - 1] > max) max = matrix[i][cols - 1]
    }
    val medianPosition = (rows * cols + 1) / 2
    // Time complexity O(R * log(max - min) * logC)
    while (min < max) {
        val mid = min + (max - min) / 2
        var midPos = 0
        // Time complexity O(R * logC)
        for(i in 0 until rows) {
            // search for a position of mid in the current row using
            // Arrays#binarySearch that returns
            // index of the search key, if it is contained in the array;
            // otherwise, (-(insertion point) - 1)
            // Time Complexity O(logC)
            val pos = Arrays.binarySearch(matrix[i], mid) + 1
            // accumulate the final position of the mid element in the matrix
            // as if the matrix were flat mapped to a list.
            midPos += abs(pos)
        }
        if (midPos < medianPosition) {
            min = mid + 1
        } else {
            // this trick ensures that we find element that is in fact in the matrix
            // if we stopped when midPos == medianPos we could try to return
            // mid element, BUT the mid element was computed as the middle between
            // max and min, so it might not be present in the matrix,
            // therefore we simply keep updating max = mid when either the midPos > medianPosition
            // or midPos == medianPos
            max = mid
        }
    }
    return min
}



















