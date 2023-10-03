package leetcode.ace_coding.strings_arrays.reverse_words

fun reverseWords(s: String): String {
    return s.trim().split("\\s+".toRegex()).reversed().joinToString(separator = " ")
}