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
fun isPalindromeRecursive(line: String) : Boolean {
    return isPalindromeRecursive(line, 0, line.length - 1)
}

private fun isPalindromeRecursive(line: String, start: Int, endInclusive: Int) : Boolean {
    if (start >= endInclusive) {
        return true
    }
    return (line[start] == line[endInclusive] && isPalindromeRecursive(line, start + 1, endInclusive - 1))
}























