package geeks_for_geeks.algorithms.recursion

fun logBaseTwoFloorRecursive(num: Int): Int {
    if (num == 1) {
        return 0
    }
    return 1 + logBaseTwoFloorRecursive(num / 2)
}

/**
 * Returns the sum of [ceil] natural numbers using
 * recursion.
 */
fun sumOfNRecursive(ceil: Int): Int {
    if (ceil == 0) {
        return 0
    }
    return ceil + sumOfNRecursive(ceil - 1)
}

/**
 * Checks if a given String [line] is a palindrome or not,
 * using recursive method.
 */
fun isPalindromeRecursive(line: String): Boolean {
    return isPalindromeRecursive(line, 0, line.length - 1)
}

private fun isPalindromeRecursive(line: String, start: Int, endInclusive: Int): Boolean {
    if (start >= endInclusive) {
        return true
    }
    return (line[start] == line[endInclusive] && isPalindromeRecursive(line, start + 1, endInclusive - 1))
}

fun sumOfDigitsRecursive(num: Int): Int {
    return sumOfDigitsRecursive(num, 0)
}

private tailrec fun sumOfDigitsRecursive(num: Int, sum: Int): Int {
    if (num == 0) {
        return sum
    }
    return sumOfDigitsRecursive(num / 10, sum + (num % 10))
}

fun sumOfDigitsNoTailrec(num: Int): Int {
    if (num <= 9) {
        return num
    }
    return sumOfDigitsNoTailrec(num / 10) + num % 10
}

/**
 * Given a rope of length [n], the task is to cut the rope into max number of pieces,
 * such that every piece is of length either [a], or [b], or [c].
 *
 * Time Complexity O(3 ^ N)
 */
fun ropeCutting(n: Int, a: Int, b: Int, c: Int): Int {
    if (n == 0) {
        return 0
    }
    if (n < 0) {
        return -1
    }
    val res = maxOf(
        ropeCutting(n - a, a, b, c),
        ropeCutting(n - b, a, b, c),
        ropeCutting(n - c, a, b, c),
    )

    if (res == -1) {
        return -1
    }
    return res + 1
}

/**
 * Given a string of length N, the task is to generate all subsets of the string.
 * All characters in the string are distinct.
 *
 * The solution is based on the following idea:
 *  If we have subsets of string of length n - 1, we
 *  can generate subsets of string length n.
 *
 *  Example:
 *        ""
 *      /
 *   "" - "C"
 *       "A"
 *      /
 *  "A" - "AC"
 *
 *       "B"
 *      /
 *  "B" - "BC"
 *
 *       "AB"
 *      /
 *  "AB" - "ABC"
 *
 */
fun generateSubsetsRecursive(input: String): Set<String> {
    val result = mutableSetOf<String>()
    generateSubsetsRecursive(input, "", result, 0)
    return result
}

/**
 * Example. input = abc
 * Recursion Tree for the method is as follows:
 *                                 ""  -  current, index = 0
 *                               /    \
 *                            ""        "a" - current, index = 1
 *                          /   \      /  \
 *                       ""    "b"  "a"    "ab" - current, index = 2
 *                      / \   / \   /  \   /   \
 *                    "" c  b  cb a  ac   ab   abc - current, index = 3 == input.length
 */
private fun generateSubsetsRecursive(input: String, current: String, result: MutableSet<String>, index: Int) {
    if (index == input.length) {
        result.add(current)
        return
    }
    generateSubsetsRecursive(input, current, result, index + 1)
    generateSubsetsRecursive(input, current + input[index], result, index + 1)
}



















