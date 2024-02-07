package leetcode.top_interview_150.intervals.insert_interval

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InsertIntervalSolutionTest {

    private val sut = InsertIntervalSolution()

    @Test
    fun testCorrect() {

//        val in1 = arrayOf(
//            intArrayOf(1,2),
//            intArrayOf(6,9),
//        )
//
//        val new1 = intArrayOf(2, 5)
//        val ex1 = arrayOf(
//            intArrayOf(1, 5),
//            intArrayOf(6, 9)
//        )
//
//        val res1 = sut.insert(in1, new1)
//        for (i in res1.indices) {
//            for (j in res1[i].indices) {
//                assertEquals(ex1[i][j], res1[i][j])
//            }
//        }

        val ex2 = arrayOf(intArrayOf(2, 7))
        val res2 = sut.insert(arrayOf(intArrayOf(1, 5)), intArrayOf(2, 7))

        for (i in res2.indices) {
            for (j in res2[i].indices) {
                assertEquals(ex2[i][j], res2[i][j])
            }
        }

    }

}