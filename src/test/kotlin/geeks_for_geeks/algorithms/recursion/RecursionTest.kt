package geeks_for_geeks.algorithms.recursion

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

internal class RecursionTest {

    @Test
    fun sumOfNRecursive_correct() {
        assertAll(
            "SumOfN Recursion",
            { assertEquals(0, sumOfNRecursive(0)) },
            { assertEquals(1, sumOfNRecursive(1)) },
            { assertEquals(15, sumOfNRecursive(5)) },
            { assertEquals(21, sumOfNRecursive(6)) },
        )
    }

    @Test
    fun isPalindromeRecursive_correct() {
        assertAll(
            "Is Palindrome Recursion",
            { assertEquals(true, isPalindromeRecursive("")) },
            { assertEquals(true, isPalindromeRecursive("a")) },
            { assertEquals(true, isPalindromeRecursive("aa")) },
            { assertEquals(true, isPalindromeRecursive("abba")) },
            { assertEquals(true, isPalindromeRecursive("aaa")) },
            { assertEquals(true, isPalindromeRecursive("aaabbbaaa")) },
            { assertEquals(false, isPalindromeRecursive("aaab_aaa")) },
            { assertEquals(false, isPalindromeRecursive("ab")) },
        )
    }
}