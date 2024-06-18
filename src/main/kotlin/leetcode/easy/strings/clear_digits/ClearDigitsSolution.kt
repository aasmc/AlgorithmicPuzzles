package leetcode.easy.strings.clear_digits

class ClearDigitsSolution {

    fun clearDigits(s: String): String {
        var res = s
        var idx = 0
        while(idx < res.length) {
            if (res[idx].isDigit()) {
                res = if (idx != 0) {
                    res.substring(0, idx - 1) + res.substring(idx + 1)
                } else {
                    res.substring(1)
                }
                --idx
            } else {
                ++idx
            }
        }
        return res
    }

    fun clearDigits2(s: String): String {
        var idx = 0
        val sb = StringBuilder(s)
        while(idx < sb.length) {
            if (sb[idx].isDigit()) {
                if (idx != 0) {
                    sb.delete(idx - 1, idx + 1)
                } else {
                    sb.delete(0, 1)
                }
                --idx
            } else {
                ++idx
            }
        }
        return sb.toString()
    }

}