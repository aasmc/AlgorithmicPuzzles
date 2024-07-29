package leetcode.medium.yandex_prep.merge_intervals

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class MergeIntervalsSolutionTest {

    private val sut = MergeIntervalsSolution()

    @Test
    fun testCorrect() {

        val ex1 = arrayOf(intArrayOf(1,6), intArrayOf(8,10), intArrayOf(15, 18))
        val res1 = sut.merge(arrayOf(
            intArrayOf(1,3),
            intArrayOf(2,6),
            intArrayOf(8,10),
            intArrayOf(15,18),
        ))

        assertEquals(ex1.size, res1.size)
        for (i in ex1.indices) {
            assertContentEquals(ex1[i], res1[i])
        }
    }

}