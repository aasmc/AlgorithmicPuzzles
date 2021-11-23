package geeks_for_geeks.algorithms.math

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.math.exp

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
        assertFalse(isPrimeUsingLoopEfficient(25))
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
        val expected = listOf(2, 3, 3, 5, 5)
        for (i in res.indices) {
            assertEquals(expected[i], res[i])
        }
    }

    @Test
    fun primeFactorsEfficient_correct84() {
        val res = primeFactorsEfficient(84)
        val expected = listOf(2, 2, 3, 7)
        for (i in res.indices) {
            assertEquals(expected[i], res[i])
        }
    }

    @Test
    fun allDivisorsOfNumberNaive() {
        val res = divisorsOfNumberNaive(15)
        val expected = listOf(1, 3, 5, 15)
        expected.forEachIndexed { index, i ->
            assertEquals(i, res[index])
        }
    }

    @Test
    fun allDivisorsOfNumberEfficient() {
        val res = divisorsOfNumberEfficient(15)
        val expected = listOf(1, 3, 5, 15)
        expected.forEachIndexed { index, i ->
            assertEquals(i, res[index])
        }
    }

    @Test
    fun allDivisorsOfNumberEfficientOne() {
        val res = divisorsOfNumberEfficient(1)
        val expected = listOf(1)
        expected.forEachIndexed { index, i ->
            assertEquals(i, res[index])
        }
    }

    @Test
    fun allDivisorsOfNumberEfficient15() {
        val res = divisorsOfNumberEfficient(25)
        val expected = listOf(1, 5, 25)
        expected.forEachIndexed { index, i ->
            assertEquals(i, res[index])
        }
    }

    @Test
    fun primesLessThanOrEqualTo10() {
        val expected = listOf(2,3,5,7)
        val res = primesLessThanOrEqualTo(10)
        expected.forEachIndexed { index, i ->
            assertEquals(i, res[index])
        }
        assertEquals(expected.size, res.size)
    }

    @Test
    fun primesLessThanOrEqualTo23() {
        val expected = listOf(2,3,5,7,11,13,17,19, 23)
        val res = primesLessThanOrEqualTo(23)
        expected.forEachIndexed { index, i ->
            assertEquals(i, res[index])
        }
        assertEquals(expected.size, res.size)
    }

    @Test
    fun sieveOfEratosthenes23() {
        val expected = listOf(2,3,5,7,11,13,17,19, 23)
        val res = sieveOfEratosthenes(23)
        expected.forEachIndexed { index, i ->
            assertEquals(i, res[index])
        }
        assertEquals(expected.size, res.size)
    }

    @Test
    fun powerNaive_toThePowerOfZero() {
        assertEquals(1, powerNaive(10, 0))
    }

    @Test
    fun powerNaive_toThePowerOfOne() {
        assertEquals(10, powerNaive(10, 1))
    }

    @Test
    fun powerNaive_toThePowerOfThree() {
        assertEquals(8, powerNaive(2, 3))
    }

    @Test
    fun powerRec_toThePowerOfZero() {
        assertEquals(1, powerRecursionEffective(10, 0))
    }

    @Test
    fun powerRec_toThePowerOfOne() {
        assertEquals(10, powerRecursionEffective(10, 1))
    }

    @Test
    fun powerRec_toThePowerOfThree() {
        assertEquals(8, powerRecursionEffective(2, 3))
    }

    @Test
    fun powerRec_toThePowerOfFive() {
        assertEquals(243, powerRecursionEffective(3, 5))
    }

    @Test
    fun powerIterative_toThePowerOfZero() {
        assertEquals(1, powerIterative(10, 0))
    }

    @Test
    fun powerIterative_toThePowerOfOne() {
        assertEquals(10, powerIterative(10, 1))
    }

    @Test
    fun powerIterative_toThePowerOfThree() {
        assertEquals(8, powerIterative(2, 3))
    }

    @Test
    fun powerIterative_toThePowerOfFive() {
        assertEquals(243, powerIterative(3, 5))
    }

    @Test
    fun quadraticEquasion_twoRoots() {
        val res = quadraticEquasionRoots(1, -7, 12)
        assertEquals(4, res[0])
        assertEquals(3, res[1])
    }

    @Test
    fun quadraticEquasion_oneRoot() {
        val res = quadraticEquasionRoots(1, -2, 1)
        assertEquals(1, res[0])
        assertEquals(1, res[1])
    }

    @Test
    fun quadraticEquasion_noRoots() {
        val res = quadraticEquasionRoots(752, 904, 164)
        assertEquals(-1, res[0])
        assertEquals(-1, res[1])
    }

    @Test
    fun digitsInFactorial() {
        val expected = 52
        val res = digitsInFactorial(42)
        assertEquals(expected, res)
    }

    @Test
    fun termOfGdpTest() {
        val res = termOfGDB(2,3,1)
        assertEquals(2.0, res)
    }

    @Test
    fun termOfGdpTestSecond() {
        val res = termOfGDB(2,4,5)
        assertEquals(32.0, res)
    }

    @Test
    fun exactly3Divisors() {
        val expected = 168
        assertEquals(expected, exactly3Divisors(999999))
    }

    @Test
    fun sumUnderModulo_correct() {
        val expected = 582344006L
        assertEquals(expected, sumUnderModulo(9223372036854775807, 9223372036854775807))
    }

    @Test
    fun multiplicationUnderModulo_correct() {
        val expected = 484266119L
        assertEquals(expected, multiplicationUnderModulo(92233720368547758, 92233720368547758))
    }

    @Test
    fun moduloInverse_correct() {
        val expected = 12
        assertEquals(expected, modInverse(10, 17))
    }
}

































