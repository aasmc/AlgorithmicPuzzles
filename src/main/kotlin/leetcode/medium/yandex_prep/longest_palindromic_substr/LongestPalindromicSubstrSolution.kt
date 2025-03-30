package leetcode.medium.yandex_prep.longest_palindromic_substr

class LongestPalindromicSubstrSolution {

    /**
     * Idea. For every character in the string, we want to find out
     * the longest palindromic substring with that character in the
     * center of it. We can do this by going to the left of the character
     * and to the right of the character simultaneously. This is true for
     * palindromic strings of odd length. But we need to
     * consider an edge case, when the string is of even length also.
     *
     * E.g. str = babad
     * Iteration 1: char = b, go to the left = none, then stop, max palindrome = b
     * Iteration 2: char = a, go to the left, char b, to the right char b, max palindrome = bab
     * etc.
     */
    fun longestPalindrome(s: String): String {
        var left = 0
        var right = 0
        var max = 0
        for (i in s.indices) {
            // case 1: check of odd length string is a palindrome
            var l = i - 1
            var r = i + 1
            while (l >= 0 && r < s.length && s[l] == s[r]) {
                if (r - l + 1 > max) {
                    max = r - l + 1
                    left = l
                    right = r
                }
                --l
                ++r
            }
            // case 2: check if even length string is a palindrome
            l = i
            r = i + 1
            while (l >= 0 && r < s.length && s[l] == s[r]) {
                if (r - l + 1 > max) {
                    max = r - l + 1
                    left = l
                    right = r
                }
                ++r
                --l
            }
        }
        return s.substring(left, right + 1)
    }


}