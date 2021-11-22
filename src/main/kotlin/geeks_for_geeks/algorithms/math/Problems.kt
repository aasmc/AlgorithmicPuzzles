package geeks_for_geeks.algorithms.math

import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
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

    // since we know that 2 and 3 are prime numbers we can check if the number is divisible by 2 and 3 beforehand.
    // it will dramatically decrease the number of iterations needed to find out the rest of the factors.
    while (num % 2 == 0) {
        res.add(2)
        num /= 2
    }

    while (num % 3 == 0) {
        res.add(3)
        num /= 3
    }
    // we have crossed all possible divisors (factors) of 2 and 3, (4, 6, 8, 10 are among them), so we start from 5
    // since we crossed divisors of 3 as well (9, 15 are among them) we can iterate using i and i + 2, because
    // we have 7, 11, 17, 19 and so on divisors (factors) left. Therefore, we use step 6.
    var i = 5
    while (i * i <= num) {
        // while the number is divisible by 5 then 11 them 17 etc. we keep on adding them to the result list
        while (num % i == 0) {
            res.add(i)
            num /= i
        }
        // while number is divisible by 7, 13, 19 etc. we keep on adding them to the result list
        while (num % (i + 2) == 0) {
            res.add(i)
            num /= (i + 2)
        }
        i += 6
    }
    // if the remaining part of the number is greater than 1, them we know that is the last remaining prime factor
    // so, we add it to the list as well.
    if (num > 1) {
        res.add(num)
    }
    return res
}

/**
 * Finds divisors of a number in a sorted order.
 */
fun divisorsOfNumberNaive(number: Int): List<Int> {
    val result = mutableListOf<Int>()
    for (i in 1..number) {
        if (number % i == 0) {
            result.add(i)
        }
    }
    return result
}

/**
 * Finds divisors of a number in a sorted order.
 */
fun divisorsOfNumberEfficient(number: Int): List<Int> {
    val result = mutableListOf<Int>()

    var i = 1
    while (i * i < number) {
        if (number % i == 0) {
            // add the smaller of the two divisors (remember that if a number is divisible by another
            // number, than it has another divisor. All divisors come in pairs. E.g. 30 is divisible by (2, 15),
            // this is the pair of divisors, and among them there's always one that is less than or equal to the other)
            // here we add the smaller one
            result.add(i)
        }
        ++i
    }
    while (i >= 1) {
        if (number % i == 0) {
            // here we add the greater of the divisors.
            result.add(number / i)
        }
        --i
    }
    return result
}

fun nextPrime(num: Int): Int {
    return generateSequence(num + 1) { i ->
        i + 1
    }.first {
        isPrimeUsingLoopEfficient(it)
    }
}

fun primesLessThanOrEqualTo(max: Int): List<Int> {
    return generateSequence(2, ::nextPrime)
        .takeWhile { it <= max }
        .toList()
}

/**
 * Time complexity of the algorithm is O(n*log*logn).
 */
fun sieveOfEratosthenes(max: Int): List<Int> {
    // initially we mark all numbers as prime
    val sieve = BooleanArray(max + 1) { true }
    val result = mutableListOf<Int>()
    // skip 0 and 1
    var i = 2
    // traverse numbers one by one
    while (i <= max) {
        // if it is marked as prime, then it has been previously traversed and checked
        if (sieve[i]) {
            // so add it to the result
            result.add(i)
            // start with i * i, because we know that we have already previously traversed all smaller numbers
            // e.g. i = 2, j = 4 (first iteration, we skip 3, but it is a prime number)
            // second iteration j = 4 + 2 = 6
            // third iteration j = 6 + 2 = 8 ... j = 10, j = 12, j = 14
            // then i = 3, j = 9, we have already traversed all previous multiples of 2, and only 7 is left - it is a prime
            // j = 12, j = 16
            // then i = 4, j = 16, so this is a pattern
            // here's a math explanation. Composite number smaller than i can be represented as:
            // i * (i - 1) or i * (i - 2) etc.
            // so it has a smaller divisor than i. And these smaller divisors are already considered in earlier iterations.
            var j = i * i
            while (j <= max) {
                sieve[j] = false
                j += i
            }
        }
        ++i
    }
    return result
}

fun powerNaive(number: Int, p: Int): Long {
    var res = 1L
    for (i in 1..p) {
        res *= number
    }
    return res
}

/**
 * The solution is based on the fact that a number^p can be represented as
 * number^(p/2) * number^(p/2) if p is equal, and if p is not equal
 * it can be represented as number^(p-1/2) * number^(p-1/2) * number
 *
 * Time complexity is O(logN)
 * Space complexity is O(logN) + function call overhead
 */
fun powerRecursionEffective(number: Int, p: Int): Long {
    if (p == 0) {
        return 1L
    }
    var tmp = powerRecursionEffective(number, p / 2)
    tmp *= tmp
    return if (p % 2 == 0) {
        tmp
    } else {
        tmp * number
    }
}

/**
 * Solution is based on the fact that any decimal number can be represented as the sum of
 * 2-s raised to some power. E.g. 10 = 2^3 + 2^1 = 0b1010.
 *
 * So, the problem is that we need to raise [number] to the power of [p].
 * E.g. 3^10. It can be represented as 3^8 * 3^2. We decomposed [p] which initially was
 * 10 into 8 and 2. We then need to traverse bits of [p] from LSB to MSB and consider each bit as
 * a multiplier of [number] raised to the bit position.
 *
 * E.g. 3^ 10 = 3^8 * 3^2
 *                          10 = 0b 1   0   1   0
 *                                 3^8 3^4 3^2 3^1
 *
 * When we see a 0 bit, we ignore the result, when we see a 1 bit we multiply the result by the number, BUT
 * we need to raise the number to the power of 2 on every iteration of bit traversal.
 *
 * We use the binary representation of the [p]
 * Time complexity is O(logN)
 * Space complexity is O(1)
 */
fun powerIterative(number: Int, p: Int): Long {
    var res = 1L
    var num = number
    var pp = p
    // traverse the power bit by bit from LSB to MSB
    while (pp > 0) {
        // if LSB is 1 (pp is not divisible by 2)
        if (pp and 1 != 0) { // a bitwise trick to check if a number is odd or not
            // then we multiply the result by the number
            res *= num
        }
        // on every iteration we multiply number by the number
        num *= num
        // divide the power by two to get the next LSB
        // use bit shift operation for optimisation
        pp = pp shr 1
    }
    return res
}

fun quadraticEquasionRoots(a: Int, b: Int, c: Int): List<Int> {
    val discriminant = b * b * 1.0 - 4 * a * c * 1.0
    if (discriminant < 0) {
        return mutableListOf(-1)
    }

    val root1 = floor((-b + sqrt(discriminant)) / (2 * a))
    val root2 = floor((-b - sqrt(discriminant)) / (2 * a))
    return listOf(root1.toInt(), root2.toInt())
}












































