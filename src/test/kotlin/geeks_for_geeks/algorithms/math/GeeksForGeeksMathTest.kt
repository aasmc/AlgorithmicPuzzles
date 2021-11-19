package geeks_for_geeks.algorithms.math

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigInteger

internal class GeeksForGeeksMathTest {

    @Test
    fun countDigits_correctOnPositiveNumber() {
        val num = 123
        val expected = 3
        assertEquals(expected, findNumberOfDigitsIn(num))
    }

    @Test
    fun countDigits_correctOnZero() {
        val num = 0
        val expected = 1
        assertEquals(expected, findNumberOfDigitsIn(num))
    }

    @Test
    fun countDigits_correctOnNegativeNumber() {
        val num = -123
        val expected = 3
        assertEquals(expected, findNumberOfDigitsIn(num))
    }

    @Test
    fun isPalindrome_trueOnOddDigitNumber() {
        val number = 313
        assertTrue(isPalindromePositiveNumber(number))
    }

    @Test
    fun isPalindrome_trueOnEvenDigitNumber() {
        val number = 4224
        assertTrue(isPalindromePositiveNumber(number))
    }

    @Test
    fun isPalindrome_trueOnOneDigitNumber() {
        val number = 1
        assertTrue(isPalindromePositiveNumber(number))
    }

    @Test
    fun isPalindrome_false() {
        val number = 44537
        assertFalse(isPalindromePositiveNumber(number))
    }

    @Test
    fun factorialOfOne() {
        assertEquals(BigInteger.ONE, factorialOfNumber(1))
    }

    @Test
    fun factorialOfZero() {
        assertEquals(BigInteger.ONE, factorialOfNumber(0))
    }

    @Test
    fun factorialOfTwo() {
        assertEquals(BigInteger.TWO, factorialOfNumber(2))
    }

    @Test
    fun factorialOfTen() {
        assertEquals(BigInteger.valueOf(3628800L), factorialOfNumber(10))
    }

    @Test
    fun trailingZeroesInFactorialNaiveCorrect() {
        assertEquals(24, trailingZeroesInFactorialNaive(100))
    }

    @Test
    fun trailingZeroesInFactorialEfficientCorrect() {
        assertEquals(24, trailingZeroesInFactorialEfficient(100))
    }

    @Test
    fun gdcEuclidModuloCorrect() {
        assertEquals(10, gcdEuclidModulo(20, 30))
    }

    @Test
    fun gdcEuclidTraditionalCorrect() {
        assertEquals(10, gcdEuclidTraditional(20, 30))
    }

    @Test
    fun lcmNaiveIdentity() {
        assertEquals(1, lowestCommonMultipleNaive(1, 1))
    }

    @Test
    fun lcmNaiveCorrect() {
        assertEquals(21, lowestCommonMultipleNaive(3, 7))
    }

    @Test
    fun lcmEfficientIdentity() {
        assertEquals(1, lowestCommonMultipleNaive(1, 1))
    }

    @Test
    fun lcmEfficientICorrect() {
        assertEquals(21, lowestCommonMultipleNaive(3, 7))
    }

    @Test
    fun isPrimeTwo() {
        assertTrue(isPrimeUsingSqrt(2))
    }

    @Test
    fun isPrimeEvenNumber() {
        assertFalse(isPrimeUsingSqrt(20))
    }

    @Test
    fun isPrimeCorrect() {
        assertTrue(isPrimeUsingSqrt(101))
    }

    @Test
    fun isPrimeTwoLoop() {
        assertTrue(isPrimeUsingLoopEfficient(2))
    }

    @Test
    fun isPrimeEvenNumberLoop() {
        assertFalse(isPrimeUsingLoopEfficient(20))
    }

    @Test
    fun isPrimeCorrectLoop() {
        assertTrue(isPrimeUsingLoopEfficient(101))
    }

    @Test
    fun primeFactorsNaive_correct() {
        val res = primeFactorsNaive(12)
        val expected = listOf(2, 2, 3)
        for (i in res.indices) {
            assertEquals(expected[i], res[i])
        }
    }

    @Test
    fun primeFactorsEfficient_correct() {
        val res = primeFactorsEfficient(450)
        val expected = listOf(2, 3, 3,5,5)
        for (i in res.indices) {
            assertEquals(expected[i], res[i])
        }
    }

    @Test
    fun primeFactorsEfficient_correct84() {
        val res = primeFactorsEfficient(84)
        val expected = listOf(2, 2, 3,7)
        for (i in res.indices) {
            assertEquals(expected[i], res[i])
        }
    }

}

































