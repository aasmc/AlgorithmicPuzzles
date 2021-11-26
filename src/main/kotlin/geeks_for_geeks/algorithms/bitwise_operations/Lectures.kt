package geeks_for_geeks.algorithms.bitwise_operations

import kotlin.math.pow

/**
 * Sets the i-th bit in the [set] to 1.
 *
 * Note that the position of the first bit is 0.
 *
 * Step 1: move 1 to the i-th position by using (1 shl i)
 * Step 2: OR the set with the result.
 */
fun setBit(set: Int, i: Int): Int {
    return set or (1 shl (i - 1))
}

/**
 * Checks if the i-th bit in the [set] is set to 1.
 *
 * Note that the position of the first bit is 0.
 */
fun isSetByShl(set: Int, i: Int): Boolean {
    val shifted = (1 shl (i - 1))
    return (set and shifted) != 0
}

fun isSetByShr(set: Int, i: Int): Boolean {
    return ((set shr (i - 1)) and 1) == 1
}

/**
 * Clears the i-th bit in the [set].
 * Note that the position of the first bit is 0.
 *
 * Step 1: move 1 to the i-th position.
 * Step 2: invert the result.
 * Step 3: AND the result with [set]
 *
 * E.g. [set] 01101101, [i] = 4 *
 * Step 1:    00001000
 * Step 2:    11110111
 *        AND
 * Step 3:    01101101
 *            01100101
 */
fun clearBit(set: Int, i: Int): Int {
    return set and (1 shl (i - 1)).inv()
}

/**
 * Toggles the i-th bit in the [set] from 0 to 1 or from 1 to 0.
 * Note that the position of the first bit is 0.
 *
 * Step 1: move 1 to the i-th position.
 * Step 2: XOR the result with the [set].
 */
fun toggleBit(set: Int, i: Int): Int {
    return set xor (1 shl (i - 1))
}

/**
 * Returns a number with the first [n] bits set to 1.
 */
fun setAll(n: Int): Int {
    return (1 shl n) - 1
}

/**
 * Verifies that the number is the power of 2.
 *
 * Based on the fact that if we subtract 1 from a number raised to
 * the power of two, then in the binary representation we will get
 * another number that will have all 0 bits set to 1 in the positions before
 * the last bit set to 1 in the initial number and that last bit that was set to
 * 1 in the initial number will be set to 0.
 *
 * Also, any power of two has only 1 bit set to 1.
 *
 * 0b1000 - 1 = 0b0111
 *
 * Step 1: Check if the number is greater than 0.
 * Step 2: check if [n] AND [n - 1] are equal to 0.
 *
 * E.g. n = 8 = 0b1000
 *          AND
 *  n - 1 = 7 = 0b0111
 *    result:   0b0000
 */
fun isPowerOfTwo(n: Int): Boolean {
    return n > 0 && (n and (n - 1)) == 0
}

/**
 * Counts the number of set bits in a non-negative integer.
 * Time complexity O(total number of bits)
 */
fun countSetBitsNaive(set: Int): Int {
    var s = set
    var count = 0
    while (s > 0) {
        count += (s and 1)
        s = s shr 1
    }
    return count
}

/**
 * Counts the number of set bits in a non-negative integer
 * using Brian Kerningam's algorithm.
 *
 * Time complexity O(number of set bits)
 */
fun countSetBitsKerningam(set: Int): Int {
    var s = set
    var count = 0
    while (s > 0) {
        s = s and (s - 1)
        ++count
    }
    return count
}

/**
 * Stores the number of set bits any 8-bit number (range from 0 to 255) has.
 *
 * 0 has 0 set bits
 * 1 has 1 set bit
 * 2 has 1 set bit
 * 3 has 2 set bits
 * 4 has 1 set bit
 * 5 has 2 set bits
 * and so on...
 */
val table: IntArray = IntArray(256)

fun initializeTable() {
    table[0] = 0
    for (i in 1 until 256) {
        table[i] = (i and 1) + table[i / 2]
    }
}

/**
 * Counts the number of set bits in a non-negative integer
 * using lookup table algorithm.
 *
 * But it requires preprocessing of the table.
 *
 * Time complexity O(1)
 */
fun countSetBitsLookupTable(set: Int): Int {
    var s = set
    // s AND 0xff will get us the last 8 bits from s.
    // we look up the number of set bits among those 8 bits in the table that has been prepared beforehand.
    var res = table[s and 0xff]
    // shift s by the 8 bits that we have already checked.
    s = s shr 8
    // repeat the procedure four times since we store integers as 32 bit numbers.
    res += table[s and 0xff]
    s = s shr 8
    res += table[s and 0xff]
    s = s shr 8
    res += table[s and 0xff]
    s = s shr 8
    return res
}

