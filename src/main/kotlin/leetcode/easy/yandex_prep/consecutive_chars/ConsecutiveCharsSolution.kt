package leetcode.easy.yandex_prep.consecutive_chars

class ConsecutiveCharsSolution {

    fun maxPower(s: String): Int {
        if (s.isEmpty()) return 0
        if (s.length == 1) return 1
        var res = Int.MIN_VALUE
        var start = 0
        var end = 1
        while (end < s.length) {
            if (s[end] != s[start]) {
                res = maxOf(res, end - start)
                start = end
            }
            ++end
        }
        res = maxOf(res, end - start)
        return res
    }

}