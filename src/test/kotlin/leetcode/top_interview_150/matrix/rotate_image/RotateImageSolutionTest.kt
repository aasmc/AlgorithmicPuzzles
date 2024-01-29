package leetcode.top_interview_150.matrix.rotate_image

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RotateImageSolutionTest {

    private val sut = RotateImageSolution()

    @Test
    fun testCorrect() {

        val m1 = arrayOf(
            intArrayOf(1,2,3),
            intArrayOf(4,5,6),
            intArrayOf(7,8,9)
        )

        val ex1 = arrayOf(
            intArrayOf(7,4,1),
            intArrayOf(8,5,2),
            intArrayOf(9,6,3)
        )

        sut.rotate(m1)
        for (i in m1.indices) {
            for (j in m1.indices) {
                assertEquals(ex1[i][j], m1[i][j])
            }
        }

    }

}