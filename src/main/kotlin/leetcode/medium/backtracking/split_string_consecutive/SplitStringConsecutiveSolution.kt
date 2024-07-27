package leetcode.medium.backtracking.split_string_consecutive

class SplitStringConsecutiveSolution {

    fun splitString(s: String): Boolean {

        // idx - starting index of the next substring we are considering
        // digit - previous digit from the substring
        fun dfs(idx: Int, digit: Long): Boolean {
            if (idx == s.length) return true

            var nextDigit = 0L
            for (j in idx until s.length) {
                nextDigit = nextDigit * 10 + (s[j] - '0')
                if (nextDigit + 1 == digit && dfs(j + 1, nextDigit)) {
                    return true
                }
            }
            return false
        }

        var digit = 0L
        for (i in 0 until s.lastIndex) {
            digit = digit * 10 + (s[i] - '0')
            if (dfs(i + 1, digit)) {
                return true
            }
        }
        return false
    }

}