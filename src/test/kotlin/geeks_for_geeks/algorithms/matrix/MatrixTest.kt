package geeks_for_geeks.algorithms.matrix

import geeks_for_geeks.algorithms.arrays.array
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

    @Test
    fun transposeMatrix_correct_two_by_two() {
        val rowOne = intArrayOf(1, 2)
        val rowTwo = intArrayOf(1, 2)
        val matrix = arrayOf(rowOne, rowTwo)
        transposeMatrix(matrix)
        assertEquals(1, matrix[0][0])
        assertEquals(1, matrix[0][1])
        assertEquals(2, matrix[1][0])
        assertEquals(2, matrix[1][1])
    }

    @Test
    fun transposeMatrix_correct_four_by_four() {
        val rowOne = intArrayOf(1, 2, 3, 4)
        val rowTwo = intArrayOf(5, 6, 7, 8)
        val rowThree = intArrayOf(9, 10, 11, 12)
        val rowFour = intArrayOf(13, 14, 15, 16)
        val matrix: Array<IntArray> = arrayOf(rowOne, rowTwo, rowThree, rowFour)
        val expected1 = intArrayOf(1, 5, 9, 13)
        val expected2 = intArrayOf(2, 6, 10, 14)
        val expected3 = intArrayOf(3, 7, 11, 15)
        val expected4 = intArrayOf(4, 8, 12, 16)
        transposeMatrix(matrix)
        assertTrue(expected1.contentEquals(matrix[0]))
        assertTrue(expected2.contentEquals(matrix[1]))
        assertTrue(expected3.contentEquals(matrix[2]))
        assertTrue(expected4.contentEquals(matrix[3]))
    }

    @Test
    fun rotateMatrixToLeft_correct() {
        val rowOne = intArrayOf(1, 2, 3, 4)
        val rowTwo = intArrayOf(5, 6, 7, 8)
        val rowThree = intArrayOf(9, 10, 11, 12)
        val rowFour = intArrayOf(13, 14, 15, 16)
        val matrix: Array<IntArray> = arrayOf(rowOne, rowTwo, rowThree, rowFour)
        val expected1 = intArrayOf(4, 8, 12, 16)
        val expected2 = intArrayOf(3, 7, 11, 15)
        val expected3 = intArrayOf(2, 6, 10, 14)
        val expected4 = intArrayOf(1, 5, 9, 13)
        rotateMatrixToLeft(matrix)
        assertTrue(expected1.contentEquals(matrix[0]))
        assertTrue(expected2.contentEquals(matrix[1]))
        assertTrue(expected3.contentEquals(matrix[2]))
        assertTrue(expected4.contentEquals(matrix[3]))
    }

    @Test
    fun spiralTraversalOfMatrix_singleRow_correct() {
        val rowOne = intArrayOf(1, 2, 3, 4)
        val matrix: Array<IntArray> = arrayOf(rowOne)
        val result = spiralTraversalOfMatrix(matrix)
        val expected = rowOne.toList()
        assertEquals(expected, result)
    }

    @Test
    fun spiralTraversalOfMatrix_singleColumn_correct() {
        val rowOne = intArrayOf(1,)
        val rowTwo = intArrayOf(5, )
        val rowThree = intArrayOf(9,)
        val rowFour = intArrayOf(13,)
        val matrix: Array<IntArray> = arrayOf(rowOne, rowTwo, rowThree, rowFour)
        val result = spiralTraversalOfMatrix(matrix)
        val expected = listOf<Int>(1,5,9,13)
        assertEquals(expected, result)
    }

    @Test
    fun spiralTraversalOfMatrix_correct() {
        val rowOne = intArrayOf(1, 2, 3, 4)
        val rowTwo = intArrayOf(5, 6, 7, 8)
        val rowThree = intArrayOf(9, 10, 11, 12)
        val rowFour = intArrayOf(13, 14, 15, 16)
        val matrix: Array<IntArray> = arrayOf(rowOne, rowTwo, rowThree, rowFour)
        val result = spiralTraversalOfMatrix(matrix)
        val expected = listOf<Int>(1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10)
        assertEquals(expected, result)
    }

    @Test
    fun searchInMatrixWithColumnsAndRowsSorted_correct() {
        val rowOne = intArrayOf(1, 2, 3, 4)
        val rowTwo = intArrayOf(5, 6, 7, 8)
        val rowThree = intArrayOf(9, 10, 11, 12)
        val rowFour = intArrayOf(13, 14, 15, 16)
        val matrix: Array<IntArray> = arrayOf(rowOne, rowTwo, rowThree, rowFour)
        val target = 11
        val result = searchInMatrixWithColumnsAndRowsSorted(matrix, target)
        assertEquals(2, result.first)
        assertEquals(2, result.second)

        val notFound = 30
        val notFoundRes = searchInMatrixWithColumnsAndRowsSorted(matrix, notFound)
        assertEquals(-1, notFoundRes.first)
        assertEquals(-1, notFoundRes.second)
    }

    @Test
    fun matrixMedian_correct() {
        val rowOne = intArrayOf(5,10,20,30,40)
        val rowTwo = intArrayOf(1,2,3,4,6)
        val rowThree = intArrayOf(11,13,15,17,19)
        val matrix: Array<IntArray> = arrayOf(rowOne, rowTwo, rowThree)
        val expected = 11
        val result = matrixMedian(matrix)
        assertEquals(expected, result)
    }

}