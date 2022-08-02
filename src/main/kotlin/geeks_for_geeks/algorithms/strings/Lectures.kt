package geeks_for_geeks.algorithms.strings

fun main() {
    stringToFrequencySorted("geeksforgeeks")
}

/**
 * Given a lowercase string, print all its characters in sorted order
 * and their frequencies as well.
 * Time Complexity O(N)
 */
fun stringToFrequencySorted(str: String) {
    val count = IntArray(26) { 0 }
    str.forEach { char ->
        count[char - 'a']++
    }
    for (i in 0 until 26) {
        if (count[i] > 0) {
            println("Char: ${(i + 'a'.code).toChar()}. " +
                    "ASCII value of char: ${i + 'a'.code}. Frequency: ${count[i]}")
        }
    }
}

/**
 * Checks if the string is a palindrome.
 * Time Complexity O(N)
 */
fun String.isPalindrome(): Boolean {
    for (i in 0 until length / 2) {
        if (this[i] != this[length - i - 1]) {
            return false
        }
    }
    return true
}

/**
 * Checks if a given [String] is a subsequence of [other] [String].
 * A subsequence means that all chars in this [String] are present in [other]
 * [String] and are in the same order.
 */
infix fun String.isSubsequenceOf(other: String): Boolean {
    if (this.length > other.length) return false
    var i = 0
    var j = 0
    while (i < this.length && j < other.length) {
        if (this[i] == other[j]) {
            ++i
            ++j
        } else {
            ++j
        }
    }
    return i == this.length
}

fun isSubsequenceRecursive(current: String, other: String) : Boolean {
    if (current.length > other.length) return false
    return isSubsequenceRecursiveHelper(current, other, current.length, other.length)
}

/**
 * It is common practise to use length of a [String] as an index parameter
 * in recursive algorithms.
 */
private fun isSubsequenceRecursiveHelper(
    current: String,
    other: String,
    currentLength: Int,
    otherLength: Int
) : Boolean {
    if (currentLength == 0) return true
    if (otherLength == 0) return false
    return if (current[currentLength - 1] == other[otherLength - 1]) {
        isSubsequenceRecursiveHelper(current, other, currentLength - 1, otherLength - 1)
    } else {
        isSubsequenceRecursiveHelper(current, other, currentLength, otherLength - 1)
    }
}
