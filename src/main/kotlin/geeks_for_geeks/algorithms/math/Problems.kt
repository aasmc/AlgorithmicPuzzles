package geeks_for_geeks.algorithms.math

import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

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

/**
 * In this method we count the number of 5 inside the range of numbers. If, e.g. a number consists of
 * many 5s, then we count them all. E.g., 25 consists of 5x5. It allows us to count the number of trailing zeroes,
 * because we know that only 5 x 2 gives a zero at the end.
 */
fun trailingZeroesInFactorialEfficient(number: Int): Int {
    var res = 0
    var i = 5
    while (i <= number) {
        res += number / i
        i *= 5
    }
    return res
}

fun gcdEuclidModulo(a: Int, b: Int): Int {
    if (b == 0) return a
    return gcdEuclidModulo(b, a % b)
}

fun gcdEuclidTraditional(a: Int, b: Int): Int {
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

fun lowestCommonMultipleNaive(a: Int, b: Int): Int {
    var result = maxOf(a, b);
    while (true) {
        if (result % a == 0 && result % b == 0) {
            return result
        }
        ++result;
    }
}

/**
 * Lowest common multiple of two numbers a and b is computed using the formula:
 *
 * lcm = (a * b) / gcd(a, b)
 *
 * where gcd is the greatest common divisor of the two numbers.
 *
 * This formula is derived from the fact that
 * a * b = lcm(a, b) * gcd(a, b)
 */
fun lowestCommonMultipleEfficient(a: Int, b: Int): Int {
    return (a * b) / gcdEuclidModulo(a, b)
}

fun isPrimeUsingSqrt(number: Int): Boolean {
    if (number == 1) {
        return false
    }
    if (number == 2) {
        return true
    }
    val sqrtCeiling = ceil(sqrt(number.toDouble())).toInt()
    return (2..sqrtCeiling)
        .none { divisor ->
            number % divisor == 0
        }
}

/**
 * Efficient solution based on the fact that we first need to check if a number is
 * divisible by 2 and 3 (two of the most common divisors). This excludes many checks from
 * the iterative approach.
 *
 * After that we loop over numbers starting from 5 until sqrt(number) with a step of 6 and check
 * if number is divisible by i or i + 2. By adding i + 2 we ensure that we can skip 6 numbers on iteration.
 * E.g. we already checked if number is divisible by 2, 3 and 5(on first iteration). 5 + 6 =11,
 * so we can cross out 6, 8, 9, 10. Only 7 remains. to account for 7, we add i + 2.
 */
fun isPrimeUsingLoopEfficient(number: Int): Boolean {
    if (number == 1) {
        return false
    }
    if (number == 2 || number == 3) {
        return true
    }
    if (number % 2 == 0 || number % 3 == 0) {
        return false
    }
    var i = 5
    while (i * i < number) {
        if (number % i == 0 || number % (i + 2) == 0) {
            return false
        }
        i += 6
    }
    return true
}

/**
 * Time complexity is O(n^2logn)
 */
fun primeFactorsNaive(number: Int): List<Int> {
    val res = mutableListOf<Int>()
    for (i in 2 until number) {
        if (isPrimeUsingLoopEfficient(i)) {
            var x = i
            while (number % x == 0) {
                res.add(i)
                x *= i
            }
        }
    }
    return res
}


fun primeFactorsEfficient(number: Int): List<Int> {
    val res = mutableListOf<Int>()
    var num = number

    while (num % 2 == 0) {
        res.add(2)
        num /= 2
    }

    while (num % 3 == 0) {
        res.add(3)
        num /= 3
    }

    var i = 5
    while (i * i <= num) {
        while (num % i == 0) {
            res.add(i)
            num /= i
        }
        while (num % (i + 2) == 0) {
            res.add(i)
            num /= (i + 2)
        }
        i += 6
    }
    if (num > 1) {
        res.add(num)
    }
    return res
}














