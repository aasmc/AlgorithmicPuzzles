package leetcode.dp.longest_palindrome

fun longestPalindrome(s: String): String {
    var max = Int.MIN_VALUE
    var from = 0
    var to = s.length
    for (i in s.indices) {
        var left = i
        var right = i
        // check for substrings of odd length
        while (isPalindrome(left, right, s)) {
            if (isCurrentSubsequenceGreater(left, right, max)) {
                max = right - left + 1
                from = left
                to = right
            }
            --left
            ++right
        }

        // check for substrings of even length
        left = i
        right = i + 1
        while (isPalindrome(left, right, s)) {
            if (isCurrentSubsequenceGreater(left, right, max)) {
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

private fun isCurrentSubsequenceGreater(curLeftIdx: Int, curRightIdx: Int, curMax: Int): Boolean {
    return curRightIdx - curLeftIdx + 1 > curMax
}

private fun isPalindrome(leftIdx: Int, rightIdx: Int, s: String): Boolean {
    return leftIdx >= 0 && rightIdx < s.length && s[leftIdx] == s[rightIdx]
}


