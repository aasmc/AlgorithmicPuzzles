package leetcode.top_interview_150.hashmap.isomorphic_strings

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class IsomorphicStringsSolutionTest {

    private val sut = IsomorphicStringsSolution()

    @Test
    fun testCorrect() {

        assertTrue(sut.isIsomorphic("egg", "add"))
        assertFalse(sut.isIsomorphic("foo", "bar"))
        assertTrue(sut.isIsomorphic("paper", "title"))

    }

}