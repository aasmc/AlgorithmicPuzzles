package leetcode.ace_coding.strings.merge_strings

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MergeStringsAlternatelyTest {

    @Test
    fun testMergeEqualStrings() {
        val one = "abc"
        val two = "pqr"
        val expected = "apbqcr"
        val result = mergeAlternately(one, two)
        assertEquals(expected, result)
    }

    @Test
    fun testMergeLeftStringSmaller() {
        val one = "ab"
        val two = "pqrs"
        val expected = "apbqrs"
        val result = mergeAlternately(one, two)
        assertEquals(expected, result)
    }

    @Test
    fun testMergeRightStringSmaller() {
        val one = "abcd"
        val two = "pq"
        val expected = "apbqcd"
        val result = mergeAlternately(one, two)
        assertEquals(expected, result)
    }

}