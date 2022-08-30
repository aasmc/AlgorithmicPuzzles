package geeks_for_geeks.algorithms.strings

import java.lang.StringBuilder

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
            println(
                "Char: ${(i + 'a'.code).toChar()}. " +
                        "ASCII value of char: ${i + 'a'.code}. Frequency: ${count[i]}"
            )
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
 *
 * This algorithm assumes, that [this] [String]'s length is smaller that the other's.
 *
 * Step 1. assign pointers to the beginning of the strings.
 * Step 2. traverse both strings until either of the pointers goes to the end of the string.
 *      - if chars at current indices are the same, then move both pointers ahead one step.
 *      - otherwise just move the pointer in the [other] string.
 * Step 3. check if [this] [String]'s pointer moved till the end, if so, then we know that
 *         chars from this string are present in the other string, then we return [true],
 *         otherwise we know that some char from this string is not present in the other
 *         string, so we return [false].
 *
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

fun isSubsequenceRecursive(current: String, other: String): Boolean {
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
): Boolean {
    if (currentLength == 0) return true
    if (otherLength == 0) return false
    return if (current[currentLength - 1] == other[otherLength - 1]) {
        isSubsequenceRecursiveHelper(current, other, currentLength - 1, otherLength - 1)
    } else {
        isSubsequenceRecursiveHelper(current, other, currentLength, otherLength - 1)
    }
}

/**
 * Checks if [this] [String] and [other] [String] are anagrams, i.e. they contain
 * the same characters and frequencies of characters are the same also.
 *
 * Naive implementation requires sorting a [this] [String] and then comparing it to the [other] [String].
 *
 * A better approach is to use array of integers to count characters. We increment counts
 * for current string, and decrement counts for the other string. This way we know that
 * the strings are anagrams, if the array eventually contains all zeroes.
 *
 * To determine the size of the count array, we find min and max char codes from both strings,
 * if the strings are anagrams, then the min and max values are the same.
 */
fun String.isAnagramWith(other: String): Boolean {
    if (this.length != other.length) return false
    val (minThis, maxThis) = minMaxCharValue()
    val (minOther, maxOther) = other.minMaxCharValue()
    if (minThis != minOther || maxThis != maxOther) return false

    val size = maxThis - minThis + 1
    val count = IntArray(size) { 0 }
    for (i in 0 until length) {
        val currentThisCode = this[i].code
        count[currentThisCode - minThis]++
        val currentOtherCode = other[i].code
        count[currentOtherCode - minOther]--
    }
    return count.all { it == 0 }
}

private fun String.minMaxCharValue(): Pair<Int, Int> {
    if (isEmpty()) throw RuntimeException("String is empty, you cannot find min and max char values in an empty String.")
    val minCode = minOf { it }.code
    val maxCode = maxOf { it }.code
    return minCode to maxCode
}

/**
 * Return the index of the leftmost character that is repeating in the string. If there's no
 * repeating character, it returns -1.
 */
fun String.leftMostRepeatingCharacterIndex(): Int {
    val (minCode, maxCode) = minMaxCharValue()
    val count = IntArray(maxCode - minCode + 1) { 0 }
    for (char in this) {
        count[char.code - minCode]++
    }
    for ((index, char) in this.withIndex()) {
        if (count[char.code - minCode] > 1) return index
    }
    return -1
}

/**
 * Improve the algorithm to find the index in a single pass (except for finding min and max codes).
 * We store indices of the first occurrence of characters in the array. Initially the array elements are
 * all set to -1.
 * At first we initialize the result to Int.MAX_VALUE.
 * Traverse the string, if the current char's index is -1, then it is the first occurrence, so we store
 * the index of the current char in the array. Otherwise, it is not the first occurrence, so we
 * compare the result with the value stored in the array, and set the result to the minimum of the two.
 * In the end, we compare the result to Int.MAX_VALUE and return -1 if true, or result otherwise.
 */
