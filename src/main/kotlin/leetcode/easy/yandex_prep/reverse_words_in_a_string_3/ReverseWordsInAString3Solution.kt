package leetcode.easy.yandex_prep.reverse_words_in_a_string_3

class ReverseWordsInAString3Solution {

    fun reverseWords(s: String): String {
        var left = 0
        var right = 0
        val sb = StringBuilder()
        while (right < s.length) {
            while (right < s.length && s[right] != ' ') {
                ++right
            }
            for (i in right - 1 downTo left) {
                sb.append(s[i])
            }
            while (right < s.length && s[right] == ' ') {
                sb.append(' ')
                ++right
            }
            left = right
        }
        return sb.toString()
    }

}