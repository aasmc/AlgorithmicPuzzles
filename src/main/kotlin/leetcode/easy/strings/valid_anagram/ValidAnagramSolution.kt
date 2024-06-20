package leetcode.easy.strings.valid_anagram

class ValidAnagramSolution {

    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val charToCount = hashMapOf<Char, Int>()
        s.forEach { ch ->
            charToCount.merge(ch, 1, Int::plus)
        }
        t.forEach { ch ->
            val merged = charToCount.merge(ch, -1, Int::plus)!!
            if (merged < 0) {
                return false
            }
        }
        return true
    }

}