package leetcode.ace_coding.strings_arrays.reverse_words

fun reverseWordsSimple(s: String): String {
    return s.trim().split("\\s+".toRegex()).reversed().joinToString(separator = " ")
}

fun reverseWords(s: String): String {
    var left = 0
    var right = 0
    val words = mutableListOf<String>()
    while (right <= s.lastIndex) {
        if (s[left] == ' ') {
            ++left
            ++right
        } else if (s[right] == ' ') {
            words.add(s.substring(left, right))
            left = right + 1
            right++
        } else {
            ++right
        }
    }
    if (left < right) {
        words.add(s.substring(left, right))
    }
    val sb = StringBuilder()
    while (words.isNotEmpty()) {
        sb.append(words.removeLast())
        if (words.isNotEmpty()) {
            sb.append(" ")
        }
    }
    return sb.toString()
}