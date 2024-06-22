package leetcode.easy.sliding_window.minimum_recolors

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MinimumRecolorsSolutionTest {

    private val sut = MinimumRecolorsSolution()

    @Test
    fun testCorrect() {

        assertEquals(3, sut.minimumRecolors("WBBWWBBWBW", 7))
        assertEquals(0, sut.minimumRecolors("WBWBBBW", 2))
        assertEquals(4, sut.minimumRecolors("BWBBWWBBBWBWWWBWWBBWBWBBWBB", 11))

    }

}