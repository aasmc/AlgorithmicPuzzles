package leetcode.easy.two_pointers.valid_palindrome_2

class ValidPalindromeSolution2 {

    fun validPalindrome(s: String): Boolean {
        var start = 0
        var end = s.lastIndex
        while (start < end) {
            if (s[start] != s[end]) {
                return isPalindrome(s, start, end - 1) || isPalindrome(s, start + 1, end)
            }
            ++start
            --end
        }
        return true
    }

    private fun isPalindrome(s: String, from: Int, to: Int): Boolean {
        var start = from
        var end = to
        while (start < end) {
            if (s[start++] != s[end--]) {
                return false
            }
        }
        return true
    }
}