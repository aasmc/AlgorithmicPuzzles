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
        assertEquals(10, gdcEuclidModulo(20, 30))
    }

    @Test
    fun gdcEuclidTraditionalCorrect() {
        assertEquals(10, gdcEuclidTraditional(20, 30))
    }
}

































