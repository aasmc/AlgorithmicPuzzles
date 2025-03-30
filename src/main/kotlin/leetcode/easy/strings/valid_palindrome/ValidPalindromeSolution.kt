package leetcode.easy.strings.valid_palindrome

class ValidPalindromeSolution {

    fun isPalindrome(s: String): Boolean {
        var start = 0
        var end = s.lastIndex
        while (start < end) {
            while (start < s.length && !s[start].isLetterOrDigit()) {
                ++start
            }
            while (end >= 0 && !s[end].isLetterOrDigit()) {
                --end
            }
            if (start > end) {
                break
            }
            if (s[start].lowercase() != s[end].lowercase()) {
                return false
            }
            ++start
            --end
        }
        return true
    }

}