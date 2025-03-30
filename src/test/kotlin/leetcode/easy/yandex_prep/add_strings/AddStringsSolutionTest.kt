package leetcode.easy.yandex_prep.add_strings

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AddStringsSolutionTest {

    private val sut = AddStringsSolution()

    @Test
    fun testCorrect() {

//        assertEquals("533", sut.addStrings("456", "77"))
//        assertEquals("134", sut.addStrings("11", "123"))
        assertEquals("108", sut.addStrings("99", "9"))

    }

}