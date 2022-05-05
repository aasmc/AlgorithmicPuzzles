package geeks_for_geeks.algorithms.matrix

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MatrixTest {

    @Test
    fun convertMatrixToSnakeForm_correct() {
        val rowOne = intArrayOf(1, 2, 3, 4)
        val rowTwo = intArrayOf(5, 6, 7, 8)
        val rowThree = intArrayOf(9, 10, 11, 12)
        val rowFour = intArrayOf(13, 14, 15, 16)
        val matrix: Array<IntArray> = arrayOf(rowOne, rowTwo, rowThree, rowFour)
        val expected = listOf<Int>(
            1, 2, 3, 4, 8, 7, 6, 5, 9, 10, 11, 12, 16, 15, 14, 13
        )
        assertEquals(expected, convertMatrixInSnakeForm(matrix))
    }

    @Test
    fun boundaryMatrixTraversal_four_rows_correct() {
        val rowOne = intArrayOf(1, 2, 3, 4)
        val rowTwo = intArrayOf(5, 6, 7, 8)
        val rowThree = intArrayOf(9, 10, 11, 12)
        val rowFour = intArrayOf(13, 14, 15, 16)
        val matrix: Array<IntArray> = arrayOf(rowOne, rowTwo, rowThree, rowFour)

        val expected = listOf<Int>(
            1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5
        )
        assertEquals(expected, boundaryMatrixTraversal(matrix))
    }

    @Test
    fun boundaryMatrixTraversal_two_rows_correct() {
        val rowOne = intArrayOf(1, 2, 3, 4)
        val rowTwo = intArrayOf(5, 6, 7, 8)
        val matrix: Array<IntArray> = arrayOf(rowOne, rowTwo)

        val expected = listOf<Int>(
            1, 2, 3, 4, 8, 7, 6, 5
        )
        assertEquals(expected, boundaryMatrixTraversal(matrix))
    }

    @Test
    fun boundaryMatrixTraversal_one_row_correct() {
        val rowOne = intArrayOf(1, 2, 3, 4)
        val matrix: Array<IntArray> = arrayOf(rowOne)

        val expected = listOf<Int>(
            1, 2, 3, 4
        )
        assertEquals(expected, boundaryMatrixTraversal(matrix))
    }

    @Test
    fun boundaryMatrixTraversal_one_column_correct() {
        val rowOne = intArrayOf(1)
        val rowTwo = intArrayOf(2)
        val rowThree = intArrayOf(3)
        val rowFour = intArrayOf(4)
        val matrix: Array<IntArray> = arrayOf(rowOne, rowTwo, rowThree, rowFour)

        val expected = listOf<Int>(
            1, 2, 3, 4
        )
        assertEquals(expected, boundaryMatrixTraversal(matrix))
    }

    @Test
    fun boundaryMatrixTraversal_one_column_one_row_correct() {
        val rowOne = intArrayOf(1)
        val matrix: Array<IntArray> = arrayOf(rowOne)

        val expected = listOf<Int>(
            1
        )
        assertEquals(expected, boundaryMatrixTraversal(matrix))
    }
}