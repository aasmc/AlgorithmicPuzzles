package leetcode.ace_coding.strings.gcd

fun gcdOfStringsBruteForce(str1: String, str2: String): String {
    var minPrefixLength = minOf(str1.length, str2.length)
    while (minPrefixLength > 0) {
        if (isGcd(str1, str2, minPrefixLength)) {
            return str1.substring(0, minPrefixLength)
        }
        --minPrefixLength
    }
    return ""
}

/**
 * Checks if [left] and [right] strings can be equally divided
 * by the prefix with size = [prefixSize].
 *
 * Step 1. Check if lengths of both strings can be divided without remainder
 * by the prefixSize.
 *
 * Step 2. Check if both strings are made up of multiples of prefix.
 */
private fun isGcd(left: String, right: String, prefixSize: Int): Boolean {
    if (left.length % prefixSize > 0 || right.length % prefixSize > 0) {
        return false
    }
    val base = left.substring(0, prefixSize)
    return left.replace(base, "").isEmpty() && right.replace(base, "").isEmpty()
}