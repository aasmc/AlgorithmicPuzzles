package leetcode.top_interview_150.array_string.index_of_first_occurrence

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class IndexOfFirstOccurrenceSolutionTest {

    private val sut = IndexOfFirstOccurrenceSolution()

    @Test
    fun testCorrect() {
        assertEquals(0, sut.strStr("sadbutsad", "sad"))
        assertEquals(-1, sut.strStr("leetcode", "leeto"))
        assertEquals(-1, sut.strStr("aaa", "bbb"))
        assertEquals(0, sut.strStr("abcabcacb", "abc"))
        assertEquals(5, sut.strStr("ababcabcd", "abcd"))
    }

}