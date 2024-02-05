package leetcode.top_interview_150.hashmap.is_anagram

class IsAnagramSolution {

    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val map = hashMapOf<Char, Int>()
        s.forEach { ch -> map.merge(ch, 1, Int::plus) }
        for (ch in t) {
            map.merge(ch, -1, Int::plus)
            if (map[ch]!! <0) return false
        }
        return true
    }

}