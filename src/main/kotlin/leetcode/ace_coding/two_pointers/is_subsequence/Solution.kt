package leetcode.ace_coding.two_pointers.is_subsequence

fun isSubsequence(s: String, t: String): Boolean {
    var sIdx = 0
    var tIdx = 0
    while (sIdx < s.length && tIdx < t.length) {
        if (s[sIdx] == t[tIdx]) {
            sIdx++
        }
        tIdx++
    }
    return sIdx == s.length
}