/**
 * Given an array of numbers, find the only one number that appears odd number
 * of times in the array.
 *
 * Based on some properties of XOR operator.
 *
 * x XOR x = 0
 * x XOR 0 = x
 * x XOR y = y XOR x
 * x XOR (y XOR z) = (x XOR y) XOR z
 *
 * Example: input = [4,3,4,5,5]
 *
 * res = 0b100 XOR 0 = 0b100
 * res = 0b100 XOR 0b011 = 0b111
 * res = 0b111 XOR 0b100 = 0b011
 * res = 0b011 XOR 0b101 = 0b110
 * res = 0b110 XOR 0b101 = 0b011 = 3
 *
 * The idea is that by XORing all elements of the array with each other, and if the array contains
 * only one element that appears odd number of times, then the elements that appear even number of times
 * will cancel each other out.
 */
fun oneOddOccurring(set: IntArray): Int {
    var res = 0
    for (i in set.indices) {
        res = res xor set[i]
    }
    return res
}

/**
 * Given an array of n numbers that has values in range [1..n+1]. Every number
 * appears exactly once. One number from that range is missing in the array.
 * Find the number.
 *
 * Example: n = 3
 *          range = 1..4
 *          arr = [1,4,3]
 *          missing = 2
 */
fun findOneMissingNumberInArray(arr: IntArray): Int {
    var res = 0
    arr.forEach {
        res = res xor it
    }
    var res2 = 0
    (1..arr.size + 1).forEach {
        res2 = res2 xor it
    }
    return res xor res2
}

/**
 * Given an array of integers that contains all numbers appearing even number of times,
 * except two numbers that appear odd number of times, find those two numbers.
 *
 * Example:
 *          input = [3,4,3,4,5,4,4,6,7,7]
 *          odd occurrences: 5, 6
 * Constraints:
 *  size of the array is at least 2.
 *
 *  Solution is based on the XOR properties.
 *
 *  Step 1. Perform XOR on all elements of the array. This way we cancel out the numbers that
 *          occur even number of times. As a result we get the XOR of our two odd occurrences.
 *          In the example above, we get 5 XOR 6.
 * Step 2. Since XOR returns 1 only when one of the bits we are comparing is set to 1, then we
 *         can divide the array into two groups.
 *         Group 1 will contain the last bit set to 1 in XOR as set.
 *         Group 2 will contain the last bit set to 1 in XOR as not set.
 * Step 3. Perform XOR of all the numbers in group 1 and group 2. This is our result.
 *
 * Example: [3,4,3,4,5,4,4,6,7,7]
 *
 * Step 1. res = 0b110 XOR 0b101 = 0b011
 * Step 2. Group 1. [3,3,5,7,7]
 *         Group 2. [4,4,4,4,6]
 * Step 3. output1 = 5
 *         output2 = 6
 */
fun findTwoOddOccurrencesInArray(arr: IntArray): IntArray {
    var res0 = 0
    var res1 = 0
    var res2 = 0
    arr.forEach {
        res0 = res0 xor it
    }
    val lastSetBit = getLastSetBit(res0)
    arr.forEach { num ->
        if ((num and lastSetBit) != 0) {
            res1 = res1 xor num
        } else {
            res2 = res2 xor num
        }
    }
    return intArrayOf(res1, res2)
}

/**
 * Returns the number that in binary representation contains only 1 bit set to 1,
 * and that bit corresponds to the last set bit in the initial number [num].
 *
 * Step 1. num - 1. This step clears the last set bit in the [num] and
 * sets all the following bits to 1.
 *        E.g. num = 0b101000
 *         num - 1 = 0b100111
 * Step 2. Invert the (num - 1). This way we receive
 *          0b100111
 *          0b011000
 *
 * Step 3. num AND (num-1).inv()
 *         0b101000
 *         AND
 *         0b011000
 *         0b001000
 */
fun getLastSetBit(num: Int): Int {
    return num and (num - 1).inv()
}

/**
 * Given a string of distinct characters, generate all possible subsets of the
 * characters.
 *
 * E.g. input "abc"
 *     output "", "a", "b", "c", "ab", "ac", "bc", "abc"
 *
 * In general, if we have n characters in the string, then
 * there're 2^n subsets.
 *
 * Solution. Create a counter that will contain all possible positions of the chars in the subsets
 * represented in the binary form.
 *   For our input = "abc" counter = 7. Which is 2^3 -1
 *   in Binary it is:
 *   000  ->  ""
 *   001  ->  "a"
 *   010  ->  "b"
 *   011  ->  "ab"
 *   100  ->  "c"
 *   101  ->  "ca"
 *   110  ->  "cb"
 *   111  ->  "abc"
 *
 *   For every value of the counter we traverse through the string and check if the
 *   corresponding bit is set or not.
 *
 *   Time complexity is O(2^N * N)
 */
fun generatePowerSet(str: String): List<String> {
    val n = str.length
    val powerSize = 2.0.pow(n.toDouble()).toInt()
    val result = mutableListOf<String>()
    val sb = StringBuilder()
    for (counter in 0 until powerSize) {
        // for every char in the string
        for (j in 0 until n) {
            // if the corresponding bit is set in the counter
            if (counter and (1 shl j) != 0) {
                // we append the char to the builder
                sb.append(str[j])
            }
        }
        result.add(sb.toString())
        sb.clear()
    }
    return result
}

/**
 * Finds the log base 2 of a 32-bit Integer.
 */
