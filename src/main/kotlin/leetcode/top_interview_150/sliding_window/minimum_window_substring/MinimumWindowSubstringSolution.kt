package leetcode.top_interview_150.sliding_window.minimum_window_substring

class MinimumWindowSubstringSolution {

    fun minWindow(s: String, t: String): String {
        if (s == t) return s
        if (t.isEmpty()) return ""
        val needMap = hashMapOf<Char, Int>()
        val haveMap = hashMapOf<Char, Int>()
        t.forEach { ch ->
            needMap.merge(ch, 1, Int::plus)
        }
        val need = needMap.size
        var have = 0
        var left = 0
        var res = -1 to -1
        var resLength = Int.MAX_VALUE
        for (right in s.indices) {
            val ch = s[right]
            haveMap.merge(ch, 1, Int::plus)
            if (needMap.containsKey(ch) && haveMap[ch] == needMap[ch]) {
                ++have
            }
            while (have == need) {
                // possibly update the result
                val curLength = right - left + 1
                if (curLength < resLength) {
                    res = left to right
                    resLength = curLength
                }
                // pop from the left of window
                val leftCh = s[left]
                haveMap.merge(leftCh, -1, Int::plus)
                if (needMap.containsKey(leftCh) && haveMap[leftCh]!! < needMap[leftCh]!!) {
                    --have
                }
                ++left
            }
        }

        return if (resLength != Int.MAX_VALUE) s.substring(res.first, res.second + 1) else ""
    }
}