package leetcode.top_interview_150.array_string.index_of_first_occurrence


class IndexOfFirstOccurrenceSolution {
    val x = 31
    val mod = 98689
    fun strStr(haystack: String, needle: String): Int {
        if (needle.isEmpty()) return 0
        if (needle.length > haystack.length) return -1
        var highPower = 1
        repeat((1 until needle.length).count()) {
            highPower = (highPower * x) % mod
        }
        var needleHash = 0
        var haystackHash = 0
        for (i in needle.indices) {
            needleHash = (needleHash * x + needle[i].code) % mod
            haystackHash = (haystackHash * x + haystack[i].code) % mod
        }
        val n = haystack.length
        val m = needle.length
        for (i in 0..(n - m)) {
            if (needleHash == haystackHash) {
                var equal = true
                for (j in 0 until m) {
                    if (haystack[i + j] != needle[j]) {
                        equal = false
                        break
                    }
                }
                if (equal) {
                    return i
                }
            }
            if (i < (n - m)) {
                haystackHash = (x * (haystackHash - haystack[i].code * highPower) + haystack[i + m].code) % mod
                if (haystackHash < 0) {
                    haystackHash += mod
                }
            }
        }
        return -1
    }

}