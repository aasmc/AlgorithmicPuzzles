package geeks_for_geeks.algorithms.recursion

import org.junit.jupiter.api.Assertions
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

    @Test
    fun sumOfDigitsRecursive_correct() {
        assertAll(
            "SumOfDigits Recursion",
            { assertEquals(0, sumOfDigitsRecursive(0)) },
            { assertEquals(1, sumOfDigitsRecursive(10)) },
            { assertEquals(6, sumOfDigitsRecursive(15)) },
            { assertEquals(16, sumOfDigitsRecursive(367)) },
            { assertEquals(37, sumOfDigitsRecursive(667738)) },
        )
    }
    @Test
    fun sumOfDigitsNoTailRecRecursive_correct() {
        assertAll(
            "SumOfDigits No tailrec Recursion",
            { assertEquals(0, sumOfDigitsNoTailrec(0)) },
            { assertEquals(1, sumOfDigitsNoTailrec(10)) },
            { assertEquals(6, sumOfDigitsNoTailrec(15)) },
            { assertEquals(16, sumOfDigitsNoTailrec(367)) },
            { assertEquals(37, sumOfDigitsNoTailrec(667738)) },
        )
    }

    @Test
    fun ropeCutting_correct() {
        assertAll(
            "Rope cutting",
            { assertEquals(5, ropeCutting(5, 2,5,1)) },
            { assertEquals(2, ropeCutting(23, 12, 9,11)) },
            { assertEquals(-1, ropeCutting(5, 4,2,6)) },
            { assertEquals(-1, ropeCutting(9, 2,2,2)) },
        )
    }

    @Test
    fun generatePowerSetRecursive_correct() {
        val expected = setOf<String>("", "a", "b", "c", "ab", "ac", "bc", "abc")
        val result = generateSubsetsRecursive("abc")
        Assertions.assertEquals(expected.size, result.size)
        Assertions.assertTrue(expected.containsAll(result))
    }

    @Test
    fun josephusPermutationsRecursive_correct() {
        assertAll(
            "Josephus permutations",
            { assertEquals(3, josephusRecursion(7, 3)) },
            { assertEquals(3, josephusRecursion(5, 3)) },
        )
    }

}


























