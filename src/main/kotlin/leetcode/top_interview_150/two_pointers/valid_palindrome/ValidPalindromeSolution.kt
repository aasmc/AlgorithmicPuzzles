package leetcode.top_interview_150.two_pointers.valid_palindrome

class ValidPalindromeSolution {

    fun isPalindrome(s: String): Boolean {
        val lower = s.lowercase()
        var start = 0
        var end = lower.lastIndex
        while (start <= end) {
            while (start < lower.length && !lower[start].isLetterOrDigit()) {
                ++start
            }
            while (end >= 0 && !lower[end].isLetterOrDigit()) {
                --end
            }
            if (start > end) {
                break
            }
            if (lower[start] != lower[end]) {
                return false
            }
            ++start
            --end
        }
        return true
    }

}