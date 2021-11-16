package leetcode.algo_study_plan01.move_zeroes

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MoveZeroesTest {

    @Test
    fun moveZeroes_whenZerosAreInTheMiddle() {
        val input = intArrayOf(0,1,0,3,12)
        val expected = intArrayOf(1,3,12,0,0)
        moveZeroes(input)
        for(i in input.indices) {
            assertEquals(expected[i], input[i])
        }
    }

    @Test
    fun moveZeroes_arrayOfSingleZero() {
        val input = intArrayOf(0)
        val expected = intArrayOf(0)
        moveZeroes(input)
        for(i in input.indices) {
            assertEquals(expected[i], input[i])
        }
    }

    @Test
    fun moveZeroes_manyLeadingZeroes() {
        val input = intArrayOf(0,0,0,0,0,4,3,6,7,2,4)
        val expected = intArrayOf(4,3,6,7,2,4,0,0,0,0,0)
        moveZeroes(input)
        for(i in input.indices) {
            assertEquals(expected[i], input[i])
        }
    }

    @Test
    fun moveZeroes_manyTrailingZeroes() {
        val input = intArrayOf(4,3,6,7,2,4,0,0,0,0,0)
        val expected = intArrayOf(4,3,6,7,2,4,0,0,0,0,0)
        moveZeroes(input)
        for(i in input.indices) {
            assertEquals(expected[i], input[i])
        }
    }

    @Test
    fun moveZeroes_alternatingZeroes() {
        val input = intArrayOf(1,0,2,0,3,0,4,0,5,0)
        val expected = intArrayOf(1,2,3,4,5,0,0,0,0,0)
        moveZeroes(input)
        for(i in input.indices) {
            assertEquals(expected[i], input[i])
        }
    }

    @Test
    fun moveZeroes_mixedZeroes() {
        val input = intArrayOf(1,0,0,0,2,0,0,3,0,4,0,0,0,5,0)
        val expected = intArrayOf(1,2,3,4,5,0,0,0,0,0,0,0,0,0,0)
        moveZeroes(input)
        for(i in input.indices) {
            assertEquals(expected[i], input[i])
        }
    }

}

