fun String.leftMostRepeatingCharacterIndexImproved(): Int {
    val (minCode, maxCode) = minMaxCharValue()
    val firstIndexStore = IntArray(maxCode - minCode + 1) { -1 }
    var res = Int.MAX_VALUE
    for ((index, char) in this.withIndex()) {
        val firstIndex = firstIndexStore[char.code - minCode]
        if (firstIndex == -1) {
            firstIndexStore[char.code - minCode] = index
        } else {
            res = kotlin.math.min(res, firstIndex)
        }
    }
    return if (res == Int.MAX_VALUE) {
        -1
    } else {
        res
    }
}

/**
 * In this version we traverse the string from right to left and at the same time
 * keep track if we previously visited the char from the string. if yes, then we update
 * the result to the current index, otherwise we mark the char as visited.
 */
fun String.leftMostCharacterRepeatingImprovedVersionTwo(): Int {
    val (minCode, maxCode) = minMaxCharValue()
    val visited = BooleanArray(maxCode - minCode + 1) { false }
    var res = -1
    for (i in length - 1 downTo 0) {
        val currentIdx = this[i].code - minCode
        if (visited[currentIdx]) {
            res = i
        } else {
            visited[currentIdx] = true
        }
    }
    return res
}

fun String.indexOfLeftmostNonRepeatingChar(): Int {
    val (minCode, maxCode) = minMaxCharValue()
    val count = IntArray(maxCode - minCode + 1)
    for (char in this) {
        count[char.code - minCode]++
    }
    for ((index, char) in this.withIndex()) {
        if (count[char.code - minCode] == 1) {
            return index
        }
    }
    return -1
}

fun String.indexOfLeftmostNonRepeatingCharImproved(): Int {
    val (minCode, maxCode) = minMaxCharValue()
    val indexStore = IntArray(maxCode - minCode + 1) { -1 }
    for ((index, char) in this.withIndex()) {
        if (indexStore[char.code - minCode] == -1) { // first occurrence
            indexStore[char.code - minCode] = index // save index to store
        } else {
            indexStore[char.code - minCode] = -2 // non-first occurrence -> update to -2
        }
    }
    var result = Int.MAX_VALUE
    for (i in 0..maxCode - minCode) {
        if (indexStore[i] >= 0) { // we have a non-repeating char
            result = kotlin.math.min(result, indexStore[i])
        }
    }
    return if (result == Int.MAX_VALUE) {
        -1
    } else {
        result
    }
}

fun String.reverseWords(): String {
    return this.split(" ").reversed().joinToString(separator = " ")
}

/**
 * Reverses words in a given string represented as CharArray.
 * Precondition is that all the words are separated by a single space.
 *
 * First we reverse chars in all words.
 * Then we reverse the entire CharArray.
 */
fun reverseWords(str: CharArray): String {
    var start = 0
    for (end in str.indices) {
        if (str[end] == ' ') {
            reverseSingleWord(str, start, end - 1)
            start = end + 1
        }
    }
    // Don't forget to reverse the last word
    reverseSingleWord(str, start, str.size - 1)
    reverseSingleWord(str, 0, str.size - 1)
    return str.joinToString(separator = "")
}

private fun reverseSingleWord(word: CharArray, from: Int, to: Int) {
    var low = from
    var high = to
    while (low <= high) {
        val tmp = word[low]
        word[low] = word[high]
        word[high] = tmp
        ++low
        --high
    }
}

/**
 * Searchers for occurrences of [pattern] in a given [String].
 * Returns indices of all starting positions of the [pattern] in the [str].
 * If the [str] doesn't contain the [pattern] then return an empty list.
 */
fun findPatternNaive(str: String, pattern: String): List<Int> {
    val windowed = str.windowed(size = pattern.length, step = 1, partialWindows = false)
    val result = mutableListOf<Int>()
    for ((index, window) in windowed.withIndex()) {
        if (window == pattern) {
            result.add(index)
        }
    }
    return result.toList()
}

/**
 * Time complexity: O((n - m + 1) * m)
 * Auxiliary space: O(1)
 */
