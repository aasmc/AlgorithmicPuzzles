package leetcode.easy.graph.path_exists

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PathExistsSolutionTest {

    private val sut = PathExistsSolution()

    @Test
    fun testCorrect() {

        assertTrue(sut.validPath(3, arrayOf(
            intArrayOf(0,1),
            intArrayOf(1,2),
            intArrayOf(2,0)
        ), 0, 2))

    }

}