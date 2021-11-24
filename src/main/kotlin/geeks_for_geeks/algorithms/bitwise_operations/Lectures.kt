package geeks_for_geeks.algorithms.bitwise_operations

/**
 * Sets the i-th bit in the [set] to 1.
 *
 * Note that the position of the first bit is 0.
 *
 * Step 1: move 1 to the i-th position by using (1 shl i)
 * Step 2: OR the set with the result.
 */
fun setBit(set: Int, i: Int): Int {
    return set or (1 shl i)
}

/**
 * Checks if the i-th bit in the [set] is set to 1.
 */
fun isSet(set: Int, i: Int): Boolean {
    return (set and (1 shl i)) != 0
}

/**
 * Clears the i-th bit in the [set].
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
    return set and (1 shl i).inv()
}

/**
 * Toggles the i-th bit in the [set] from 0 to 1 or from 1 to 0.
 *
 * Step 1: move 1 to the i-th position.
 * Step 2: XOR the result with the [set].
 */
fun toggleBit(set: Int, i: Int): Int {
    return set xor (1 shl i)
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
 * another number that will consist of all 1s in the positions before
 * the initial number.
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

















