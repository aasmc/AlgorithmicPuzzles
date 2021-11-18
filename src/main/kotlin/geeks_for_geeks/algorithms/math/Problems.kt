package geeks_for_geeks.algorithms.math

import java.math.BigInteger
import kotlin.math.abs

fun findNumberOfDigitsIn(number: Int): Int {
    if (number == 0) return 1
    var count = 0
    var num = abs(number)
    while (num > 0) {
        num /= 10
        ++count
    }
    return count
}

fun isPalindromePositiveNumber(number: Int): Boolean {
    var reversed = 0
    var current = number
    while (current > 0) {
        reversed *= 10
        reversed += current % 10
        current /= 10
    }
    return reversed == number
}

fun factorialOfNumberUsingFold(number: Int): BigInteger {
    return when (number) {
        0, 1 -> BigInteger.ONE
        else -> {
            (2..number).fold(BigInteger.ONE) { acc, num ->
                acc * BigInteger.valueOf(num.toLong())
            }
        }
    }
}

fun factorialOfNumber(number: Int): BigInteger {
    var result = BigInteger.valueOf(1L)
    for (i in 2..number) {
        result *= BigInteger.valueOf(i.toLong())
    }
    return result
}

fun trailingZeroesInFactorialNaive(number: Int): Int {
    var factorial = when (number) {
        0, 1 -> BigInteger.ONE
        else -> (2..number).fold(BigInteger.ONE) { acc, num ->
            acc * BigInteger.valueOf(num.toLong())
        }
    }
    var res = 0
    while (factorial.mod(BigInteger.TEN) == BigInteger.ZERO) {
        ++res
        factorial = factorial.divide(BigInteger.TEN)
    }
    return res
}

fun trailingZeroesInFactorialEfficient(number: Int): Int {
    var res = 0
    var i = 5
    while (i <= number) {
        res += number / i
        i *= 5
    }
    return res
}

fun gdcEuclidModulo(a: Int, b: Int): Int {
    if (b == 0) return a
    return gdcEuclidModulo(b, a % b)
}

fun gdcEuclidTraditional(a: Int, b: Int): Int {
    var left = a
    var right = b
    while (left != right) {
        if (left > right) {
            left -= right
        } else {
            right -= left
        }
    }
    return left
}



















