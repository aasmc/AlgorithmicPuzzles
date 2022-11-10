package geeks_for_geeks.algorithms.bst

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BstLecturesTest {

    @Test
    fun ceilingOnLeftSideOfArray_correct() {
        val in1 = intArrayOf(2, 8, 30, 15, 25, 12)
        val ex1 = intArrayOf(-1, -1, -1, 30, 30, 15)

        val ac1 = ceilingOnLeftSideOfArray(in1)
        assertTrue(ex1.contentEquals(ac1))

        val in2 = intArrayOf(30, 20, 10)
        val ex2 = intArrayOf(-1, 30, 20)

        val ac2 = ceilingOnLeftSideOfArray(in2)
        assertTrue(ex2.contentEquals(ac2))

        val in3 = intArrayOf(10, 20, 30)
        val ex3 = intArrayOf(-1, -1, -1)

        val ac3 = ceilingOnLeftSideOfArray(in3)
        assertTrue(ex3.contentEquals(ac3))

        val in4 = intArrayOf(6, 18, 4, 5)
        val ex4 = intArrayOf(-1, -1, 6, 6)

        val ac4 = ceilingOnLeftSideOfArray(in4)
        assertTrue(ex4.contentEquals(ac4))

    }

}