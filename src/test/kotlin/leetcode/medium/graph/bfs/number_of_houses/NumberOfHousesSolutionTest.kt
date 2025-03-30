package leetcode.medium.graph.bfs.number_of_houses

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class NumberOfHousesSolutionTest {

    private val sut = NumberOfHousesSolution()

    @Test
    fun testCorrect() {

//        assertContentEquals(
//            intArrayOf(6, 0, 0),
//            sut.countOfPairs(3, 1, 3)
//        )

        assertContentEquals(
            intArrayOf(10, 8, 2, 0, 0),
            sut.countOfPairs(5, 2, 4)
        )

    }

}