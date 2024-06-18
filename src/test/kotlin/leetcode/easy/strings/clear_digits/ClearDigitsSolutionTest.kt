package leetcode.easy.strings.clear_digits

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ClearDigitsSolutionTest {

    private val sut = ClearDigitsSolution()

    @Test
    fun testCorrect() {
//        assertEquals("abc", sut.clearDigits("abc"))
        assertEquals("", sut.clearDigits("cb34"))
    }

}