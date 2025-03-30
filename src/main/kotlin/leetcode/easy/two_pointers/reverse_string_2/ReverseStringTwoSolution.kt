package leetcode.easy.two_pointers.reverse_string_2

class ReverseStringTwoSolution {

    fun reverseStr(s: String, k: Int): String {
        val chars = s.toCharArray()
        for (i in chars.indices step 2 * k) {
            var start = i
            var end = minOf(i + k - 1, chars.lastIndex)
            while (start < end) {
                val tmp = chars[start]
                chars[start] = chars[end]
                chars[end] = tmp
                ++start
                --end
            }
        }
        return String(chars)
    }

}