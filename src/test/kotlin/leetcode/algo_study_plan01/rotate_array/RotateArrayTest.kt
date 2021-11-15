package leetcode.algo_study_plan01.rotate_array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RotateArrayTest {

    @Test
    fun reverseArray_correct() {
        val input = intArrayOf(1)
        val output = intArrayOf(1)
        reverse(input, 0, input.lastIndex)
        for (i in input.indices) {
            assertEquals(output[i], input[i])
        }
    }

    @Test
    fun rotateArray_threePositions_correct() {
        val input = intArrayOf(1,2,3,4,5,6,7)
        val k = 3
        val expected = intArrayOf(5,6,7,1,2,3,4)
        rotateByReversing(input, k)

        for (i in input.indices) {
            assertEquals(expected[i], input[i])
        }
    }

    @Test
    fun rotateArray_twoPositions_correct() {
        val input = intArrayOf(-1,-100,3,99)
        val k = 2
        val expected = intArrayOf(3,99,-1,-100)
        rotateByReversing(input, k)

        for (i in input.indices) {
            assertEquals(expected[i], input[i])
        }
    }

    @Test
    fun rotateArray_onePositions_correct() {
        val input = intArrayOf(1)
        val k = 1
        val expected = intArrayOf(1)
        rotateByReversing(input, k)

        for (i in input.indices) {
            assertEquals(expected[i], input[i])
        }
    }

    @Test
    fun rotateArrayOfSizeTwo_twoPositions_correct() {
        val input = intArrayOf(1, 2)
        val k = 2
        val expected = intArrayOf(1, 2)
        rotateByReversing(input, k)

        for (i in input.indices) {
            assertEquals(expected[i], input[i])
        }
    }

    @Test
    fun rotateArrayOfSizeOne_twoPositions_correct() {
        val input = intArrayOf(-1)
        val k = 2
        val expected = intArrayOf(-1)
        rotateByReversing(input, k)

        for (i in input.indices) {
            assertEquals(expected[i], input[i])
        }
    }
}























