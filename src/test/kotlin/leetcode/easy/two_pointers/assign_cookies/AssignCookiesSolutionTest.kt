package leetcode.easy.two_pointers.assign_cookies

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AssignCookiesSolutionTest {

    private val sut = AssignCookiesSolution()

    @Test
    fun testCorrect() {

        assertEquals(1, sut.findContentChildren(intArrayOf(1,2,3), intArrayOf(1,1)))
        assertEquals(2, sut.findContentChildren(intArrayOf(1,2), intArrayOf(1,2,3)))

    }

}