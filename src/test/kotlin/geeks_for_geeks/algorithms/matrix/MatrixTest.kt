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

}