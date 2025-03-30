package leetcode.dp.word_break

fun wordBreak(s: String, wordDict: List<String>): Boolean {
    val dp = BooleanArray(s.length + 1) { false }
    dp[s.length] = true
    for (i in s.length - 1 downTo 0) {
        for (word in wordDict) {
            if (isWordLessThatString(i, s.length, word.length) && isSubstringEqualToWord(i, s, word)) {
                // if we could break the string at position i + word.length, then we can safely mark
                // current position as a possible word break.
                dp[i] = dp[i + word.length]
            }
            if (dp[i]) break
        }
    }
    return dp[0]
}

private fun isSubstringEqualToWord(from: Int, s: String, word: String): Boolean {
    return s.substring(from, from + word.length) == word
}

private fun isWordLessThatString(curIdx: Int, stringLength: Int, wordLength: Int): Boolean {
    return curIdx + wordLength <= stringLength
}