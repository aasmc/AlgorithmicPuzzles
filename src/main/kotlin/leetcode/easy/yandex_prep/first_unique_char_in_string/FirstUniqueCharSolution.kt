package leetcode.easy.yandex_prep.first_unique_char_in_string

class FirstUniqueCharSolution {

    fun firstUniqChar(s: String): Int {
        val charToCount = hashMapOf<Char, Int>()
        s.forEach { ch ->
            charToCount.merge(ch, 1, Int::plus)
        }

        s.forEachIndexed { index, char ->
            if (charToCount[char]!! == 1) {
                return index
            }
        }
        return -1
    }
}