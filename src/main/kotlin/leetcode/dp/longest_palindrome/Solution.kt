package leetcode.dp.longest_palindrome

fun longestPalindrome(s: String): String {
    var max = Int.MIN_VALUE
    var from = 0
    var to = s.length
    for (i in s.indices) {
        var left = i
        var right = i
        while (left >= 0 && right < s.length && s[left] == s[right]) {
            if ((right - left + 1) > max) {
                max = right - left + 1
                from = left
                to = right
            }
            --left
            ++right
        }

        left = i
        right = i + 1
        while (left >= 0 && right < s.length && s[left] == s[right]) {
            if ((right - left + 1) > max) {
                max = right - left + 1
                from = left
                to = right
            }
            --left
            ++right
        }
    }
    return s.substring(from, to + 1)
}