fun logBase2(num: Int): Int {
    var res = 0
    var n = num
    while (n > 0) {
        n = n shr 1
        ++res
    }
    return res
}

fun findMSB(num: Int): Int {
    var pos = 0
    var n = num
    while (n > 0) {
        n = n shr 1
        ++pos
    }
    return (1 shl (pos - 1))
}

/**
 * Given an array arr[] of N positive elements.
 * The task is to find the Maximum AND Value generated by
 * any pair of the element from the array.
 *
 * Example: Input: a[] = {4, 8, 12, 16}
 *          Output: 8
 *          The pairs 8 and 12 gives us the '&' value as 12.
 */
fun findMaximumANDInArray(arr: IntArray): Int {
    var res = 0
    // iterate over all the bits starting from 31 down to 0
    (31 downTo 0).forEach { bit ->
        // count the elements in the array that have the MSB set to 1
        val count = checkBit(res or (1 shl bit), arr)
        if (count >= 2) {
            res = res or (1 shl bit)
        }
    }
    return res
}

/**
 * Counts the number of elements in the [arr] that have MSB corresponding to the pattern.
 */
private fun checkBit(pattern: Int, arr: IntArray): Int {
    var count = 0
    arr.forEach { element ->
        if ((pattern and element) == pattern) {
            ++count
        }
    }
    return count
}

fun positionOfRightmostDifferentBit(m: Int, n: Int): Int {
    if (m == n) {
        return -1;
    }
    val xored = m xor n
    var position = xored and (xored - 1).inv()
    var counter = 0
    while (position > 0) {
        ++counter
        position = position shr 1
    }
    return counter
}

/**
 * You are given a number N. Find the total count of set bits for all numbers
 * from 1 to N(both inclusive).
 *
 * Example:
 *
 * Input: N = 4
 * Output: 5
 * Explanation:
 * For numbers from 1 to 4.
 * For 1: 0 0 1 = 1 set bits
 * For 2: 0 1 0 = 1 set bits
 * For 3: 0 1 1 = 2 set bits
 * For 4: 1 0 0 = 1 set bits
 * Therefore, the total set bits is 5.
 */
fun countSetBitsInAllNumbersUntil(n: Int): Int {
    var two = 2
    var ans = 0
    var nn = n
    while (nn != 0) {
        ans += (n / two) * (two shr 1)
        if ((n and (two - 1)) > (two shr 1) - 1) {
            ans += (n and (two - 1)) - (two shr 1) + 1
        }
        two = two shl 1
        nn = nn shr 1
    }
    return ans
}

/**
 * You are given two numbers A and B.
 * The task is to count the number of bits needed to be flipped to convert A to B.
 *
 * Example:
 *
 *   Input: A = 10, B = 20
 *   Output: 4
 *   Explanation:
 *   A  = 01010
 *   B  = 10100
 *   As we can see, the bits of A that need
 *   to be flipped are 01010. If we flip
 *   these bits, we get 10100, which is B.
 */
fun countBitToFlip(a: Int, b: Int): Int {
    if (a == b) {
        return 0
    }
    var xored = a xor b
    var count = 0
    while (xored > 0) {
        xored = xored and (xored - 1)
        ++count
    }
    return count
}

/**
 * Given a number N. The task is to check whether it is sparse or not. A number is said to
 * be a sparse number if no two or more consecutive bits are set in the binary representation.
 *
 * Example 1:
 * Input: N = 2
 * Output: 1
 * Explanation: Binary Representation of 2 is 10,
 * which is not having consecutive set bits.
 * So, it is sparse number.
 *
 * Example 2:
 * Input: N = 3
 * Output: 0
 * Explanation: Binary Representation of 3 is 11,
 * which is having consecutive set bits in it.
 * So, it is not a sparse number.
 *
 * Expected Time Complexity: O(log N).
 * Expected Auxiliary Space: O(1).
 */
fun isSparse(n: Int): Boolean {
    var counter = 0
    var nn = n
    while (nn > 0) {
        if ((nn and 1) == 1) {
            ++counter
            if (counter > 1) {
                return false
            }
        } else {
            counter = 0
        }
        nn = nn shr 1
    }
    return true
}

/**
 * Given a number N. Find the length of the longest consecutive
 * 1s in its binary representation.
 *
 * Example1:
 * Input: N = 14
 * Output: 3
 * Explanation:
 * Binary representation of 14 is
 * 1110, in which 111 is the longest
 * consecutive set bits of length is 3.
 *
 * Expected Time Complexity: O(log N).
 * Expected Auxiliary Space: O(1).
 *
 * Constraints:
 * 1 <= N <= 10^6
 */
fun findMaxConsecutiveOnes(num: Int) : Int {
    var max = 0
    var counter = 0
    var n = num
    while (n > 0) {
        if (n and 1 > 0) {
            ++counter
        } else {
            if (max < counter) {
                max = counter
            }
            counter = 0
        }
        n = n shr 1
    }
    // ensure correct result if number consists only of 1s
    if (max < counter) {
        max = counter
    }
    return max
}

























