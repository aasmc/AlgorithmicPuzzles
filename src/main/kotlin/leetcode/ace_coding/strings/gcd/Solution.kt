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

fun gcdOfStrings(str1: String, str2: String): String {
    // if strings have a common divisor, it means that both of the strings are
    // a concatenation of that same divisor. And that also means, that if we
    // concatenate both of the strings in any order, they will form the same
    // string
    if ("$str1$str2" != "$str2$str1") return ""
    val gcdLength = gcd(str1.length, str2.length)
    return str1.substring(0, gcdLength)
}

private tailrec fun gcd(x: Int, y: Int): Int {
    if (y == 0) return x
    return gcd(y, x % y)
}