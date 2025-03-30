package leetcode.easy.sliding_window.k_beauty_of_number

class KBeautySolution {

    fun divisorSubstrings(num: Int, k: Int): Int {
        var result = 0
        var left = 0
        var right = k - 1
        val str = num.toString()
        while (right < str.length) {
            val window = str.substring(left, right + 1).toInt()
            if (window != 0 && num % window == 0) {
                ++result
            }
            ++left
            ++right
        }
        return result
    }

    fun divisorSubstrings2(num: Int, k: Int): Int {
        return num.toString()
            .windowed(k, 1)
            .map { it.toInt() }
            .count { it != 0 && num % it == 0 }
    }

}