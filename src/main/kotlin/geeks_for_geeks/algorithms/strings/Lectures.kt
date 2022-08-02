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