fun findPatternNaiveVersion2(str: String, pattern: String): List<Int> {
    val m = pattern.length
    val n = str.length
    val result = mutableListOf<Int>()
    for (i in 0..n - m) { // time complexity of the outer loop: n - m + 1
        var j = 0
        while (j < m) { // time complexity of the inner loop: m
            if (pattern[j] != str[i + j]) {
                break
            }
            ++j
        }
        if (j == m) {
            result.add(i)
        }
    }
    return result
}

/**
 * This algorithm takes advantage of the fact that pattern has all
 * distinct characters. This means, that if we find a mismatch at index j (of the pattern)
 * during comparing the pattern with the string, we can be sure
 * that all chars before index j will never match the string if shifted by one position,
 * therefore we can slide the window by j indices.
 *
 * E.G. string    ABCABCD
 *      pattern   ABCD
 *      i = 0 (index of the string)
 *      j = 0 (index of the pattern)
 *      comparison stops at index j = 3 - there's a mismatch: A != D and i = 0
 *      all chars of the pattern before j: ABC will never match
 *      chars in the string if their comparison will start at index i = 1,
 *      so we skip the comparison by 3 indices and begin the next iteration
 *      of the inner loop from i = 3 instead of i = 1.
 *
 * Overall time complexity: O(N)
 */
fun findPatternNaivePatternDistinct(str: String, pattern: String): List<Int> {
    val m = pattern.length
    val n = str.length
    val result = mutableListOf<Int>()
    var i = 0
    while (i <= n - m) {
        var j = 0
        while (j < m) {
            if (pattern[j] != str[i + j]) {
                break
            }
            ++j
        }
        if (j == m) {
            result.add(i)
        }
        if (j == 0) { // fail on first iteration, i.e. the first char in the
            // pattern doesn't match the char in the string
            ++i
        } else { // some chars in the pattern matched, so we skip by j chars.
            i += j
        }
    }
    return result
}


fun findPatternRabinKarp(str: String, pattern: String): List<Int> {
    val prime = 98689
    val d = 31
    // precompute d ^ (pattern.length - 1) % prime
    var highPower = 1
    repeat((1 until pattern.length).count()) {
        highPower = (highPower * d) % prime
    }
    var patternHash = 0
    var textHash = 0
    // compute hash for pattern and substring of text of the same length
    // the resulting hash is computed by the following formula:
    // hash = text[0] * d ^ (pattern.length - 1) + text[1] * d ^ (pattern.length - 2) .. text[pattern.length - 1]
    // this is necessary to reduce the amount of spurious hits during comparison of the hashes.
    // looking at the code it is unclear that we indeed are following the specified formula,
    // so here is an example (omit modulo division for simplicity):
    // hash = 0,
    // iteration 1: hash = (0 * d + pattern[0]) = pattern[0]
    // iteration 2: hash = pattern[0] * d + pattern[1]
    // iteration 3: hash = (pattern[0] * d + pattern[1]) * d + pattern[2] =
    // = pattern[0] * d^2 + pattern[1] * d + pattern[2]
    // As you can see we are completely following the formula
    for (i in pattern.indices) {
        patternHash = (patternHash * d + pattern[i].code) % prime
        textHash = (textHash * d + str[i].code) % prime
    }

    val n = str.length
    val m = pattern.length

    val result = mutableListOf<Int>()
    for (i in 0..(n - m)) {
        // we compare for equality only if hashes of pattern and text match
        if (patternHash == textHash) {
            var equal = true
            for (j in 0 until m) {
                if (str[i + j] != pattern[j]) {
                    equal = false
                    break
                }
            }
            if (equal) {
                result.add(i)
            }
        }
        // we don't rehash for the last window of the text
        if (i < (n - m)) {
            // we use the following formula (omit modulo division for simplicity):
            // textHash[i + 1] = d * (textHash[i] - text[i] * d ^ (pattern.length - 1)) + text[i + pattern.length]
            // basically it is a rolling hash function, we subtract the hashed value of the i-th char and
            // add the hashed value of the (i + pattern.length)-th char
            textHash = (d * (textHash - str[i].code * highPower) + str[i + m].code) % prime
            if (textHash < 0) {
                textHash += prime
            }
        }
    }
    return result
}




