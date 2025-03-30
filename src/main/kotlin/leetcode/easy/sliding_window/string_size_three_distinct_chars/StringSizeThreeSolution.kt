package leetcode.easy.sliding_window.string_size_three_distinct_chars

class StringSizeThreeSolution {

    fun countGoodSubstrings(s: String): Int {
        return s.windowed(3).count { it[0] != it[1] && it[1] != it[2] && it[0] != it[2] }
    }

}